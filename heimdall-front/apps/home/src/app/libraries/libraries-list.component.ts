import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {LibraryService, LibView} from './library.service';

@Component({
  selector: 'libraries-list',
  template: `
    <library
      class="heimdall-front-lib-view"
      *ngFor="let libView of libViews"
      [libView]="libView">
    </library>
  `
})
export class LibrariesListComponent implements OnInit, OnChanges {

  public libViews: LibView[];
  @Input() filterValue: string;

  constructor(private libDashBoardViewService: LibraryService) {
    this.libViews = libDashBoardViewService.getLibsDashBoardView();
  }

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.filterLibs(this.filterValue);
  }

  private filterLibs(searchValueFilter: string) {
    this.libViews = this.libDashBoardViewService.getLibsDashBoardView()
      .filter(value => value.name.toLocaleLowerCase().includes(searchValueFilter));
  }

}
