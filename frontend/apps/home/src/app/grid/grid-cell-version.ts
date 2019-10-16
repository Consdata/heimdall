import {Component, HostBinding, Input, OnChanges, OnInit} from '@angular/core';
import {VersionStatus} from './grid.service';

@Component({
  selector: 'grid-cell-version',
  template: `
    <div class="grid-cell-version">
      <div class="grid-cell-status">{{status}}</div>
    </div>
  `,
  styleUrls: [
    'grid-cell-version.scss'
  ]
})
export class GridCellVersion implements OnInit {

  @HostBinding('style.grid-column-start')
  @Input() public column: number;

  @HostBinding('style.grid-row-start')
  @Input() public row: number;

  @Input() public status: number;

  statusText: string;

  ngOnInit(): void {
    console.log(status);
    console.log(VersionStatus[status]);
    this.statusText = VersionStatus[status];
  }
}
