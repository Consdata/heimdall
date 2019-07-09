module.exports = {
  name: 'heimdall-monitor-tracking-rest',
  preset: '../../../../jest.config.js',
  coverageDirectory: '../../../../coverage/libs/heimdall/monitor-tracking/rest',
  snapshotSerializers: [
    'jest-preset-angular/AngularSnapshotSerializer.js',
    'jest-preset-angular/HTMLCommentSerializer.js'
  ]
};
