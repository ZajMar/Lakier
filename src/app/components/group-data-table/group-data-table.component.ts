import {Component, OnInit, ViewChild} from '@angular/core';
import {CdkDragDrop, moveItemInArray} from '@angular/cdk/drag-drop';
import {MatTable, MatTableDataSource} from "@angular/material/table";
import {FormsService} from "../../services/selected-lacquer/forms.service";
import {LacquerDataTableItem} from "../data-table/lacquer-table-data-source";
import {LacquerClientService, LacquerGroup} from "../../services/lacquer/lacquer-client.service";
import {BehaviorSubject} from "rxjs";

@Component({
  selector: 'group-data-table',
  templateUrl: './group-data-table.component.html',
  styleUrls: ['./group-data-table.component.css']
})
export class GroupDataTableComponent implements OnInit {
  @ViewChild('table') table: MatTable<LacquerDataTableItem>;
  dataSource: MatTableDataSource<LacquerDataTableItem>;
  displayedColumns: string[] = ['position', 'code', 'name', 'brand', 'popularity', 'remove'];
  availableGroups = new BehaviorSubject<LacquerGroup[]>([]);
  selectedGroupId: string;

  constructor(private formsService: FormsService,
              private lacquerClientService: LacquerClientService) {
  }

  dropTable(event: CdkDragDrop<MatTableDataSource<LacquerDataTableItem>>) {
    const prevIndex = this.dataSource.data.findIndex((d) => d === event.item.data);
    moveItemInArray(this.dataSource.data, prevIndex, event.currentIndex);
    for (let i = 0; i < this.dataSource.data.length; i++) {
      this.dataSource.data[i].position = i;
    }
    this.table.renderRows();
  }

  ngOnInit(): void {
    this.dataSource = new MatTableDataSource();
    this.formsService.getSelectedRows()
      .subscribe(selected => {
        let mergedGroupElements = this.dataSource.data.concat(selected);
        this.lacquerClientService.getSortedLacquers(mergedGroupElements, this.formsService.sortBy, this.formsService.sortOrder, this.formsService.allowDuplicates)
          .subscribe(sortedLacquers => {
            let lacquers = [];
            lacquers = sortedLacquers.map(lacquer => ({
              id: lacquer.lacquerId,
              code: lacquer.lacquerCode,
              name: lacquer.lacquerName,
              position: lacquer.position,
              brand: lacquer.lacquerBrand,
              popularity: lacquer.lacquerPopularity
            }));
            this.dataSource.data = lacquers;
          });
      });
    this.lacquerClientService.getAllGroups()
      .subscribe(allGroups => {
        this.availableGroups.next(allGroups);
        allGroups.forEach(a => {
          console.log(a.groupId)
        })
      });
  }

  removeAll() {
    this.dataSource.data = [];
  }

  removeAt(index: number) {
    const data = this.dataSource.data;
    data.splice(index, 1);
    this.dataSource.data = data;
    for (let i = 0; i < this.dataSource.data.length; i++) {
      this.dataSource.data[i].position = i;
    }
  }

  public refreshGroup() {
    this.lacquerClientService.getLacquerGroup(this.selectedGroupId)
      .subscribe(group => {
        let lacquerGroupElements = group.lacquerGroupElements;
        let lacquers = [];
        lacquers = lacquerGroupElements.map(lacquer => ({
          id: lacquer.lacquerId,
          code: lacquer.lacquerCode,
          name: lacquer.lacquerName,
          position: lacquer.position,
          brand: lacquer.lacquerBrand,
          popularity: lacquer.lacquerPopularity
        }));
        this.dataSource.data = lacquers;
      });
  }

  groupName: string;
  public groupNameNotPresent(): boolean {
    return this.groupName == null || typeof this.groupName === 'undefined' || this.groupName == '';
  }

  public saveGroup(): void {
    this.lacquerClientService.saveGroup(this.dataSource.data, this.formsService.sortBy, this.formsService.sortOrder, this.formsService.allowDuplicates, this.groupName)
      .subscribe(id => {
        this.lacquerClientService.getAllGroups()
          .subscribe(allGroups => {
            this.availableGroups.next(allGroups);
          });
      });
  }

  print(): void {
    let printContents, popupWin;
    printContents = document.getElementById('print-section').innerHTML;
    popupWin = window.open('', '_blank', 'top=0,left=0,height=100%,width=auto');
    popupWin.document.open();
    popupWin.document.write(`
      <html>
        <head>
          <title>Print tab</title>
          <style>
          //........Customized style.......
          </style>
        </head>
         <body onload="window.print();window.close()">${printContents}</body>
      </html>`
    );
    popupWin.document.close();
  }
}
