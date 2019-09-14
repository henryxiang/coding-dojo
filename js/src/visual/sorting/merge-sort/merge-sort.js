const defaultLogger = (m) => console.log(m);

const merge = (data, start, end, mid, log = defaultLogger) => {
  const temp = [];
  log({type: 'copy', data: [start, end, mid]});
  for (let i = start; i <= end; i++) {
    temp[i] = data[i];
  }
  let i = start, j = mid+1;
  for (let k = start; k <= end; k++) {
    if ((i <= mid && temp[i] <= temp[j]) || j > end) {
      log({type: 'merge', data: [i, k]});
      data[k] = temp[i++];
    } else {
      log({type: 'merge', data: [j, k]});
      data[k] = temp[j++];
    }
  }
}

export const sort = (data, start = 0, end = data.length-1, log = defaultLogger) => {
  if (start >= end) return;
  const mid = Math.floor((start + end) / 2);
  // log({type: 'range', data: [start, end]});
  sort(data, start, mid, log);
  sort(data, mid+1, end, log);
  merge(data, start, end, mid, log);
  log({type: 'sorted', data: [start, end]});
}
