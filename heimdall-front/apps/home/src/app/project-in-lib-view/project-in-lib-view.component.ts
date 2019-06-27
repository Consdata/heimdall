import {Component, Input} from '@angular/core';
import {ProjectView, VersionStatus} from "../lib-dashboard-view-services/lib-dash-board-view.service";

@Component({
  selector: 'project-libraries',
  template: `
    <div class="project-container">
      <div class="project-view_name">{{project.name}}</div>
      <div class="project-view_version" [ngClass]="getStatus(project.status)">{{project.projectVersion}}</div>
    </div>
  `
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
