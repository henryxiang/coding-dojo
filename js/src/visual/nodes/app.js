import Raphael from 'raphael';
import _ from 'lodash';
import 'babel-polyfill';
import Node from './graph-node';

const body = document.getElementsByTagName('body')[0];
const app = document.createElement('div');
// app.id = 'app';
body.append(app);

// app.innerText = 'Demo App';
const paper = Raphael(app, 600, 400);
// paper
//   .rect(50, 50, 100, 100)
//   .attr({ fill: 'palegreen' });

let letters = ['A', 'B', 'C', 'D', 'E'];
const nodes = {};

for (let i = 0; i < letters.length; i++) {
  const x = 100 + 100*i;
  const y = 200;
  const node = new Node(letters[i], x, y);
  node.draw(paper);
  nodes[letters[i]] = node;
}

const shuffle = () => {
  letters = _.shuffle(letters);
  for (let i = 0; i < letters.length; i++) {
    const x = 100 + 100*i;
    const y = 200;
    console.log(i, letters[i]);
    nodes[letters[i]].moveTo(x, y);
  }
};

shuffle();
shuffle();
shuffle();
