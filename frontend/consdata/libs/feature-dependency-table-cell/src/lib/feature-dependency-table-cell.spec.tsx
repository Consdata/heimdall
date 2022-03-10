import { render } from '@testing-library/react';

import FeatureDependencyTableCell from './feature-dependency-table-cell';

describe('FeatureDependencyTableCell', () => {
  it('should render successfully', () => {
    const { baseElement } = render(<FeatureDependencyTableCell />);
    expect(baseElement).toBeTruthy();
  });
});
