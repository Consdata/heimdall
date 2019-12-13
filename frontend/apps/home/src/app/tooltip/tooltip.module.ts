import {TooltipComponent} from "./tooltip.component";
import {TooltipDirective} from "./tooltip.directive";
import {OverlayModule} from "@angular/cdk/overlay";
import {NgModule} from "@angular/core";
import {OverlayStyleComponent} from "./overlay-style.component";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {BrowserModule} from "@angular/platform-browser";

@NgModule({
  declarations: [
    TooltipDirective,
    TooltipComponent,
    OverlayStyleComponent
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    OverlayModule
  ],
  providers: [],
  exports: [
    TooltipDirective
  ],
  entryComponents: [TooltipComponent]
})
export class TooltipModule {
}
