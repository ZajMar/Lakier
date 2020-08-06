import {DataSource} from '@angular/cdk/collections';
import {catchError, finalize} from 'rxjs/operators';
import {BehaviorSubject, Observable, of} from 'rxjs';
import {LacquerClientService} from "../../services/lacquer-client.service";
import {EventEmitter, Output} from "@angular/core";

export interface LacquerDataTableItem {
  name: string;
  id: string;
}

/**
 * Data source for the DataTable view. This class should
 * encapsulate all logic for fetching and manipulating the displayed data
 * (including sorting, pagination, and filtering).
 */
export class LacquerTableDataSource implements DataSource<LacquerDataTableItem> {

  public lacquerSubject = new BehaviorSubject<LacquerDataTableItem[]>([]);
  public totalNumber;

  private loadingSubject = new BehaviorSubject<boolean>(false);
  public loading$ = this.loadingSubject.asObservable();

  @Output() reloadData: EventEmitter<any> = new EventEmitter();

  constructor(private lacquerClientService: LacquerClientService) {
  }

  connect(): Observable<LacquerDataTableItem[]> {
    return this.lacquerSubject.asObservable();
  }

  public loadLacquer(nameFilter = '', codeFilter = '', sort = '', pageIndex = 0, pageSize = 10) {
    this.loadingSubject.next(true);
    this.lacquerSubject.next([]);
    let sortType = LacquerTableDataSource.mapSortType(sort);
    this.lacquerClientService.getLacquer(nameFilter, codeFilter, pageIndex, pageSize, sortType)
      .pipe(
        catchError(() => of([])),
        finalize(() => this.loadingSubject.next(false))
      )
      .subscribe(rootObject => {
          let tableObjects = [];
          if ("results" in rootObject && "totalRecords" in rootObject) {
            tableObjects = rootObject.results == null ? [] : rootObject.results.map(lacquer => ({
              id: lacquer.lacquerId,
              name: lacquer.lacquerName
            }));
            this.totalNumber = rootObject.totalRecords;
          }
          this.lacquerSubject.next(tableObjects);
          this.reloadData.emit(tableObjects);
        }
      );
  }

  private static mapSortType(sort: string) {
    if (sort == 'asc') {
      return 'lacquerName';
    } else if (sort == 'desc') {
      return '-lacquerName';
    }
    return '';
  }

  disconnect() {
    this.lacquerSubject.complete();
    this.loadingSubject.complete();
  }
}
