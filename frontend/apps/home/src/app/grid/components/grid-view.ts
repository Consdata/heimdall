import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {DependencyEntity, GridService, GridViewEntity, ProjectEntity, VersionStatus} from '../services/grid.service';

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
          [minorDescription]='gridService.artifactVersion(libView)'
          [typeClass]="'grid-cell-text-columns'"
          (click)="sortByProject(libView)">
        </grid-cell-text>
      </div>
      <div class="grid-center">
        <div class="grid-center-column">
          <grid-cell-text
            *ngFor="let libView of gridView$.dependencyEntities"
            [mainText]='libView.dependencyArtifact'
            [majorDescription]='libView.dependencyGroup'
            [minorDescription]='gridService.artifactVersion(libView)'
            [typeClass]="'grid-cell-text-rows'"
            (click)="sortByDependency(libView)">
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

  sortByProject(projectEntity: ProjectEntity): void {
    let dependenciesList = this.gridView$.dependencyEntities;
    let versionList = this.gridView$.versionEntities;
    let usedDependencies = versionList.filter(dependency => dependency.projectId === projectEntity.projectId);
    usedDependencies.sort((a, b) => b.status - a.status);
    usedDependencies.forEach(
      dependency => this.pushElementOnListHead(dependenciesList, dependenciesList.map(e => e.dependencyId).indexOf(dependency.dependencyId))
    );
  }

  sortByDependency(dependencyEntity: DependencyEntity): void {
    let projectList = this.gridView$.projectEntities;
    let versionList = this.gridView$.versionEntities;
    let usedProjects = versionList.filter(project => project.dependencyId === dependencyEntity.dependencyId);
    usedProjects.sort((a, b) => b.status - a.status);
    usedProjects.forEach(
      project => this.pushElementOnListHead(projectList, projectList.map(e => e.projectId).indexOf(project.projectId))
    );
  }

  pushElementOnListHead(list, oldIndex) {
    if (0 >= list.length) {
      let k = 0 - list.length + 1;
      while (k--) {
        list.push(undefined);
      }
    }
    list.splice(0, 0, list.splice(oldIndex, 1)[0]);
  };
}
