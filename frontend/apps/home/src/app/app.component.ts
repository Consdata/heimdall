import {Component} from '@angular/core';

@Component({
  selector: 'hmd-root',
  template: `
    <navbar (filterValueEmitter)="filterValue($event)"></navbar>
<!--    <libraries-list [filterValue]="filter">-->
<!--    </libraries-list>-->
    <grid-view></grid-view>
  `,
  styleUrls: [
    'app.component.scss'
  ]
})
export class AppComponent {

  filter: string = '';

  filterValue(filter) {
    this.filter = filter;
  }

}
