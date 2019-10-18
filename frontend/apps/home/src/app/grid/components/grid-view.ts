import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {GridService, GridViewEntity} from '../services/grid.service';

@Component({
  selector: 'grid-view',
  template: `
    <div class="grid-container">
      <div class="grid-top-row">
        <grid-cell-text></grid-cell-text>
        <grid-cell-text
          *ngFor="let libView of gridView$.projectEntities"
          [mainText]='libView.projectArtifact'
          [majorDescription]='libView.projectGroup'
          [minorDescription]='gridService.artifactVersion(libView)'>
        </grid-cell-text>
      </div>
      <div class="grid-center">
        <div class="grid-center-column">
          <grid-cell-text
            *ngFor="let libView of gridView$.dependencyEntities"
            [mainText]='libView.dependencyArtifact'
            [majorDescription]='libView.dependencyGroup'
            [minorDescription]='gridService.artifactVersion(libView)'>
          </grid-cell-text>
        </div>
        <div class="grid-center-version">
          <grid-content-versions 
            [columns]="gridView$.projectEntities"
            [rows]="gridView$.dependencyEntities"
            [versions]="gridView$.versionEntities">
          </grid-content-versions>
        </div>
      </div>
    </div>
  `,
  styleUrls: [
    'grid-view.scss'
  ]
})
export class GridView implements OnInit {

  public gridView$: GridViewEntity;

  constructor(private gridService: GridService) {
  }

  ngOnInit() {
    this.gridView$ = this.gridService.getLibsGridView();
  }

}
