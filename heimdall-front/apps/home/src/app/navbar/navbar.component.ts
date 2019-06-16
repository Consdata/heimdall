import {Component, EventEmitter, Output} from '@angular/core';
import {Subject} from 'rxjs';
import 'rxjs-compat/add/observable/interval';

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
  @Output() filterValueEmitter = new EventEmitter();

  onKey(event: any): void {
    var value = event.target.value;
    this.filterValueEmitter.emit(value);
  }

}
