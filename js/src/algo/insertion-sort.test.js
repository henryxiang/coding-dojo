const _ = require('lodash');
const sort = require('./insertion-sort');

test('sorts an integer array', () => {
  const a = _.shuffle(_.range(10));
  expect(sort(a)).toEqual(_.sortBy(a));
});