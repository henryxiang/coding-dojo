const $ = document;
const body = $.getElementsByTagName('body')[0];
const div = $.createElement('div');
div.innerText = 'Hello World!';
body.append(div);
