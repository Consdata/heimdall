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
import {GridModule} from './grid/grid.module';
import {ViewSwitch} from './navbar/view-switch';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {TooltipModule} from "./tooltip/tooltip.module";

@NgModule({
  declarations: [AppComponent,
    LibrariesListComponent,
    LibraryComponent,
    LibraryProjectDependencyComponent,
    NavbarComponent,
    ViewSwitch
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    RestApiModule,
    RouterModule.forRoot([], {initialNavigation: 'enabled'}),
    HeimdallMonitorTrackingRestModule.forRoot(),
    GridModule,
    TooltipModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
