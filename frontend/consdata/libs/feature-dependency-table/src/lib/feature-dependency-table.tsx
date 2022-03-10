import styles from './feature-dependency-table.module.scss';
import {Column, useTable} from 'react-table';

export interface FeatureDepTableProps {
  readonly data: object[];
  readonly columns: Column<object>[];
}

export function FeatureDependencyTable({data, columns}: FeatureDepTableProps) {
  const {headerGroups, rows, prepareRow,} = useTable({columns, data})

  return (
    <table className={styles["heim-dependency-table"]}>
      <thead>
      {headerGroups.map(headerGroup => (
        <tr {...headerGroup.getHeaderGroupProps()}>
          {headerGroup.headers.map(column => (
            <th {...column.getHeaderProps()}>{column.render('Header')}</th>
          ))}
        </tr>
      ))}
      </thead>
      <tbody>
      {rows.map((row, i) => {
        prepareRow(row)
        return (
          <tr {...row.getRowProps()}>
            {row.cells.map(cell => {
              return <td {...cell.getCellProps()}>{cell.render('Cell')}</td>
            })}
          </tr>
        )
      })}
      </tbody>
    </table>
  );
}

export default FeatureDependencyTable;
