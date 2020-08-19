import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MaterialModule} from "./material/material.module";
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorIntl, MatPaginatorModule} from '@angular/material/paginator';
import {MatSortModule} from '@angular/material/sort';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {DataTableComponent} from './components/data-table/data-table.component';
import {GroupDataTableComponent} from './components/group-data-table/group-data-table.component';
import {DragDropModule} from "@angular/cdk/drag-drop";
import {AddButtonComponent} from './components/add-button/add-button.component';
import {MatNativeDateModule} from '@angular/material/core';
import {getPolishPaginatorIntl} from "./components/data-table/polish-paginator-intl";

@NgModule({
  declarations: [
    AppComponent,
    DataTableComponent,
    GroupDataTableComponent,
    AddButtonComponent
  ],
  imports: [
    HttpClientModule,
    BrowserAnimationsModule,
    MaterialModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    ReactiveFormsModule,
    DragDropModule,
    FormsModule,
    MatNativeDateModule
  ],
  providers: [
    {provide: MatPaginatorIntl, useValue: getPolishPaginatorIntl()},
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
