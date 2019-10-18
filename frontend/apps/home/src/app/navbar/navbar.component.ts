import {Component, EventEmitter, Output, ViewChild} from '@angular/core';
import {ViewState, ViewSwitch} from './view-switch';

@Component({
  selector: 'navbar',
  template: `
    <div class="navbar-wrapper">
      <div class="navbar-logo-wrapper">
        <div class="navbar-logo"></div>
        <div class="navbar-logo-label">Heimdall</div>
      </div>
      <div class="navbar-search-wrapper">
        <view-switch
          (viewStateEmitter)="changeView($event)"></view-switch>
        <input class="navbar-search" (keyup)="onKey($event)">
      </div>
    </div>
  `,
  styleUrls: [
    'navbar.component.scss'
  ]
})
export class NavbarComponent {

  @Output() filterValueEmitter = new EventEmitter<string>();
  @Output() viewStateEmitter = new EventEmitter<ViewState>();

  onKey(event: any): void {
    this.filterValueEmitter.emit(event.target.value.toLocaleLowerCase());
  }

  changeView(event: ViewState): void {
    this.viewStateEmitter.emit(event);
  }
}
