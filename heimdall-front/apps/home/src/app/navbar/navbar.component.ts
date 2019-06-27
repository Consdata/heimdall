import {Component, EventEmitter, Output} from '@angular/core';
import {Subject} from 'rxjs';

@Component({
  selector: 'navbar',
  template: `
    <div class="navbar-wrapper">
      <div class="navbar-logo"></div>
      <div class="navbar-text">Heinmdall</div>
      <input class="navbar-search" (keyup)="onKey($event)">
    </div>
  `,
})
export class NavbarComponent {
  @Output() filterValueEmitter = new EventEmitter<string>();

  onKey(event: any): void {
    this.filterValueEmitter.emit(event.target.value.toLocaleLowerCase());
  }

}
