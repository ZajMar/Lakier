import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {TableComponent} from './components/table/table.component';
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {DataTableComponent} from './components/data-table/data-table.component';
import {LayoutModule} from '@angular/cdk/layout';
import {MaterialModule} from "./material/material.module";


@NgModule({
  declarations: [
    AppComponent,
    TableComponent,
    DataTableComponent,
  ],
  imports: [
    HttpClientModule,
    BrowserAnimationsModule,
    LayoutModule,
    MaterialModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
