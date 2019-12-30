import {NgModule} from '@angular/core';

import {BrowserModule} from '@angular/platform-browser';
import {GridViewComponent} from './components/grid-view.component';
import {GridCellTextComponent} from './components/cell-types/grid-cell-text.component';
import {GridContentVersionsComponent} from './components/grid-content-versions.component';
import {GridCellVersionComponent} from './components/cell-types/grid-cell-version.component';
import {GridCellBackgroundComponent} from './components/cell-types/grid-cell-background.component';
import {TooltipModule} from "../tooltip/tooltip.module";

@NgModule({
  declarations: [
    GridViewComponent,
    GridCellTextComponent,
    GridContentVersionsComponent,
    GridCellVersionComponent,
    GridCellBackgroundComponent,
  ],
  imports: [
    BrowserModule,
    TooltipModule
  ],
  providers: [],
  exports: [
    GridViewComponent
  ],
  bootstrap: []
})
export class GridModule {
}
