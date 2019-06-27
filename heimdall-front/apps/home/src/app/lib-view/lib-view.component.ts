import {Component, Input} from '@angular/core';
import {LibView} from '../lib-dashboard-view-services/lib-dash-board-view.service';

@Component({
  selector: 'library-view',
  template: `
    <div class="lib-container">
      <div class="library-info-container">
        <div class="lib-view-label-name">{{libView.name}}</div>
        <div class="lib-view-label-current-version">{{libView.currentVersion}}</div>
      </div>
      <project-libraries
        *ngFor="let project of libView.libs"
        [project]="project"
      ></project-libraries>
    </div>
  `,
})
export class LibViewComponent {

  @Input() libView: LibView;

  constructor() {
  }

}
