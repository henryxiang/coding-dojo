function findMinItemIndex(input, start, end, comparator) {
  if (!Array.isArray(input)) throw "Input is not an array.";
  if (start < 0 || end > input.length) throw "Index out of array bound.";
  if (start > end) throw `Invalid range indices: ${start}..${end}`;
  if (start === end - 1) return start;
  let minIndex = start, minValue = input[start];
  if (!comparator) {
    comparator = function(a, b) {
      if (a > b) return 1;
      else if (a < b) return -1;
      else return 0;
    }
  }
  for (let i = start; i < end; i += 1) {
    if (comparator(input[i], minValue) < 0) {
      minIndex = i;
      minValue = input[i];
    }
  }
  return minIndex;
}

function swap(input, i, j) {
  const t = input[i];
  input[i] = input[j];
  input[j] = t;
}

module.exports = function(input, comparator) {
  if (!Array.isArray(input)) throw "Input is not an array.";
  if (input.length === 0) return [];
  if (input.length === 1) return [input[0]];
  const results = [...input];
  for (let i = 0; i < input.length; i += 1) {
    const minIndex = findMinItemIndex(results, i, results.length, comparator);
    swap(results, i, minIndex);
  }
  return results;
}
