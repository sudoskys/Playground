


class useTool:
    # github：sudoskys
    def __init__(self):
        self.debug = True

    def dprint(self, log):
        if self.debug:
            print(log)
    def filesafer(self, filename):
        def wr(filename):
            import os
            file_dir = os.path.split(filename)[0]
            if not os.path.isdir(file_dir):
                os.makedirs(file_dir)
            if not os.path.exists(filename):
                os.system(r'touch %s' % filename)
            return filename

        try:
            road = wr(filename)
            # droad = os.getcwd() + road
            self.dprint("New+ " + road)
            return road
        except:
            import os
            print("重定向路径中" + str(os.getcwd() + '/' + filename))
            try:
                road = wr(os.getcwd() + '/' + filename)
                return road
            except IOError as err:
                print("err", err)
                print("Error:NOT FOUND FILE 没有找到文件或读取文件失败")
                return False
    def sData(self, file_name, tables):
        self.filesafer(file_name)
        if type(tables) == type({}) or type(tables) == type(["x"]):
            try:
                from ruamel.yaml import YAML
                yaml = YAML()
                with open(file_name, 'w') as f_obj:
                    yaml.dump(tables, f_obj)
            except IOError as err:
                print("err", err)
                raise Exception("NOT FOUND FILE 没有找到文件或读取文件失败", err)
            else:
                return True
        else:
            print("Type Error:MUST TABLE", tables)
            return False

    def rData(self, file_names):
        import os
        file_name = os.getcwd() + '/' + file_names
        self.filesafer(file_name)
        from ruamel.yaml import YAML
        with open(file_name) as f_obj:
            try:
                data = YAML(typ='safe').load(f_obj)
                # print(data)
                return data
            except Exception as err:
                print("err", err)
                return {}

