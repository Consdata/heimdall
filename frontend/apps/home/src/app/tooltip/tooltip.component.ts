import {Component, Input} from "@angular/core";
import {animate, style, transition, trigger} from "@angular/animations";

@Component({
  selector: 'hmd-tooltip',
  template: `
    <hmd-overlay-styles></hmd-overlay-styles>
    <div @tooltip>{{ text }}</div>
  `,
  styleUrls: ["tooltip.component.scss"],
  animations: [
    trigger('tooltip', [
      transition(':enter', [
        style({opacity: 0}),
        animate(400, style({opacity: 1})),
      ]),
      transition(':leave', [
        animate(400, style({opacity: 0})),
      ]),
    ]),
  ]
})
export class TooltipComponent {
  @Input() text = '';
}
