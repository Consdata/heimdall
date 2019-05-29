import {async, fakeAsync, TestBed, tick} from '@angular/core/testing';
import {AppComponent} from './app.component';
import {RouterTestingModule} from '@angular/router/testing';
import {RestApiService} from '../../../../libs/rest-api/src/lib/rest-api.service';
import {Observable, of} from 'rxjs';

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule],
      declarations: [AppComponent],
      providers: [
        {provide: RestApiService, useClass: RestApiServiceMock}
      ]
    }).compileComponents();
  }));

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });


  it(`should have as title 'Test'`, fakeAsync(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    tick();
    fixture.detectChanges();
    expect(app.title).toEqual('Test 1 2 3');
  }));

  it('should render title in a h1 tag', fakeAsync(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const compiled = fixture.debugElement.nativeElement;
    tick();
    fixture.detectChanges();
    expect(compiled.querySelector('h1').textContent).toContain(
      'Welcome to Test 1 2 3!'
    );
  }));

});

class RestApiServiceMock implements Partial<RestApiService> {

  getInfo(): Observable<string> {
    return of('Test 1 2 3');
  }
}
