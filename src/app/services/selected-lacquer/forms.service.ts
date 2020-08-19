import {EventEmitter, Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {LacquerDataTableItem} from "../../components/data-table/lacquer-table-data-source";

@Injectable({
  providedIn: 'root'
})
export class FormsService {

  clickedAddToGroup: EventEmitter<any> = new EventEmitter();
  selectedRowsSubject = new BehaviorSubject<LacquerDataTableItem[]>([]);
  sortBy = "lacquerName";
  sortOrder = "asc";
  allowDuplicates = false;

  constructor() {
  }

  getSelectedRows(): Observable<LacquerDataTableItem[]> {
    return this.selectedRowsSubject.asObservable();
  }

  updateSelectedRows(selectedRows: LacquerDataTableItem[]) {
    this.selectedRowsSubject.next(selectedRows);
  }
}
