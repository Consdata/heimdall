import {Component} from '@angular/core';

@Component({
  selector: 'hmd-root',
  template: `
    <navbar (filterValueEmitter)="filterValue($event)"></navbar>
    <libraries-list
      class="hmd-libs-dash-board"
      [filterValue]="filter">
    </libraries-list>
  `,
})
export class AppComponent {

  filter: string = '';

  filterValue(filter) {
    this.filter = filter;
  }

}
