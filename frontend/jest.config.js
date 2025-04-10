module.exports = {
  preset: 'jest-preset-angular',
  setupFilesAfterEnv: ['<rootDir>/src/setup-jest.ts'],
  testMatch:['**/+(*.)+(spec).+(ts)'],
  collectCoverage: true,
};
