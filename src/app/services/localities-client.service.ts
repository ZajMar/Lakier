import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class LocalitiesClientService {

  constructor(private httpClient: HttpClient) { }

  public getLocalities(searchFilter: string): Observable<Locality[]> {
    return this.httpClient.get<Locality[]>("http://localhost:8080/units/locality/" + searchFilter + "?page=0&pageSize=10");
  }

}

export interface Locality {
  id: string;
  name: string;
  parentId: string;
  level: number;
  kind: string;
  hasDescription: boolean;
}
