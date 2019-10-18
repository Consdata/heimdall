import {NgModule} from '@angular/core';
import {GridView} from './components/grid-view';
import {GridCellText} from './components/cell-types/grid-cell-text';
import {GridContentVersions} from './components/grid-content-versions';
import {GridCellVersion} from './components/cell-types/grid-cell-version';
import {GridCellBackground} from './components/cell-types/grid-cell-background';
import {BrowserModule} from '@angular/platform-browser';

@NgModule({
  declarations: [
    GridView,
    GridCellText,
    GridContentVersions,
    GridCellVersion,
    GridCellBackground,
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  exports: [
    GridView
  ],
  bootstrap: []
})
export class GridModule {
}
