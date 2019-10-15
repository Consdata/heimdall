import {Component, Input} from '@angular/core';

@Component({
  selector: 'grid-cell',
  template: `
    <div class="grid-cell">
        <div class="grid-cell-main-text">{{mainText}}</div>
        <div class="grid-cell-major-description-text">{{majorDescription}}</div>
        <div class="grid-cell-minor-description-text">{{minorDescription}}</div>
    </div>
  `,
  styleUrls: [
    'grid-cell.scss'
  ]
})
export class GridCell {

  @Input() mainText: string;

  @Input() majorDescription: string;

  @Input() minorDescription: string;

  constructor() {
  }

}
