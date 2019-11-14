import {Component, HostBinding, Input, OnChanges, OnInit} from '@angular/core';
import {VersionStatus} from '../../services/grid.service';

@Component({
  selector: 'grid-cell-background',
  template: `
    <div class="grid-cell-background">
      <div class="grid-cell-background-item"></div>
    </div>
  `,
  styleUrls: [
    'grid-cell-background.component.scss'
  ]
})
export class GridCellBackgroundComponent {

  @HostBinding('style.grid-column-start')
  @Input() public column: number;

  @HostBinding('style.grid-row-start')
  @Input() public row: number;

}
