import {Component, Input} from '@angular/core';

@Component({
  selector: 'grid-cell-text',
  template: `
    <div class="grid-cell" [ngClass]="typeClass">
        <div class="grid-cell-text grid-cell-main-text">{{mainText}}</div>
        <div class="grid-cell-text grid-cell-major-description-text">{{majorDescription}}</div>
        <div class="grid-cell-text grid-cell-minor-description-text">{{minorDescription}}</div>
    </div>
  `,
  styleUrls: [
    'grid-cell-text.component.scss'
  ]
})
export class GridCellTextComponent {

  @Input() mainText: string;

  @Input() majorDescription: string;

  @Input() minorDescription: string;

  @Input() typeClass: string;

}
