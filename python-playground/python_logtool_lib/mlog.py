class mLog:
    # mLog("log","succes!").wq()
    def __init__(self, roadName, logs):
        if not all([roadName, logs]):  # 当 arg1, arg2, arg3都不为空时all函数返回true
            self.OK = False
            # return jsonify(errno=RET.PARAMERR, errmsg=u"oh ,log function 参数不完整！")
            pass
        else:
            self.OK = True
            self.nowtimes = time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())
            self.logr = str(useTool().filesafer(os.getcwd() + '/' + roadName + '.txt'))
            self.logC = logs
            # use usetool ,please

    def wq(self):
        if self.OK:
            with open(self.logr, 'a+') as f:
                f.write(str(self.nowtimes) + ' -' + str(self.logC) + "\n")
                useTool().dprint(str(self.nowtimes) + ' -' + str(self.logC))
