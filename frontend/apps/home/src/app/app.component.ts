import {Component, OnInit} from '@angular/core';
import {RestApiService} from '../../../../libs/rest-api/src/lib/rest-api.service';

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

  constructor(private restApiService: RestApiService) {
  }

  filterValue(filter) {
    this.filter = filter;
  }

}
