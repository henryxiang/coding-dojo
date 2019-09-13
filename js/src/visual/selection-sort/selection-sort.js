const swap = (arr, i, j) => {
  const t = arr[i];
  arr[i] = arr[j];
  arr[j] = t;
}

const findMinIndex = (arr, start, end) => {
  let minIndex = start, minValue = arr[start];
  for (let i = start; i < end; i++) {
    if (arr[i] < minValue) {
      minIndex = i;
      minValue = arr[i];
    }
  }
  return minIndex;
}

export const sort = (data, log) => {
  if (!log) log = () => null;

  for (let i = 0; i < data.length - 1; i++) {
    log({type: 'range', data: [i, data.length - 1]});

    const minIndex = findMinIndex(data, i, data.length);
    log({type: 'highlight', data: minIndex});

    swap(data, i, minIndex);
    log({type: 'swap', data: [i, minIndex]});
  }
}
