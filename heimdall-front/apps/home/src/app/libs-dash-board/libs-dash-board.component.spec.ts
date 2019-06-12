import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LibsDashBoardComponent } from './libs-dash-board.component';

describe('LibsDashBoardComponent', () => {
  let component: LibsDashBoardComponent;
  let fixture: ComponentFixture<LibsDashBoardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LibsDashBoardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LibsDashBoardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
