import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import {RestApiService} from './rest-api.service';

@NgModule({
  imports: [CommonModule,
    HttpClientModule],
  providers: [RestApiService]
})
export class RestApiModule {
}
