import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {Observable} from 'rxjs';
import {LibraryService, LibView} from './library.service';

@Component({
  selector: 'libraries-list',
  template: `
      <library
              class="hmd-lib-view"
              *ngFor="let libView of libs$ | async"
              [libView]="libView">
      </library>
  `
})
export class LibrariesListComponent implements OnInit, OnChanges {

  public libs$: Observable<LibView[]>;
  @Input() filterValue: string;

  constructor(private libDashBoardViewService: LibraryService) {
  }

  ngOnInit() {
    this.libs$ = this.libDashBoardViewService.getLibsDashBoardView();
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes.filterValue && !changes.filterValue.isFirstChange()) {
      this.filterLibs(this.filterValue);
    }
  }

  private filterLibs(searchValueFilter: string) {
    // this.libs$ = this.libDashBoardViewService.getLibsDashBoardView()
    //   .filter(value => value.name.toLocaleLowerCase().includes(searchValueFilter));
  }

}
