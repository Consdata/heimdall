import {Component, EventEmitter, Output} from '@angular/core';

export enum ViewState {
  GRID, CARDS
}

@Component({
  selector: 'view-switch',
  template: `
    <div class="view-switch-container">
      <div class="view-switch-icon">
        <div class="view-switch-icon-grid"></div>
      </div>
      <label class="view-switch-label">
        <input type="checkbox" (change)="changeView($event)" checked="true">
        <span class="view-switch-slider-round"></span>
      </label>
      <div class="view-switch-icon">
        <div class="view-switch-icon-cards"></div>
      </div>
    </div>
  `,
  styleUrls: [
    'view-switch.scss'
  ]
})
export class ViewSwitch {

  viewState: ViewState;

  @Output() viewStateEmitter = new EventEmitter<ViewState>();

  constructor() {
    this.viewState = ViewState.CARDS
  }

  changeView(event: any): void {
    if (event.target.checked === true) {
      this.viewState = ViewState.CARDS
    } else {
      this.viewState = ViewState.GRID
    }
    this.viewStateEmitter.emit(this.viewState);
  }
}
