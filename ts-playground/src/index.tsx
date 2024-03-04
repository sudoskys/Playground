import * as React from "react";
import * as ReactDOM from "react-dom";

import {Hello} from "./components/Hello";
import {createRoot} from "react-dom/client";

import {MakeBot, makeUid} from "./great/great";

createRoot(
    document.getElementById("example")
).render(<Hello compiler="TypeScript" framework="React"/>);

// 操作页面
let student_d = {} as MakeBot;
student_d.bot_token = "10086"
student_d.bot_id = 110
// Json 化
let student_d_json = JSON.stringify(student_d)

// 显示结果
console.log(`Student ${student_d_json}`)


