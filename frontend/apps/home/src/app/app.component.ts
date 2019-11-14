import {Component, OnInit} from '@angular/core';
import {animate, style, transition, trigger} from "@angular/animations";
import {ViewState, ViewStateService} from './navbar/view-state.service';

@Component({
  selector: 'hmd-root',
  template: `
      <navbar
              (filterValueEmitter)="filterValue($event)">
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

  constructor(private viewStateService: ViewStateService) {}

  ngOnInit(): void {
    this.viewStateService.currentState.subscribe(viewState => this.currentViewState = viewState)
  }

  filterValue(filter) {
    this.filter = filter;
  }

  isSelectedView(view: ViewState): boolean {
    return this.currentViewState === view;
  }
}
