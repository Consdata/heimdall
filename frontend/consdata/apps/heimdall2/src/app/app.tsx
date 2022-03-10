import {FeatureDependencyTable} from '@consdata/feature-dependency-table';
import {FeatureDependencyTableCell, FeatureDepTableCellProps} from '@consdata/feature-dependency-table-cell';
import React from 'react';
import {CellProps} from 'react-table';

export function App() {
  const dummyWatchedLibraries = [
    'Angular',
    'Nx',
    'AssertJ',
    'Jest'
  ];

  const dummyColumns = React.useMemo(
    () => [
      {Header: '', accessor: 'lib'},
      {
        Header: 'iBiznes 1.0',
        accessor: 'ibiznes1',
        Cell: (cellProps: CellProps<FeatureDepTableCellProps>) => <FeatureDependencyTableCell version={cellProps.value}/>
      },
      {
        Header: 'iBiznes 2.0',
        accessor: 'ibiznes2',
        Cell: (cellProps: CellProps<FeatureDepTableCellProps>) => <FeatureDependencyTableCell version={cellProps.value}/>
      },
      {
        Header: 'Poczta',
        accessor: 'poczta',
        Cell: (cellProps: CellProps<FeatureDepTableCellProps>) => <FeatureDependencyTableCell version={cellProps.value}/>
      },
      {
        Header: 'Eximee',
        accessor: 'eximee',
        Cell: (cellProps: CellProps<FeatureDepTableCellProps>) => <FeatureDependencyTableCell version={cellProps.value}/>
      },
    ],
    []
  )
  const createDummyData = (): object[] => {
    return dummyWatchedLibraries.map(library => ({
      lib: library,
      ibiznes1: "Dummy",
      ibiznes2: "Dummy2",
      poczta: "Dummy3",
      eximee: "Dummy4"
    }));
  };

  const dummyData = React.useMemo(() => createDummyData(), []);

  return (
    <FeatureDependencyTable columns={dummyColumns} data={dummyData} />
  );
}

export default App;
