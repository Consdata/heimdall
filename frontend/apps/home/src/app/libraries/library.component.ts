import {Component, Input} from '@angular/core';
import {LibView} from './library.service';

@Component({
  selector: 'library',
  template: `
    <div class="lib-container">
      <div class="library-info-container">
        <div class="library-info-name">{{libView.name}}</div>
        <div class="library-info-current-version">{{libView.currentVersion}}</div>
      </div>
      <library-project-dependency
        *ngFor="let project of libView.libs"
        [project]="project">
      </library-project-dependency>
    </div>
  `,
  styleUrls: [
    'library.component.scss'
  ]
})
export class LibraryComponent {

  @Input() libView: LibView;

  constructor() {
  }

}
