-- AndroLua data access library -- suitable for Api data multiplexing
local Cache = {}
function Cache:new(time,d)
  --创建对象
  local funt = {}
  setmetatable(funt, self)
  self.__index = self
  import "java.io.File"
  self.HOME = activity.getLuaDir()
  self.i=self.HOME.."/Cache/".."all.index"
  self.c=self.HOME.."/Cache/"
  self.time=time
  if not File(self.i).exists() then
    --File(i).createNewFile()
    io.open(self.i,"w+"):write("{}"):close()
  end
  ----
  self.debugs=function(s)if d then print(s)end end
  self.StrToTable=function (str)
    if str == nil or type(str) ~= "string" then
      return
    end
    return loadstring("return " .. str)()
  end
  self.author = ("sudoskys")
  import "android.os.Environment"
  self.ROOT = tostring(Environment.getExternalStorageDirectory())
  return funt
end


function Cache:newIndex(key)
  _indexCache=self.StrToTable(io.open(self.i):read("*a"))
  _indexCache[key]=os.time()
  io.open(self.i,"w+"):write(dump(_indexCache)):close()
end
function Cache:newTable(key,content)
  --创建索引表和数据表文件
  File(self.c).mkdir()
  if #key~=0 then
    io.open(self.c..key,"w+"):write(content):close()
  end
end

function Cache:wf(key,content)
  --写入新的数据
  Dog:newIndex(key)
  Dog:newTable(key,content)
  self.debugs("写入："..key)
  return true
end

function Cache:rf(key)
  --读取一个键值
  local function sr()
    _indexCache=self.StrToTable(io.open(self.i):read("*a"))
    if _indexCache[key] then
      time=_indexCache[key]
      cha=os.time()-time
     else
      cha=114514
    end
    if cha>self.time then
      return true
     else
      return false
    end
  end
  local function rc()
    if #key~=0 and File(self.c..key).exists() then
      self.debugs("读取："..key)
      con=io.open(self.c..key):read("*a")
      self.debugs("读取内容："..con)
    end
    return con,renew
  end
  return rc(),sr()
end


Dog=Cache:new(70,true)
--Dog.wf("数据键","数据本身")
some,ss=Dog:rf("数据键")

print(some)
print(ss)
