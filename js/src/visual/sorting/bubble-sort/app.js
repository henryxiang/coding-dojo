import 'babel-polyfill';
import {shuffle, range, random} from 'lodash';
import Raphael from 'raphael';
import katex from 'katex';
import 'katex/dist/katex.min.css';
import Node, { swap } from '../../graph/graph-node';
import { sort } from './bubble-sort';

const canvas = document.getElementById('canvas');
const canvasSize = { width: canvas.clientWidth, height: canvas.clientHeight };
const replayInterval = 1800;
const appContext = {
  paper: Raphael(canvas, canvasSize.width, canvasSize.height),
  data: null,
  nodes: null,
  commands: null,
  step: 0,
  buttons: null,
};

main();

function main() {
  init();
  configButtons();
  sortData();
}

function init() {
  appContext.paper.clear();
  appContext.nodes = [];

  const start = random(0, 90);
  appContext.data = shuffle(range(start, start+10));

  const { paper, nodes, data } = appContext;
  const seg = Math.floor(canvasSize.width / (data.length + 1));
  for (let i = 0; i < data.length; i++) {
    const x = seg + seg*i;
    const y = Math.floor(canvasSize.height / 2);
    const node = new Node(data[i], x, y);
    node.draw(paper);
    nodes[i] = node;
    paper.text(x, y + node.r + 10, i);
  }
  document.getElementById('title').innerHTML = 'Bubble Sort ' +
    katex.renderToString("(O(n^2))");
}

function sortData() {
  const { data } = appContext;
  const commands = [];
  sort(data, (cmd) => commands.push(cmd))
  commands.push({ type: 'clear' });
  appContext.commands = commands;
  appContext.step = 0;
}

function configButtons() {
  const auto = document.getElementById('auto');
  const next = document.getElementById('next');
  const reset = document.getElementById('reset');
  auto.onclick = onAutoPlay;
  next.onclick = onNextStep;
  reset.onclick = onReset;
  appContext.buttons = { auto, next, reset };
}

function autoPlay() {
  const { commands } = appContext;
  const tasks = []
  let timeline = 0;
  for (let i = 0; i < commands.length; i++) {
    // timeline += replayInterval;
    const task = setTimeout(() => {
      runCommand(commands[i]);
    }, timeline);
    timeline += replayInterval;
    tasks.push(task);
  }
  appContext.tasks = tasks;
}

function runCommand(command) {
  switch(command.type) {
    case 'swap':
      swapNodes(...command.data);
      displayMessage(`item ${command.data[0]} is larger; swap it with item ${command.data[1]}`);
      break;
    case 'highlight':
      highlightNode(command.data);
      highlightNode(command.data + 1);
      displayMessage(`comparing item ${command.data} with item ${command.data + 1}`);
      break;
    case 'range':
      underlineRange(...command.data);
      displayMessage(`considering range from ${command.data[0]} to ${command.data[1]}`);
      break;
    case 'bubbled':
      selectNode(command.data);
      displayMessage(`item ${command.data} is the maximum from 0 to ${command.data}`);
      break;
    case 'clear':
      appContext.range.remove();
      appContext.nodes.forEach(n => n.circle.attr({ 'stroke': 'black', 'stroke-width': 1 }));
      displayMessage('finished sorting');
      break;
    default:
      break;
  }
}

function swapNodes(i, j) {
  const { nodes } = appContext;
  const n1 = nodes[i];
  const n2 = nodes[j];
  swap(n1, n2);
  nodes[i] = n2;
  nodes[j] = n1
}

function highlightNode(i) {
  const node = appContext.nodes[i];
  node.highlight();
}

function underlineRange(i, j) {
  const { paper, data } = appContext;
  const seg = Math.floor(canvasSize.width / (data.length + 1));
  const x = seg + seg*i - 10;
  const y = Math.floor(canvasSize.height / 2) + 40;
  const length = (j - i) * seg + 20;
  if (appContext.range) {
    appContext.range.remove();
  }
  appContext.range = paper
    .path(`M ${x} ${y} l ${length} 0 M ${x} ${y} l 0 -8 M ${x+length} ${y} l 0 -8`)
    .attr({ 'stroke': 'red', 'stroke-width': 3 });
}

function selectNode(i) {
  const { nodes } = appContext;
  nodes[i].circle.attr({ 'stroke': 'red', 'stroke-width': 3 });
}

function displayMessage(message) {
  document.getElementById('info').innerHTML = `<span>${message}</span>`;
}

function onAutoPlay() {
  const { auto, next, reset } = appContext.buttons;
  auto.disabled = true;
  next.disabled = true;
  reset.disabled = false;
  setTimeout(() => autoPlay(), 1000);
}

function onNextStep() {
  const { auto, next, reset } = appContext.buttons;
  const { commands, step } = appContext;
  if (step >= commands.length) {
    next.disabled = true;
    reset.disabled = false;
    return;
  }
  auto.disabled = true;
  reset.disabled = false;
  runCommand(commands[step]);
  appContext.step += 1;
}

function onReset() {
  const { auto, next, reset } = appContext.buttons;
  if (appContext.tasks) {
    appContext.tasks.forEach(task => clearTimeout(task));
  }
  auto.disabled = false;
  next.disabled = false;
  reset.disabled = true;
  init();
  sortData();
  displayMessage('<span>&nbsp;</span>');
}
