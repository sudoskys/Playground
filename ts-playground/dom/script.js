// 选择DOM
var headingElem = document.querySelector('h1'); // 将选择第一个 <h1> 元素
var introParagraphElem = document.getElementById('intro'); // 选择ID为 'intro' 的元素

// 修改DOM
headingElem.textContent = 'Hello, OpenAI!'; // 改变 <h1> 的文本
introParagraphElem.style.color = 'red'; // 改变段落文本颜色

// 创建并插入DOM
var newParagraphElem = document.createElement('p'); // 创建一个新的 <p> 元素
newParagraphElem.textContent = '我是新增的文本。'; // 设置新元素的文本
document.getElementById('content').appendChild(newParagraphElem); // 将新元素添加到 ID 为 'content' 的元素中

// 删除DOM
document.getElementById('content').removeChild(introParagraphElem); // 删除ID 为 'intro' 的元素

// 添加事件
var actionButton = document.getElementById('actionButton') // 获取ID为 'actionButton' 的按钮元素
actionButton.addEventListener('click', function(){ // 设置点击事件监听器
   document.body.style.backgroundColor = 'blue'; // 点击后改变背景颜色
   document.getElementById('content').style.display = 'none'; // 隐藏ID为 'content' 的元素
   document.querySelector('h1').textContent = '你点击了按钮！'; // 改变 <h1> 的文本
})
