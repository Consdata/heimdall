module.exports = {
  name: 'heimdall-monitor-tracking-api',
  preset: '../../../../jest.config.js',
  coverageDirectory: '../../../../coverage/libs/heimdall/monitor-tracking/api',
  snapshotSerializers: [
    'jest-preset-angular/AngularSnapshotSerializer.js',
    'jest-preset-angular/HTMLCommentSerializer.js'
  ]
};
