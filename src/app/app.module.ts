import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MaterialModule} from "./material/material.module";
import { DataTableComponent } from './components/data-table/data-table.component';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import {ReactiveFormsModule} from "@angular/forms";
import { GroupDataTableComponent } from './components/group-data-table/group-data-table.component';


@NgModule({
  declarations: [
    AppComponent,
    DataTableComponent,
    GroupDataTableComponent
  ],
    imports: [
        HttpClientModule,
        BrowserAnimationsModule,
        MaterialModule,
        MatTableModule,
        MatPaginatorModule,
        MatSortModule,
        ReactiveFormsModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
