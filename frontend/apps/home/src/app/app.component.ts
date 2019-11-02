import {Component, OnInit} from '@angular/core';
import {ViewState} from './navbar/view-switch';
import {animate, style, transition, trigger} from "@angular/animations";

@Component({
  selector: 'hmd-root',
  template: `
      <navbar
              (filterValueEmitter)="filterValue($event)"
              (viewStateEmitter)="changeView($event)"
              [currentViewState]="currentViewState">
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
export class AppComponent implements OnInit {
  filter = '';
  ViewState = ViewState;
  currentViewState: ViewState;

  ngOnInit(): void {
    let viewMode = localStorage.getItem('viewMode');
    if (viewMode) {
      this.currentViewState = ViewState[viewMode];
    } else {
      this.currentViewState = ViewState.CARDS;
    }
  }

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
