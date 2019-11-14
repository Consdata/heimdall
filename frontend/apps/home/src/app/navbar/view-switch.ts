import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ViewState, ViewStateService} from './view-state.service';


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
export class ViewSwitch implements OnInit {
  @Input() viewState: ViewState = null;

  constructor(private viewStateService: ViewStateService) {}

  ngOnInit() {
    this.viewStateService.currentState.subscribe(viewState => this.viewState = viewState)
  }

  changeView(event: any): void {
    if (event.target.checked === true) {
      this.viewStateService.changeStateToCardsView()
    } else {
      this.viewStateService.changeStateToGridView()
    }
  }
}
