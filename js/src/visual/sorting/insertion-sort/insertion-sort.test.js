import { sort } from './insertion-sort';
import { shuffle, range } from 'lodash';

test('it sorts an integer array', () => {
  const sorted = range(10);
  const data = shuffle(sorted)
  sort(data);
  expect(data).toEqual(sorted);
})
