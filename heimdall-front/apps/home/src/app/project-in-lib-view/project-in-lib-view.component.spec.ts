import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectInLibViewComponent } from './project-in-lib-view.component';

describe('ProjectInLibViewComponent', () => {
  let component: ProjectInLibViewComponent;
  let fixture: ComponentFixture<ProjectInLibViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProjectInLibViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProjectInLibViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
