import {Component} from '@angular/core';

@Component({
  selector: 'libraries-panel',
  template: `
    <div class="navbar-wrapper">
      <div class="navbar-logo"></div>
      <div class="navbar-text">Heinmdall</div>
    </div>
  `,
})
export class LibrariesPanelComponent {

  mockedAppLibraries: AppLibraries[] = [
    {
      applicationName: 'MockedApplicationName1',
      libraries: [{
        libraryName: 'MockedLibrary1',
        libraryVersion: '1.2'
      },{
        libraryName: 'MockedLibrary2',
        libraryVersion: '1.3'
      },{
        libraryName: 'MockedLibrary3',
        libraryVersion: '1.5'
      },
      ]
    },
    {
      applicationName: 'MockedApplicationName2',
      libraries: [{
        libraryName: 'MockedLibrary1',
        libraryVersion: '1.1'
      },{
        libraryName: 'MockedLibrary3',
        libraryVersion: '1.4'
      },{
        libraryName: 'MockedLibrary4',
        libraryVersion: '1.2'
      },
      ]
    }
  ];

}
