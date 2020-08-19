import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import {LacquerDataTableItem} from "../../components/data-table/lacquer-table-data-source";

@Injectable({
  providedIn: 'root'
})
export class LacquerClientService {

  constructor(private httpClient: HttpClient) {
  }

  public getLacquer(nameFilter: string, codeFilter: string, brandFilter: string, popularityFilter: string, page: number, pageSize: number, sort: string): Observable<LacquerRootObject> {
    return this.httpClient.get<LacquerRootObject>('http://localhost:8080/lacquer', {
      params: new HttpParams()
        .set('name', nameFilter)
        .set('code', codeFilter)
        .set('brand', brandFilter)
        .set('popularity', popularityFilter)
        .set('page', page.toString())
        .set('pageSize', pageSize.toString())
        .set('sort', sort)
    });
  }

  public getSortedLacquers(lacquerDataTableItems: LacquerDataTableItem[], sortBy: string, sortOrder: string, allowDuplicates: boolean): Observable<Lacquer[]> {
    let ids = lacquerDataTableItems.map(item => item.id);
    let request = {ids: ids, sortBy: sortBy, sortOrder: sortOrder, allowDuplicates: allowDuplicates} as SortLacquersRequest;
    return this.httpClient.post<Lacquer[]>('http://localhost:8080/lacquer-group/sort-lacquers-in-group', request, {});
  }

  public saveGroup(lacquerDataTableItems: LacquerDataTableItem[], sortBy: string, sortOrder: string, allowDuplicates: boolean, groupName: string): Observable<string> {
    let lacquerGroupElements = [] as Array<Lacquer>;
    for (let item of lacquerDataTableItems) {
      lacquerGroupElements.push({lacquerId: item.id, lacquerCode: item.code, lacquerBrand: item.brand, lacquerPopularity: item.popularity, lacquerName: item.name, position: item.position});
    }
    let lacquerGroup = {
      lacquerGroupElements: lacquerGroupElements,
      groupName: groupName,
      sortBy: sortBy,
      sortOrder: sortOrder,
      allowDuplicates: allowDuplicates
    } as LacquerGroup;
    return this.httpClient.post<string>('http://localhost:8080/lacquer-group/create-group', lacquerGroup, {});
  }

  public getAllGroups() {
    return this.httpClient.get<LacquerGroup[]>('http://localhost:8080/lacquer-group/all-groups', {});
  }

  public getLacquerGroup(lacquerGroupId: string): Observable<LacquerGroup> {
    return this.httpClient.get<LacquerGroup>('http://localhost:8080/lacquer-group/get-group', {
      params: new HttpParams()
        .set('lacquerGroupId', lacquerGroupId)
    });
  }
}

export interface Lacquer {
  lacquerId: string;
  lacquerCode: string;
  lacquerName: string;
  position: number;
  lacquerPopularity: string;
  lacquerBrand: string;
}

export interface SortLacquersRequest {
  ids: string[];
  sortBy: string;
  sortOrder: string;
  allowDuplicates: boolean;
}

export interface LacquerRootObject {
  totalRecords: string;
  page: number;
  pageSize: number;
  results: Lacquer[];
}

export interface LacquerGroup {
  groupName: string;
  sortBy: string;
  sortOrder: string;
  allowDuplicates: boolean;
  lacquerGroupElements: Lacquer[];
  groupId: string;
}
