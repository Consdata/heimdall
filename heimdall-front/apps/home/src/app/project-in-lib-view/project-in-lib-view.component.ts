import {Component, Input} from '@angular/core';
import {ProjectView, VersionStatus} from "../lib-dashboard-view-services/lib-dash-board-view.service";

@Component({
  selector: 'heimdall-front-project-in-lib-view',
  templateUrl: './project-in-lib-view.component.html',
  styleUrls: ['./project-in-lib-view.component.css']
})
export class ProjectInLibViewComponent {

  @Input() project: ProjectView;
  constructor() { }

  getStatus(libVersionStatus :VersionStatus) {
    if (libVersionStatus === VersionStatus.VERYOLD) {
      return 'veryold';
    } else if (libVersionStatus === VersionStatus.OLD) {
      return 'old';
    } else if (libVersionStatus === VersionStatus.OK) {
      return 'ok';
    }
    return 'good';
  }
}
