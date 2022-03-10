import styles from './feature-dependency-table-cell.module.scss';

export interface FeatureDepTableCellProps {
  version: string;
}

export function FeatureDependencyTableCell({version}: FeatureDepTableCellProps) {
  return (
    <div className={styles['heim-dependency-table-cell']}>
      <span>{version}</span>
    </div>
  );
}

export default FeatureDependencyTableCell;
