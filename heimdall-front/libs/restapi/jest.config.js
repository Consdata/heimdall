module.exports = {
  name: 'restapi',
  preset: '../../jest.config.js',
  coverageDirectory: '../../coverage/libs/restapi',
  snapshotSerializers: [
    'jest-preset-angular/AngularSnapshotSerializer.js',
    'jest-preset-angular/HTMLCommentSerializer.js'
  ]
};
