import {Component, EventEmitter, Input, Output} from '@angular/core';
import {ViewState} from './view-switch';

@Component({
  selector: 'navbar',
  template: `
    <div class="navbar-wrapper">
      <div class="navbar-logo-wrapper">
        <div class="navbar-logo"></div>
        <div class="navbar-logo-label">Heimdall</div>
      </div>
<!--      <div class="navbar-search-wrapper">-->
<!--          <input class="navbar-search" (keyup)="onKey($event)">-->
<!--      </div>-->
      <div class="navbar-switch-wrapper">
        <view-switch (viewStateEmitter)="changeView($event)" [viewState]="currentViewState"></view-switch>
      </div>
    </div>
  `,
  styleUrls: [
    'navbar.component.scss'
  ]
})
export class NavbarComponent {
  @Input() currentViewState: ViewState = null;
  @Output() filterValueEmitter = new EventEmitter<string>();
  @Output() viewStateEmitter = new EventEmitter<ViewState>();

  onKey(event: any): void {
    this.filterValueEmitter.emit(event.target.value.toLocaleLowerCase());
  }

  changeView(event: ViewState): void {
    this.viewStateEmitter.emit(event);
  }
}
