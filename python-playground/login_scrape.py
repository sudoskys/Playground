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
# chrome_options.add_argument('--headless')  # 无头模式，可选

# Linux 系统下配置
service = Service('/usr/bin/chromedriver')
driver = webdriver.Chrome(service=service, options=chrome_options)

# 访问登录页面
driver.get("https://login3.scrape.center/login")

# 等待机制
wait = WebDriverWait(driver, 10)

try:
    # 定位用户名输入框
    username = wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, "input[type='text']")))
    username.clear()
    username.send_keys("admin")

    # 定位密码输入框
    password = wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, "input[type='password']")))
    password.clear()
    password.send_keys("admin")

    # 定位并点击登录按钮
    login_button = wait.until(EC.element_to_be_clickable((By.CSS_SELECTOR, "div.el-form-item__content:nth-child(1) > button:nth-child(1)")))
    login_button.click()

    # 验证登录成功
    wait.until(EC.url_to_be("https://login3.scrape.center/"))
    print("登录成功！")
    
    # 等待所有卡片加载完成
    cards = wait.until(EC.presence_of_all_elements_located((By.CSS_SELECTOR, ".el-card__body h3")))

    # 打印所有卡片标题
    print("\n获取到的卡片标题：")
    for index, card in enumerate(cards, 1):
        print(f"{index}. {card.text}")
    
    # 截图相关代码
    time.sleep(1)  # 等待页面完全加载
    driver.save_screenshot('screenshot.png')
    print("截图已经保存了")
except Exception as e:
    print(f"操作失败：{str(e)}")
    driver.save_screenshot('error.png')  # 保存错误截图

finally:
    time.sleep(3)  # 等待几秒查看结果
    driver.quit()

