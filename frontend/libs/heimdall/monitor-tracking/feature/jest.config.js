module.exports = {
  name: 'heimdall-monitor-tracking-feature',
  preset: '../../../../jest.config.js',
  coverageDirectory:
    '../../../../coverage/libs/heimdall/monitor-tracking/feature',
  snapshotSerializers: [
    'jest-preset-angular/AngularSnapshotSerializer.js',
    'jest-preset-angular/HTMLCommentSerializer.js'
  ]
};
