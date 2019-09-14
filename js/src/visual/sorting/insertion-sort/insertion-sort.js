const defaultLogger = (m) => console.log(m);

const swap = (arr, i, j, log = defaultLogger) => {
  const t = arr[i];
  arr[i] = arr[j];
  arr[j] = t;
  log({type: 'swap', data: [i, j]});
}

export const sort = (data, log = defaultLogger) => {
  for (let i = 1; i < data.length; i++) {
    log({type: 'range', data: [0, i]});
    for (let j = i; j > 0 && data[j] < data[j-1]; j--) {
      log({type: 'highlight', data: j});
      swap(data, j-1, j, log);
    }
    log({type: 'sorted',data: [0, i]});
  }
}
