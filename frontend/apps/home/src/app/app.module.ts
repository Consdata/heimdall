import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {RouterModule} from '@angular/router';
import {RestApiModule} from '@heimdall-front/rest-api';
import {NavbarComponent} from './navbar/navbar.component';
import {LibrariesListComponent} from './libraries/libraries-list.component';
import {LibraryComponent} from './libraries/library.component';
import {LibraryProjectDependencyComponent} from './libraries/library-project-dependency.component';

@NgModule({
  declarations: [AppComponent,
    LibrariesListComponent,
    LibraryComponent,
    LibraryProjectDependencyComponent,
    NavbarComponent
  ],
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
