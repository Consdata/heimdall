import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {ReportDto} from './report-dto';

@Injectable()
export class RestApiService {

  constructor(private httpClient: HttpClient) {
  }

  reports$(): Observable<ReportDto> {
    return this.httpClient.get<ReportDto>('/api/report-list/v1/report');
  }

}
