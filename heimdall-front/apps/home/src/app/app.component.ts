import {Component, OnInit} from '@angular/core';
import {RestApiService} from '../../../../libs/rest-api/src/lib/rest-api.service';

@Component({
  selector: 'heimdall-front-root',
  template: `
    <navbar></navbar>
  `,
})
export class AppComponent implements OnInit {

  title = '';

  constructor(private restApiService: RestApiService) {
  }

  ngOnInit(): void {
    this.restApiService.getInfo()
      .subscribe(info => {
        this.title = info
      });
  }

}
