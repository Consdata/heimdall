import {Component, EventEmitter, Output} from '@angular/core';

@Component({
  selector: 'navbar',
  template: `
    <div class="navbar-wrapper">
      <div class="navbar-logo-wrapper">
        <div class="navbar-logo"></div>
        <div class="navbar-logo-label">Heimdall</div>
      </div>
      <div class="navbar-search-wrapper">
        <input class="navbar-search" (keyup)="onKey($event)">
      </div>
    </div>
  `,
})
export class NavbarComponent {
  @Output() filterValueEmitter = new EventEmitter<string>();

  onKey(event: any): void {
    this.filterValueEmitter.emit(event.target.value.toLocaleLowerCase());
  }

}
