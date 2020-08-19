import {AfterViewInit, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {LacquerDataTableItem, LacquerTableDataSource} from './lacquer-table-data-source';
import {LacquerClientService} from "../../services/lacquer/lacquer-client.service";
import {MatPaginator} from "@angular/material/paginator";
import {debounceTime, distinctUntilChanged, tap} from "rxjs/operators";
import {MatSort} from "@angular/material/sort";
import {fromEvent} from "rxjs";
import {SelectionModel} from '@angular/cdk/collections';
import {FormsService} from "../../services/selected-lacquer/forms.service";

@Component({
  selector: 'data-table',
  templateUrl: './data-table.component.html',
  styleUrls: ['./data-table.component.css']
})
export class DataTableComponent implements AfterViewInit, OnInit {
  dataSource: LacquerTableDataSource;
  displayedColumns = ['select', 'code', 'name', 'brand', 'popularity'];
  selection = new SelectionModel<LacquerDataTableItem>(true, []);

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild('lacquerNameInput') lacquerNameInput: ElementRef;
  @ViewChild('lacquerCodeInput') lacquerCodeInput: ElementRef;
  @ViewChild('lacquerBrandInput') lacquerBrandInput: ElementRef;
  @ViewChild('lacquerPopularityInput') lacquerPopularityInput: ElementRef;

  constructor(private lacquerClientService: LacquerClientService,
              private selectedLacquerService: FormsService) {
  }

  ngOnInit() {
    this.dataSource = new LacquerTableDataSource(this.lacquerClientService);
    this.dataSource.loadLacquer();
  }

  ngAfterViewInit(): void {
    this.initializeFilterHandler();

    this.sort.sortChange.subscribe(() => {
      this.paginator.pageIndex = 0;
      this.loadLacquerPages();
    });

    this.paginator.page
      .pipe(
        tap(() => this.loadLacquerPages())
      )
      .subscribe();

    this.dataSource.reloadData.subscribe(() => {
      this.selection.clear();
    });

    this.selectedLacquerService.clickedAddToGroup.subscribe(() => {
      this.selectedLacquerService.updateSelectedRows(this.selection.selected);
    });
  }

  private initializeFilterHandler() {
    const lacquerNameInputFromEvent = fromEvent(this.lacquerNameInput.nativeElement, 'keyup');
    const lacquerCodeInputFromEvent = fromEvent(this.lacquerCodeInput.nativeElement, 'keyup');
    const lacquerBrandInputFromEvent = fromEvent(this.lacquerBrandInput.nativeElement, 'keyup');
    const lacquerPopularityInputFromEvent = fromEvent(this.lacquerPopularityInput.nativeElement, 'keyup');
    const events = [lacquerNameInputFromEvent, lacquerCodeInputFromEvent, lacquerBrandInputFromEvent, lacquerPopularityInputFromEvent];
    events.forEach((singleFromEvent) =>
      singleFromEvent
      .pipe(
        debounceTime(150),
        distinctUntilChanged(),
        tap(() => {
          this.paginator.pageIndex = 0;
          this.loadLacquerPages();
        })
      )
      .subscribe()
    );
  }

  loadLacquerPages() {
    this.dataSource.loadLacquer(
      this.lacquerNameInput.nativeElement.value,
      this.lacquerCodeInput.nativeElement.value,
      this.lacquerBrandInput.nativeElement.value,
      this.lacquerPopularityInput.nativeElement.value,
      this.sort.direction,
      this.paginator.pageIndex,
      this.paginator.pageSize
    );
  }

  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.lacquerSubject.value.length;
    return numSelected === numRows;
  }

  /** Selects all rows if they are not all selected; otherwise clear selection. */
  masterToggle() {
    this.isAllSelected() ?
      this.selection.clear() :
      this.dataSource.lacquerSubject.value.forEach(row => this.selection.select(row));
  }

  /** The label for the checkbox on the passed row */
  checkboxLabel(row?: LacquerDataTableItem): string {
    if (!row) {
      return `${this.isAllSelected() ? 'select' : 'deselect'} all`;
    }
    return `${this.selection.isSelected(row) ? 'deselect' : 'select'} ${row.name}`;
  }
}
