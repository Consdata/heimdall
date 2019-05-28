module.exports = {
  name: 'rest-api',
  preset: '../../jest.config.js',
  coverageDirectory: '../../coverage/libs/rest-api',
  snapshotSerializers: [
    'jest-preset-angular/AngularSnapshotSerializer.js',
    'jest-preset-angular/HTMLCommentSerializer.js'
  ]
};
