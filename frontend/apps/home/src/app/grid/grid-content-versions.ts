import {Component, HostBinding, Input, OnInit} from '@angular/core';
import {DependencyEntity, ProjectEntity, VersionEntity} from './grid.service';

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
        [status]="version.status">
      </grid-cell-version>
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

  constructor() {
  }

  ngOnInit(): void {
    this.gridTemplateColumnProperty = this.calculateColumnGridSize(this.columns.length);
    this.gridTemplateRowProperty = this.calculateRowGridSize(this.rows.length);
  }

  calculateColumnGridSize(numberOfColumns: number): string {
    return `repeat(${numberOfColumns}, 160px)`;
  }

  calculateRowGridSize(numberOfRows: number): string {
    return `repeat(${numberOfRows}, 80px)`;
  }

  getColumnNumberBasedOnId(version: VersionEntity): number{
    return this.columns.map(y => y.projectId).indexOf(version.projectId) + 1;
  }

  getRowNumberBasedOnId(version: VersionEntity): number{
    return this.rows.map(y => y.dependencyId).indexOf(version.dependencyId) + 1;
  }

  getStatusTextBasedOnStatusEnum(version: VersionEntity): void{
  }
}
