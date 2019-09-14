const defaultLogger = (m) => console.log(m);

const swap = (arr, i, j, log = defaultLogger) => {
  const t = arr[i];
  arr[i] = arr[j];
  arr[j] = t;
  log({type: 'swap', data: [i, j]});
}

const findMinIndex = (arr, start, end, log = defaultLogger) => {
  let minIndex = start, minValue = arr[start];
  for (let i = start; i < end; i++) {
    if (arr[i] < minValue) {
      minIndex = i;
      minValue = arr[i];
    }
  }
  log({type: 'highlight', data: minIndex});
  return minIndex;
}

export const sort = (data, log = defaultLogger) => {
  for (let i = 0; i < data.length - 1; i++) {
    log({type: 'range', data: [i, data.length - 1]});
    const minIndex = findMinIndex(data, i, data.length, log);
    swap(data, i, minIndex, log);
  }
}
