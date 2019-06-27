import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {LibDashBoardViewService, LibView} from "../lib-dashboard-view-services/lib-dash-board-view.service";

@Component({
  selector: 'heimdall-front-libs-dash-board',
  templateUrl: './libs-dash-board.component.html'
})
export class LibsDashBoardComponent implements OnInit, OnChanges {

  public libViews: LibView[];
  @Input() filterValue: string;
  constructor(private libDashBoardViewService: LibDashBoardViewService) {
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
