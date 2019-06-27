import {Component, Input} from '@angular/core';
import {LibView} from "../lib-dashboard-view-services/lib-dash-board-view.service";

@Component({
  selector: 'heimdall-front-lib-view',
  templateUrl: './lib-view.component.html',
  styleUrls: ['./lib-view.component.css']
})
export class LibViewComponent {

  @Input() libView: LibView;

  constructor() { }

}
