import { Component, OnInit } from '@angular/core';
import {LibDashBoardViewService, LibView} from "../lib-dashboard-view-services/lib-dash-board-view.service";

@Component({
  selector: 'heimdall-front-libs-dash-board',
  templateUrl: './libs-dash-board.component.html',
  styleUrls: ['./libs-dash-board.component.css']
})
export class LibsDashBoardComponent implements OnInit {

  public libViews: LibView[];

  constructor(private libDashBoardViewService: LibDashBoardViewService) {
    this.libViews = libDashBoardViewService.getLibsDashBoardView();
  }

  ngOnInit() {
  }

}
