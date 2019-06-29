import {Component, OnInit} from '@angular/core';
import {RestApiService} from '../../../../libs/rest-api/src/lib/rest-api.service';

@Component({
  selector: 'heimdall-front-root',
  template: `
    <navbar (filterValueEmitter)="filterValue($event)"></navbar>
    <libraries-list
      class=\"heimdall-front-libs-dash-board\"
      [filterValue]="filter">
    </libraries-list>
  `,
})
export class AppComponent implements OnInit {

  title = '';
  filter: string = '';

  constructor(private restApiService: RestApiService) {
  }

  ngOnInit(): void {
    this.restApiService.getInfo()
      .subscribe(info => {
        this.title = info
      });
  }

  filterValue(filter) {
    this.filter = filter;
  }

}
