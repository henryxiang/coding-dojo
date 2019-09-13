const swap = (arr, i, j) => {
  const t = arr[i];
  arr[i] = arr[j];
  arr[j] = t;
}

export const sort = (data, log) => {
  if (!log) log = () => null;

  for (let i = data.length - 1; i > 0; i--) {
    log({type: 'range', data: [0, i]});

    for (let j = 0; j < i; j++) {
      log({type: 'highlight', data: j});
      if (data[j] > data[j+1]) {
        swap(data, j, j+1);
        log({type: 'swap', data: [j, j+1]});
      }
    }

    log({type: 'bubbled', data: i});
  }
}
