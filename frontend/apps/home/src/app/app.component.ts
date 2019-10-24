import {Component} from '@angular/core';
import {ViewState} from './navbar/view-switch';

@Component({
  selector: 'hmd-root',
  template: `
    <navbar 
      (filterValueEmitter)="filterValue($event)"
      (viewStateEmitter)="changeView($event)">
    </navbar>
    <libraries-list
     *ngIf="isSelectedView(ViewState.CARDS)"
      [filterValue]="filter">
    </libraries-list>
    <grid-view
      *ngIf="isSelectedView(ViewState.GRID)">
    </grid-view>
  `,
  styleUrls: [
    'app.component.scss'
  ]
})
export class AppComponent {
  filter = '';
  ViewState = ViewState;
  currentViewState: ViewState = ViewState.CARDS;

  filterValue(filter) {
    this.filter = filter;
  }

  changeView(event: ViewState): void {
    this.currentViewState = event;
  }
  isSelectedView(view: ViewState): boolean {
    return this.currentViewState === view;
  }
}
