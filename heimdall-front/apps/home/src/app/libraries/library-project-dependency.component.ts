import {Component, Input} from '@angular/core';
import {ProjectView, VersionStatus} from './library.service';

@Component({
  selector: 'library-project-dependency',
  template: `
    <div class="project-container" [ngClass]="getStatus(project.status)">
      <div class="project-name">{{project.name}}</div>
      <div class="project-version">{{project.projectVersion}}</div>
    </div>
  `
})
export class LibraryProjectDependencyComponent {

  @Input() project: ProjectView;
  constructor() { }

  getStatus(libVersionStatus :VersionStatus) {
    if (libVersionStatus === VersionStatus.VERYOLD) {
      return 'project-version-status-very-old';
    } else if (libVersionStatus === VersionStatus.OLD) {
      return 'project-version-status-old';
    } else if (libVersionStatus === VersionStatus.OK) {
      return 'project-version-status-ok';
    }
    return 'project-version-status-good';
  }
}
