import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable()
export class RestApiService {

  constructor(private httpClient: HttpClient) {
  }

  getInfo(): Observable<string> {
    return this.httpClient.get<string>('/api/info');
  }

}
