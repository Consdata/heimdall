import {Component, HostBinding, Input, OnInit} from '@angular/core';
import {DependencyEntity, ProjectEntity, VersionEntity, VersionStatus} from './grid.service';

@Component({
  selector: 'grid-content-versions',
  template: `
    <div class="grid-content-container"
         [ngStyle]="{
         'grid-template-columns': gridTemplateColumnProperty,
         'grid-template-rows': gridTemplateRowProperty
         }">
      <grid-cell-version
        *ngFor="let version of this.versions"
        [column]="getColumnNumberBasedOnId(version)"
        [row]="getRowNumberBasedOnId(version)"
        [version]="version">
      </grid-cell-version>
      <ng-container
        *ngFor="let item of this.columns; let x = index" [attr.data-index]="x">
        <grid-cell-background
          *ngFor="let item of this.rows; let y = index" [attr.data-index]="y"
          [column]="x+1"
          [row]="y+1">
        </grid-cell-background>
      </ng-container>
    </div>
  `,
  styleUrls: [
    'grid-content-versions.scss'
  ]
})
export class GridContentVersions implements OnInit {

  @Input() columns: ProjectEntity[];

  @Input() rows: DependencyEntity[];

  @Input() versions: VersionEntity[];

  @HostBinding('style.grid-template-columns')
  @Input() public column: number;

  @HostBinding('style.grid')
  @Input() public row: number;

  gridTemplateColumnProperty: string;
  gridTemplateRowProperty: string;

  static calculateColumnGridSize(numberOfColumns: number): string {
    return `repeat(${numberOfColumns}, 160px)`;
  }

  static calculateRowGridSize(numberOfRows: number): string {
    return `repeat(${numberOfRows}, 80px)`;
  }

  ngOnInit(): void {
    this.gridTemplateColumnProperty = GridContentVersions.calculateColumnGridSize(this.columns.length);
    this.gridTemplateRowProperty = GridContentVersions.calculateRowGridSize(this.rows.length);
  }

  getColumnNumberBasedOnId(version: VersionEntity): number {
    return this.columns.map(y => y.projectId).indexOf(version.projectId) + 1;
  }

  getRowNumberBasedOnId(version: VersionEntity): number {
    return this.rows.map(y => y.dependencyId).indexOf(version.dependencyId) + 1;
  }
}
