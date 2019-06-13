module.exports = {
  name: 'home',
  preset: '../../jest.config.js',
  coverageDirectory: '../../coverage/apps/home/',
  snapshotSerializers: [
    'jest-preset-angular/AngularSnapshotSerializer.js',
    'jest-preset-angular/HTMLCommentSerializer.js'
  ]
};
