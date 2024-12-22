from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
import time  # 设置延迟方便观察测试执行

# 设置 Chrome 选项
chrome_options = Options()
# 可选：添加无头模式
# chrome_options.add_argument('--headless')

# 初始化 WebDriver
service = Service('/usr/bin/chromedriver')  # 替换为你的 chromedriver 路径
driver = webdriver.Chrome(service=service, options=chrome_options)

try:
    # 测试登录功能
    driver.get("http://localhost:3000/login")
    print("测试登录页面...")
    time.sleep(3)  # 等待页面加载完成
    # 输入登录邮箱和密码
    driver.find_element(By.ID, "email").send_keys("demo@gmail.com")
    driver.find_element(By.ID, "password").send_keys("passwords")
    # 点击登录按钮
    driver.find_element(By.CSS_SELECTOR, "button[type='submit']").click()

    # 等待跳转到主页，确认主页加载成功
    WebDriverWait(driver, 10).until(
        EC.url_to_be("http://localhost:3000/")  # 检测 URL 地址是否为主页
    )
    print("登录功能测试完成，已跳转到主页。")

    # 测试注册功能
    driver.get("http://localhost:3000/register")
    print("测试注册页面...")
    time.sleep(3)  # 等待页面加载完成
    # 输入注册邮箱和密码
    driver.find_element(By.ID, "email").send_keys("new_user4@example.com")
    driver.find_element(By.ID, "password").send_keys("password1234")
    # 点击注册按钮
    driver.find_element(By.CSS_SELECTOR, "button").click()
    WebDriverWait(driver, 10).until(
        EC.presence_of_element_located((By.XPATH, "//*[@id='1']/div/div"))
    )
    print("注册功能测试完成，已跳转到主页。")
except Exception as e:
    print(f"测试遇到错误: {e}")

finally:
    # 等待数秒观察浏览器操作结果后关闭
    time.sleep(3)
    driver.quit()
    print("所有测试完成，浏览器已关闭。")