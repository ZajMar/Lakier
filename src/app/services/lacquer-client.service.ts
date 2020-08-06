import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class LacquerClientService {

  constructor(private httpClient: HttpClient) {
  }

  public getLacquer(nameFilter: string, codeFilter: string, page: number, pageSize: number, sort: string): Observable<LacquerRootObject> {
    return this.httpClient.get<LacquerRootObject>('http://localhost:8080/lacquer', {
      params: new HttpParams()
        .set('name', nameFilter)
        .set('code', codeFilter)
        .set('page', page.toString())
        .set('pageSize', pageSize.toString())
        .set('sort', sort)
    });
  }
}

export interface Lacquer {
  lacquerId: string;
  lacquerName: string;
}

export interface LacquerRootObject {
  totalRecords: string;
  page: number;
  pageSize: number;
  results: Lacquer[];
}

