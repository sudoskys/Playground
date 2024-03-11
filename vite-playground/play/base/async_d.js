// exp
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __generator = (this && this.__generator) || function (thisArg, body) {
    var _ = { label: 0, sent: function() { if (t[0] & 1) throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g;
    return g = { next: verb(0), "throw": verb(1), "return": verb(2) }, typeof Symbol === "function" && (g[Symbol.iterator] = function() { return this; }), g;
    function verb(n) { return function (v) { return step([n, v]); }; }
    function step(op) {
        if (f) throw new TypeError("Generator is already executing.");
        while (g && (g = 0, op[0] && (_ = 0)), _) try {
            if (f = 1, y && (t = op[0] & 2 ? y["return"] : op[0] ? y["throw"] || ((t = y["return"]) && t.call(y), 0) : y.next) && !(t = t.call(y, op[1])).done) return t;
            if (y = 0, t) op = [op[0] & 2, t.value];
            switch (op[0]) {
                case 0: case 1: t = op; break;
                case 4: _.label++; return { value: op[1], done: false };
                case 5: _.label++; y = op[1]; op = [0]; continue;
                case 7: op = _.ops.pop(); _.trys.pop(); continue;
                default:
                    if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) { _ = 0; continue; }
                    if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) { _.label = op[1]; break; }
                    if (op[0] === 6 && _.label < t[1]) { _.label = t[1]; t = op; break; }
                    if (t && _.label < t[2]) { _.label = t[2]; _.ops.push(op); break; }
                    if (t[2]) _.ops.pop();
                    _.trys.pop(); continue;
            }
            op = body.call(thisArg, _);
        } catch (e) { op = [6, e]; y = 0; } finally { f = t = 0; }
        if (op[0] & 5) throw op[1]; return { value: op[0] ? op[1] : void 0, done: true };
    }
};
function sleep(ms) {
    return new Promise(function (resolve) { return setTimeout(resolve, ms); });
}
function asyncFunction() {
    return __awaiter(this, void 0, void 0, function () {
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0:
                    console.log('Start');
                    return [4 /*yield*/, sleep(2000)];
                case 1:
                    _a.sent(); // 暂停 2 秒
                    console.log('End');
                    return [2 /*return*/];
            }
        });
    });
}
asyncFunction().then(function (r) { return console.log(r); });
function handleMultipleAsyncOperations() {
    return __awaiter(this, void 0, void 0, function () {
        var promise1, promise2, promise3, results, error_1;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0:
                    _a.trys.push([0, 2, , 3]);
                    promise1 = sleep(2);
                    promise2 = sleep(3);
                    promise3 = sleep(5);
                    return [4 /*yield*/, Promise.all([promise1, promise2, promise3])];
                case 1:
                    results = _a.sent();
                    console.log(results); // 这将是一个数组 [result1, result2, result3]
                    return [3 /*break*/, 3];
                case 2:
                    error_1 = _a.sent();
                    console.error("发生错误: ", error_1);
                    return [3 /*break*/, 3];
                case 3: return [2 /*return*/];
            }
        });
    });
}
handleMultipleAsyncOperations()
    .then(function (r) { return console.log(r); }, function (e) { return console.log(e); })
    .catch(function (e) { return console.log(e); });
var promise = new Promise(function (resolve, reject) {
    // 异步操作
    setTimeout(function () {
        if (Math.random() > 0.5) {
            resolve('成功的结果');
        }
        else {
            reject('失败的原因');
        }
    }, 1000);
});
promise.then(function (result) {
    console.log('Promise 成功: ', result);
}).catch(function (error) {
    console.log('Promise 失败: ', error);
});
