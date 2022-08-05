import {render} from '@testing-library/react';

import FeatureDepTable from './feature-dep-table';

describe('FeatureDepTable', () => {
  it('should render successfully', () => {
    const { baseElement } = render(<FeatureDepTable />);
    expect(baseElement).toBeTruthy();
  });
});
