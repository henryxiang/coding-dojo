import Raphael from "raphael";

class TextBox {
  constructor(x, y, w, h, text) {
    this.x = x;
    this.y = y;
    this.width = w;
    this.height = h;
    this.text = text;
    this.nodes = {
      text: null,
      rect: null,
    };
  }

  draw(paper) {
    const { x, y, width, height, text, nodes } = this;
    if (nodes.text) nodes.text.remove();
    if (nodes.rect) nodes.rect.remove()
    nodes.rect = paper.rect(x - width/2, y - height/2, width, height).attr({ fill: 'palegreen' });
    nodes.text = paper.text(x, y, text).attr({ fill: 'red', 'font-size': 20 });  
  }

  moveTo(x, y) {
    const duration = 2000;
    this.x = x;
    this.y = y;
    this.nodes.text.animate({ x, y }, duration);
    this.nodes.rect.animate({ x: x - this.width/2, y: y - this.height/2 }, duration);
  }
}
const $ = document;
const body = $.getElementsByTagName("body")[0];
const div = $.createElement("div");
div.id = 'canvas';
body.append(div);

const paper = Raphael('canvas', 500, 500);
const textBox = new TextBox(60, 60 , 100, 100, 'Raphael');
textBox.draw(paper);

textBox.moveTo(200, 200);
