const swap = (arr, i, j) => {
  const t = arr[i];
  arr[i] = arr[j];
  arr[j] = t;
}

export const sort = (data, log) => {
  if (!log) log = () => null;

  for (let i = 1; i < data.length; i++) {
    log({type: 'range', data: [0, i]});

    let j = i;
    log({type: 'highlight', data: j});
    while (j > 0 && data[j] < data[j-1]) {
      swap(data, j-1, j);
      log({type: 'swap', data: [j-1, j]});
      j -= 1;
    }
    
    log({type: 'sorted',data: [0, i]});
  }
}
