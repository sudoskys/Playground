from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import time
from selenium.webdriver.firefox.service import Service
from selenium.webdriver.firefox.options import Options

# 设置 Firefox 选项
firefox_options = Options()
# 禁用通知弹出窗口
firefox_options.set_preference("dom.webnotifications.enabled", False)
# 设置选项
firefox_options = Options()
# 可选：添加无头模式（在后台运行）
# firefox_options.add_argument('--headless')

# 初始化 WebDriver
service = Service('/usr/bin/geckodriver')
driver = webdriver.Firefox(service=service, options=firefox_options)
try:
    # 打开学习通登录页面
    driver.get("https://passport2.chaoxing.com/login")

    # 等待并输入手机号、密码，点击登录
    phone_input = WebDriverWait(driver, 10).until(
        EC.presence_of_element_located((By.CSS_SELECTOR, ".ipt-tel"))
    )
    phone_input.send_keys("x x x x")  # 请替换为实际的手机号
    password_input = driver.find_element(By.CSS_SELECTOR, ".ipt-pwd")
    password_input.send_keys("x x x x")  # 请替换为实际的密码
    login_button = driver.find_element(By.CSS_SELECTOR, "#loginBtn")
    login_button.click()
    # 等待登录完成并进入课程页面
    time.sleep(3)  # 等待登录后的重定向
    iframe = driver.find_element(By.CSS_SELECTOR, "#frame_content")
    print("iframe:", iframe)
    # 切换到课程页面的 frame
    driver.switch_to.frame(iframe)

    # 从课程列表中选择某个课程并进入
    course_link = WebDriverWait(driver, 10).until(
        EC.presence_of_element_located((By.CSS_SELECTOR, "#courseList li:nth-child(1) a"))
    )
    course_link.click()
    time.sleep(3)  # 等待课程页面加载
    # 切换到新的窗口
    all_handles = driver.window_handles
    driver.switch_to.window(all_handles[-1])  # 切换到最新打开的窗口

    # 在个人空间页面中选择另一个课程
    # 返回课程列表，进行另一个课程的选择
    driver.switch_to.window(all_handles[0])  # 假设个人空间在初始窗口

    frame = driver.find_element(By.CSS_SELECTOR, "#frame_content")
    # 再次切换到 frame
    driver.switch_to.frame(frame)

    # 选择另一个课程进入
    another_course_link = WebDriverWait(driver, 10).until(
        EC.presence_of_element_located((By.CSS_SELECTOR, "#courseList li:nth-child(2) a"))
    )
    another_course_link.click()

    # 打印所有窗口句柄
    all_handles = driver.window_handles
    print("所有窗口句柄:", all_handles)

finally:
    # 关闭浏览器
    driver.quit()