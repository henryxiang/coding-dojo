const defaultLogger = (m) => console.log(m);

const swap = (arr, i, j, log = defaultLogger) => {
  const t = arr[i];
  arr[i] = arr[j];
  arr[j] = t;
  log({type: 'swap', data: [i, j]});
}

const shuffle = (data) => {
  for (let i = 0; i < data.length-1; i++) {
    const n = data.length - i;
    const j = i + Math.floor(Math.random()*n);
    swap(data, i, j, () => null);
  }
}

const partition = (data, start, end, log = defaultLogger) => {
  const pivot = data[start];
  log({type: 'pivot', data: start});
  let l = start, h = end, i = start+1;
  while (i <= h) {
    log({type: 'chosen', data: i});
    if (data[i] < pivot) {
      swap(data, i, l, log);
      l += 1;
      i += 1;
    } else if (data[i] > pivot) {
      swap(data, i, h, log);
      h -= 1;
    } else {
      i += 1;
    }
  }
  const part = [start, l-1, h+1, end]
  log({type: 'partitioned', data: part});
  return part;
}

export const sort = (data, start, end, log = defaultLogger) => {
  if (start == undefined && end == undefined) {
    shuffle(data);
    start = 0;
    end = data.length - 1;
  }
  if (start >= end) return;
  log({type: 'range', data: [start, end]});
  const part = partition(data, start, end, log);
  sort(data, part[0], part[1], log);
  sort(data, part[2], part[3], log);
}
