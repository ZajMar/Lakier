import {DataSource} from '@angular/cdk/collections';
import {catchError} from 'rxjs/operators';
import {BehaviorSubject, Observable, of} from 'rxjs';
import {LacquerClientService} from "../../services/lacquer/lacquer-client.service";
import {EventEmitter, Output} from "@angular/core";

export interface LacquerDataTableItem {
  name: string;
  code: string;
  id: string;
  position: number;
  popularity: string;
  brand: string;
}

/**
 * Data source for the DataTable view. This class should
 * encapsulate all logic for fetching and manipulating the displayed data
 * (including sorting, pagination, and filtering).
 */
export class LacquerTableDataSource implements DataSource<LacquerDataTableItem> {

  public lacquerSubject = new BehaviorSubject<LacquerDataTableItem[]>([]);
  public totalNumber;

  @Output() reloadData: EventEmitter<any> = new EventEmitter();

  constructor(private lacquerClientService: LacquerClientService) {
  }

  connect(): Observable<LacquerDataTableItem[]> {
    return this.lacquerSubject.asObservable();
  }

  public loadLacquer(nameFilter = '', codeFilter = '', brandFilter = '', popularityFilter = '', sort = '', pageIndex = 0, pageSize = 10) {
    this.lacquerSubject.next([]);
    let sortType = LacquerTableDataSource.mapSortType(sort);
    this.lacquerClientService.getLacquer(nameFilter, codeFilter, brandFilter, popularityFilter, pageIndex, pageSize, sortType)
      .pipe(
        catchError(() => of([]))
      )
      .subscribe(rootObject => {
          let tableObjects = [];
          if ("results" in rootObject && "totalRecords" in rootObject) {
            tableObjects = rootObject.results == null ? [] : rootObject.results.map(lacquer => ({
              id: lacquer.lacquerId,
              code: lacquer.lacquerCode,
              name: lacquer.lacquerName,
              position: lacquer.position,
              popularity: lacquer.lacquerPopularity,
              brand: lacquer.lacquerBrand
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
  }
}
