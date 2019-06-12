import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LibViewComponent } from './lib-view.component';

describe('LibViewComponent', () => {
  let component: LibViewComponent;
  let fixture: ComponentFixture<LibViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LibViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LibViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
