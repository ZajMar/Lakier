import {Component, OnInit} from '@angular/core';
import {LocalitiesClientService, Locality} from "../../services/localities-client.service";

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {
  localities: Locality[] = [];

  constructor(private localitiesClientService: LocalitiesClientService) {
  }

  ngOnInit(): void {

  }

  searchForLocality(searchFilter: string) {
    this.localitiesClientService.getLocalities(searchFilter).subscribe(
      data => {
        this.localities = data;
      },
      error => {
        console.log(error);
      })
  }
}
