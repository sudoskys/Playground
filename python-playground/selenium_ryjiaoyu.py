# 浏览器模拟 selenium
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
import time
# 设置 Chrome 选项
chrome_options = Options()
# 可选：添加无头模式（在后台运行）
# chrome_options.add_argument('--headless')

service = Service('/usr/bin/chromedriver')
driver = webdriver.Chrome(service=service, options=chrome_options)
driver.get("http://account.ryjiaoyu.com/log-in")

# 等待页面加载并定位元素
wait = WebDriverWait(driver, 10)

# 定位并输入用户名和密码
username = wait.until(EC.presence_of_element_located((By.XPATH, '//*[@id="Email"]')))
username.clear()
username.send_keys("****")

password = wait.until(EC.presence_of_element_located((By.XPATH, '//*[@id="Password"]')))
password.clear()
password.send_keys("****")

# 点击登录按钮
login_button = wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, "input.btn")))
login_button.click()

# 检测是否成功登录
if driver.current_url != "http://account.ryjiaoyu.com/":
    print("登录失败")
    time.sleep(10) 
    driver.quit()

# 等待登录成功后的页面加载（可选）
wait.until(EC.presence_of_element_located((By.LINK_TEXT, "退出")))

# 等待截图
time.sleep(10)
driver.save_screenshot('screenshot.png')

# driver.quit()  # 如果需要关闭浏览器，取消注释此行

