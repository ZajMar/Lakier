import { Component, OnInit } from '@angular/core';
import {FormsService} from "../../services/selected-lacquer/forms.service";

interface ComboboxStringOptions {
  value: string;
  viewValue: string;
}

interface ComboboxBooleanOptions {
  value: boolean;
  viewValue: string;
}

@Component({
  selector: 'app-add-button',
  templateUrl: './add-button.component.html',
  styleUrls: ['./add-button.component.css']
})
export class AddButtonComponent implements OnInit {

  sortBy: string = "lacquerName";
  sortOrder: string = "asc";
  allowDuplicates: boolean = false;

  sortByOptions: ComboboxStringOptions[] = [
    {value: 'lacquerName', viewValue: 'Nazwa'},
    {value: 'lacquerCode', viewValue: 'Kod'},
    {value: 'lacquerBrand', viewValue: 'Marka'},
    {value: 'lacquerPopularity', viewValue: 'Popularność'}
  //  {value: 'none', viewValue: 'Brak'}
  ];

  sortOrderOptions: ComboboxStringOptions[] = [
    {value: 'asc', viewValue: 'A -> Z'},
    {value: 'desc', viewValue: 'Z -> A'}
  ];

  overwriteOptions: ComboboxBooleanOptions[] = [
    {value: false, viewValue: 'Bez duplikatów'},
    {value: true, viewValue: 'Z duplikatami'}
  ];

  constructor(private selectedLacquerService: FormsService) {

  }

  ngOnInit(): void {
    this.selectedLacquerService.sortBy = this.sortBy;
  }

  addToGroup(): void {
    this.selectedLacquerService.clickedAddToGroup.emit();
  }

  sortByChanged(): void {
    this.selectedLacquerService.sortBy = this.sortBy;
  }

  sortOrderChanged(): void {
    this.selectedLacquerService.sortOrder = this.sortOrder;
  }

  overwriteChanged(): void {
    this.selectedLacquerService.allowDuplicates = this.allowDuplicates;
  }
}
