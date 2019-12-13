import {ComponentRef, Directive, ElementRef, HostListener, Input, OnInit} from "@angular/core";
import {Overlay, OverlayPositionBuilder, OverlayRef} from "@angular/cdk/overlay";
import {ComponentPortal} from "@angular/cdk/portal";
import {TooltipComponent} from "./tooltip.component";

@Directive({selector: '[hmdTooltip]'})
export class TooltipDirective implements OnInit {

  @Input('hmdTooltip') text = '';
  private overlayRef: OverlayRef;
  private showTooltip: boolean;

  constructor(private overlayPositionBuilder: OverlayPositionBuilder,
              private elementRef: ElementRef,
              private overlay: Overlay) {
  }

  ngOnInit() {
    const positionStrategy = this.overlayPositionBuilder
      .flexibleConnectedTo(this.elementRef)
      .withPositions([{
        originX: 'center',
        originY: 'top',
        overlayX: 'center',
        overlayY: 'bottom'
      }]);
    this.overlayRef = this.overlay.create({positionStrategy});
  }

  @HostListener('mouseenter')
  show() {
    this.showTooltip = true;
    this.overlayRef.detach();
    setTimeout(() => {
      if (this.showTooltip) {
        const tooltipRef: ComponentRef<TooltipComponent> = this.overlayRef.attach(new ComponentPortal(TooltipComponent));
        tooltipRef.instance.text = this.text;
      }
    }, 800);
  }

  @HostListener('mouseout')
  hide() {
    this.showTooltip = false;
    this.overlayRef.detach();
  }
}
