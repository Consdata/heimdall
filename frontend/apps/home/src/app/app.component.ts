import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {ReportDto, ReportItemDto} from '../../../../libs/rest-api/src/lib/report-dto';
import {RestApiService} from '../../../../libs/rest-api/src/lib/rest-api.service';
import {map} from "rxjs/operators";

@Component({
  selector: 'heimdall-front-root',
  template: `
      <div *ngFor="let report of reports$ | async">
          <div>
              {{report | json}}
          </div>
      </div>
  `,
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  reports$: Observable<ReportItemDto[]>;

  constructor(private restApiService: RestApiService) {
  }

  ngOnInit(): void {
    this.reports$ = this.restApiService.reports$().pipe(
      map(report => report.items)
    )
  }

}
