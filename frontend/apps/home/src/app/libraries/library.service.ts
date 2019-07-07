import {Injectable} from '@angular/core';


export enum VersionStatus {
  VERYOLD, OLD, OK, GOOD
}

export interface ProjectView {
  name: string;
  projectVersion: string;
  status: VersionStatus;
}

export interface LibView {
  name: string;
  currentVersion: string;
  libs: ProjectView[]
}

@Injectable({
  providedIn: 'root'
})
export class LibraryService {


  private static TYPE_SCRIPT = 'typeScript';
  private static ANGULAR = 'Angular';
  private static SCSS = 'Scss';

  private static EXIMEE = 'EXIMEE';
  private static FORMSTORE = 'FORMSTORE';
  private static IBIZNES = 'IBIZNES';

  constructor() { }

  getLibsDashBoardView(): LibView[] {
    return [
      {
        name: LibraryService.TYPE_SCRIPT,
        currentVersion: '3.2.11',
        libs:[
          {name: LibraryService.EXIMEE, projectVersion: '2.10.11', status: VersionStatus.VERYOLD},
          {name: LibraryService.FORMSTORE, projectVersion: '3.1.16', status: VersionStatus.OLD},
          {name: LibraryService.IBIZNES, projectVersion: '3.2.11', status: VersionStatus.GOOD}]},
      {
        name: LibraryService.ANGULAR,
        currentVersion: '6.22.21',
        libs:[
          {name: LibraryService.EXIMEE, projectVersion: '6.22.11', status: VersionStatus.OK},
          {name: LibraryService.FORMSTORE, projectVersion: '6.12.21', status: VersionStatus.OLD},
          {name: LibraryService.IBIZNES, projectVersion: '6.24.21', status: VersionStatus.GOOD}]},
      {
        name: LibraryService.SCSS,
        currentVersion: '8.32.31',
        libs:[
          {name: LibraryService.EXIMEE, projectVersion: '8.33.31', status: VersionStatus.GOOD},
          {name: LibraryService.FORMSTORE, projectVersion: '9.32.31', status: VersionStatus.GOOD},
          {name: LibraryService.IBIZNES, projectVersion: '8.32.34', status: VersionStatus.GOOD}]}
    ];
  }

}
