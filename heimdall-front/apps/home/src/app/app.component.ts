import {Component, OnInit} from '@angular/core';
import {RestApiService} from '../../../../libs/rest-api/src/lib/rest-api.service';

@Component({
  selector: 'heimdall-front-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  title = 'project Heimdall';
  info: string;

  constructor(private restApiService: RestApiService) {
  }

  ngOnInit(): void {
    this.restApiService.getInfo().subscribe(info => this.info = info);
  }

}
