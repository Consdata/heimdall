import {Component} from '@angular/core';
import {ViewState} from './navbar/view-switch';
import {animate, state, style, transition, trigger} from "@angular/animations";

@Component({
  selector: 'hmd-root',
  template: `
    <navbar 
      (filterValueEmitter)="filterValue($event)"
      (viewStateEmitter)="changeView($event)">
    </navbar>
    <libraries-list
     *ngIf="isSelectedView(ViewState.CARDS)"
      [filterValue]="filter"
      [@fadeIn]>
    </libraries-list>
    <grid-view
      *ngIf="isSelectedView(ViewState.GRID)"
      [@fadeIn]>
    </grid-view>
  `,
  styleUrls: [
    'app.component.scss'
  ],
  animations: [
    trigger(
      'fadeIn',
      [
        transition(':enter', [
          style({opacity: 0}),
          animate('0.5s ease-in')
        ])
      ]
    )
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
