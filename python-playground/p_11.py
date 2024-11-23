class Student:
    def __init__(self, student_id, name, gender, age):
        self.student_id = student_id
        self.name = name
        self.gender = gender
        self.age = age

    def print_info(self):
        print(f"学号: {self.student_id}")
        print(f"姓名: {self.name}")
        print(f"性别: {self.gender}")
        print(f"年龄: {self.age}")

# Create a Student object
stu = Student(20210001, "张三", "男", 23)
# Print student information
stu.print_info()


class Employee:
    def __init__(self, name, age):
        self.name = name
        self.age = age
        self.ability = 100 - age

    def working(self):
        self.ability -= 5

class Boss:
    def __init__(self, money, work):
        self.money = money
        self.work = work
        self.employeeList = []
        self.canEmploy = True

    def addEmployee(self, employee):
        if self.money >= 5000:
            self.employeeList.append(employee)
            self.money -= 5000
            # print(f"雇佣员工: {employee.name} 成功")
        else:
            print(f"资金不足！无法雇佣员工{employee.name}")

    def startWork(self):
        while self.work > 0:
            for employee in self.employeeList:
                if self.work <= 0:
                    break
                if employee.ability > 0:
                    employee.working()
                    self.work -= 5

            if all(emp.ability <= 0 for emp in self.employeeList):
                print(f"剩余工作: {self.work}, 劳动力不足需要雇佣新的工人来完成")
                break

        self.endWork()

    def endWork(self):
        if self.work <= 0:
            print("工作完成!")
            for employee in self.employeeList:
                print(f"姓名: {employee.name}, 能力值: {employee.ability}")

# 创建Boss对象，输入资金和工作量
input_str = input()
money, work = map(int, input_str.split(','))
boss = Boss(money, work)

# 雇佣员工
boss.addEmployee(Employee("张三", 30))
boss.addEmployee(Employee("李四", 40))
boss.addEmployee(Employee("王五", 50))

# 开始工作
boss.startWork()
