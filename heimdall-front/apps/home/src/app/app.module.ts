import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {RouterModule} from '@angular/router';
import {RestApiModule} from '@heimdall-front/rest-api';
import { LibsDashBoardComponent } from './libs-dash-board/libs-dash-board.component';
import { LibViewComponent } from './lib-view/lib-view.component';
import { ProjectInLibViewComponent } from './project-in-lib-view/project-in-lib-view.component';

@NgModule({
  declarations: [AppComponent, LibsDashBoardComponent, LibViewComponent, ProjectInLibViewComponent],
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
