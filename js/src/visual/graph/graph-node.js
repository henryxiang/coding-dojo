const duration = 1000;

export default class Node {
  constructor(label, x = 20, y = 20, r = 20, fontSize = 20, bgColor = 'palegreen') {
    this.label = label;
    this.x = x;
    this.y = y;
    this.r = r;
    this.fontSize = fontSize;
    this.bgColor = bgColor;
    this.text = null;
    this.circle = null;
  }

  draw(paper) {
    const { x, y, r, label, fontSize, bgColor } = this;
    if (this.text) this.text.remove();
    if (this.circle) this.circle.remove();
    this.circle = paper.circle(x, y, r).attr({ fill: bgColor });
    this.text = paper.text(x, y, label).attr({ 'font-size': fontSize });
  }

  moveTo(x, y, d = duration) {
    this.x = x;
    this.y = y;
    this.text.animate({ x, y }, d);
    this.circle.animate({ cx: x, cy: y }, d)
  }

  highlight(d = duration) {
    this.circle.animate({ fill: 'blue' }, d, '>', () => this.circle.animate({ fill: this.bgColor }, d/4));
    this.text.animate({ fill: 'white' }, d, '>', () => this.text.animate({ fill: 'black'}, d/4));
  }
}

export const swap = (n1, n2) => {
  const x1 = n1.x;
  const y1 = n1.y;
  const x2 = n2.x;
  const y2 = n2.y;
  n1.moveTo(x2, y2);
  n2.moveTo(x1, y1);
}
