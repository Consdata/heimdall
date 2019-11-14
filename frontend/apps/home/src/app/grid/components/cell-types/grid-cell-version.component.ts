import {Component, HostBinding, Input, OnInit} from '@angular/core';
import {VersionEntity, VersionStatus} from '../../services/grid.models';

@Component({
  selector: 'grid-cell-version',
  template: `
    <div class="grid-cell-version" [ngClass]="versionClass">
      <div class="grid-cell-status">{{versionNumber}}</div>
    </div>
  `,
  styleUrls: [
    'grid-cell-version.component.scss'
  ]
})
export class GridCellVersionComponent implements OnInit {

  @HostBinding('style.grid-column-start')
  @Input() public column: number;

  @HostBinding('style.grid-row-start')
  @Input() public row: number;

  @Input() public version: VersionEntity;

  versionNumber: string;
  versionText: string;
  versionClass: string;

  ngOnInit(): void {
    this.setStatusInfo();
    this.concatVersionText();
  }

  setStatusInfo(): void {
    if (this.version.status === VersionStatus.VERY_OLD) {
      this.versionClass = 'project-version-status-very-old';
      this.versionText = 'Very old';
    } else if (this.version.status === VersionStatus.OLD) {
      this.versionClass = 'project-version-status-old';
      this.versionText = 'Old';
    } else if (this.version.status === VersionStatus.OK) {
      this.versionClass = 'project-version-status-ok';
      this.versionText = 'Ok';
    } else {
      this.versionClass = 'project-version-status-good';
      this.versionText = 'Good';
    }
  }

  private concatVersionText() {
    this.versionNumber = `${this.version.versionMajor}.${this.version.versionMinor}.${this.version.versionPatch}`
  }
}
