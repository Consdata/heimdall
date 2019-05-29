import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {RouterModule} from '@angular/router';
import {RestApiModule} from '@heimdall-front/rest-api';

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    RestApiModule,
    RouterModule.forRoot([], {initialNavigation: 'enabled'})
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
