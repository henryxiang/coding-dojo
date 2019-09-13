class Node {
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

  moveTo(x, y) {
    const duration = 800;
    this.x = x;
    this.y = y;
    this.text.animate({ x, y }, duration);
    this.circle.animate({ cx: x, cy: y }, duration);
    // return new Promise((resolve) => {
    //   setTimeout(() => resolve(1), duration);
    // })
  }
}

export default Node;
