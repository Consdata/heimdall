import {Component, EventEmitter, Input, Output} from '@angular/core';

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
              <input type="checkbox" (change)="changeView($event)" [attr.checked]="viewState ? '' : null">
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
  @Input() viewState: ViewState = null;
  @Output() viewStateEmitter = new EventEmitter<ViewState>();

  changeView(event: any): void {
    if (event.target.checked === true) {
      this.viewState = ViewState.CARDS;
      localStorage.setItem('viewMode', 'CARDS');
    } else {
      this.viewState = ViewState.GRID;
      localStorage.setItem('viewMode', 'GRID');
    }
    this.viewStateEmitter.emit(this.viewState);
  }
}
