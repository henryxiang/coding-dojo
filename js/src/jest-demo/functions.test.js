/**
 * Jest tests
 */

 const functions = require('./functions');

 test('2 + 2 == 4', () => {
   expect(functions.add(2, 2)).toBe(4);
 });

 test('2 + 2 != 5', () => {
   expect(functions.add(2, 2)).not.toBe(5);
 });
