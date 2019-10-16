import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {RouterModule} from '@angular/router';
import {RestApiModule} from '@heimdall-frontend/rest-api';

import {AppComponent} from './app.component';
import {LibrariesListComponent} from './libraries/libraries-list.component';
import {LibraryProjectDependencyComponent} from './libraries/library-project-dependency.component';
import {LibraryComponent} from './libraries/library.component';
import {NavbarComponent} from './navbar/navbar.component';
import {HeimdallMonitorTrackingRestModule} from '@heimdall-frontend/heimdall/monitor-tracking/rest';
import {GridView} from './grid/grid-view';
import {GridCell} from './grid/grid-cell';
import {GridContentVersions} from './grid/grid-content-versions';
import {GridCellVersion} from './grid/grid-cell-version';

@NgModule({
  declarations: [AppComponent,
    LibrariesListComponent,
    LibraryComponent,
    LibraryProjectDependencyComponent,
    GridView,
    GridCell,
    GridContentVersions,
    GridCellVersion,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    RestApiModule,
    RouterModule.forRoot([], {initialNavigation: 'enabled'}),
    HeimdallMonitorTrackingRestModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
