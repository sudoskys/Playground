"""
数据库操作工具模块
"""


def execute_sql(sql):
    """
    用于执行SQL语句的函数
    :param sql: 要执行的SQl语句
    :return: 结果集list
    """
    import pymysql

    # 连接数据库
    conn = pymysql.connect(host='localhost',
                           user='root',
                           password='rootroot',
                           database='student')

    # 获取游标
    cursor = conn.cursor()

    try:
        # 执行SQL语句
        cursor.execute(sql)
        # 获取结果集中的所有数据
        result_list = cursor.fetchall()
        conn.commit()  # 提交事务
        return result_list
    except Exception as e:
        print("数据库操作错误！", e)
        conn.rollback()  # 回滚操作

    finally:  # 不论是否有异常都关闭游标和数据库连接
        # 关闭游标
        cursor.close()
        # 关闭数据库连接
        conn.close()


def 主程序():
    总学生()
    最高平均分()
    不及格学生()
    添加记录('0000', '小周', 99)
    删除('王阳')
    更新('0000', 92)


def 总学生():
    sql = "SELECT COUNT(*) FROM python;"
    result = execute_sql(sql)
    print(f"一共有 {result[0][0]} 位同学参加了考试")


def 最高平均分():
    highest_sql = "SELECT name, score FROM python ORDER BY score DESC LIMIT 1;"
    average_sql = "SELECT AVG(score) FROM python;"

    highest_result = execute_sql(highest_sql)
    average_result = execute_sql(average_sql)

    print(f"Python程序设计课程最高分: {highest_result[0][0]}, {highest_result[0][1]}")
    print(f"Python程序设计课程平均分: {average_result[0][0]:.2f}")


def 不及格学生():
    sql = "SELECT name, score FROM python WHERE score < 60;"
    results = execute_sql(sql)

    print(f"不及格的学生有 {len(results)} 位，其姓名及分数如下:")
    for name, score in results:
        print(f"{name}: {score}")


def 添加记录(number, name, score):
    sql = f"INSERT INTO python (number, name, score) VALUES ('{number}', '{name}', {score});"
    execute_sql(sql)
    print(f"添加记录: ({number}, {name}, {score})")


def 删除(name):
    sql = f"DELETE FROM python WHERE name = '{name}';"
    execute_sql(sql)
    print(f"删除姓名为 '{name}' 的记录")


def 更新(number, new_score):
    sql = f"UPDATE python SET score = {new_score} WHERE number = '{number}';"
    execute_sql(sql)
    print(f"更新学号为 '{number}' 的成绩为 {new_score}")


if __name__ == "__main__":
    主程序()
