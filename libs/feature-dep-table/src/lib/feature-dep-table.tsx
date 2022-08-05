import {createColumnHelper, flexRender, getCoreRowModel, useReactTable} from '@tanstack/react-table';
import {useState} from 'react';
import {Dependency} from './Dependency';
import {Projects} from './Projects';

/* eslint-disable-next-line */
export interface FeatureDepTableProps {
}

const columnHelper = createColumnHelper<Dependency>();

const defaultData: Dependency[] = [
  {
    name: 'Angular',
    newestVersion: '14.5.0',
    projectVersions: {
      [Projects.EXIMEE]: '12.0.0',
      [Projects.POCZTA]: '14.0.0',
      [Projects.IB]: '9.0.0'
    }
  },
  {
    name: 'RxJs',
    newestVersion: '6.5.0',
    projectVersions: {
      [Projects.EXIMEE]: '6.0.0',
      [Projects.POCZTA]: '6.0.0',
      [Projects.IB]: '6.0.0'
    }
  }
]

const columns = [
  columnHelper.accessor(row => row.name, {
    id: " "
  }),
  columnHelper.accessor(row => row.projectVersions.iBiznes, {
    id: Projects.IB
  }),
  columnHelper.accessor(row => row.projectVersions.Eximee, {
    id: Projects.EXIMEE
  }),
  columnHelper.accessor(row => row.projectVersions.Poczta, {
    id: Projects.POCZTA
  })
]

export function FeatureDepTable(props: FeatureDepTableProps) {
  const [data] = useState([...defaultData]);

  const table = useReactTable({
    data,
    columns,
    getCoreRowModel: getCoreRowModel()
  });

  return (
    <div className="p-2">
      <table>
        <thead>
        {table.getHeaderGroups().map(headerGroup => (
          <tr key={headerGroup.id}>
            {headerGroup.headers.map(header => (
              <th key={header.id}>
                {!header.isPlaceholder && flexRender(header.column.columnDef.header, header.getContext())}
              </th>
            ))}
          </tr>
        ))}
        </thead>
        <tbody>
        {table.getRowModel().rows.map(row => (
          <tr key={row.id}>
            {row.getVisibleCells().map(cell => (
              <td key={cell.id}>
                {flexRender(cell.column.columnDef.cell, cell.getContext())}
              </td>
            ))}
          </tr>
        ))}
        </tbody>
      </table>
      <div className="h-4" />
    </div>
  );
}

export default FeatureDepTable;
