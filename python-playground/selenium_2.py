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

# 初始化 WebDriver
service = Service('/usr/bin/chromedriver')
driver = webdriver.Chrome(service=service, options=chrome_options)

try:
    # 打开包含 frame 的页面
    driver.get("https://www.runoob.com/try/try.php?filename=tryhtml_iframe")

    # 等待页面加载
    wait = WebDriverWait(driver, 10)

    # 切换到 iframe
    iframe = driver.find_element(By.CSS_SELECTOR, "#iframeResult")
    print("iframe:", iframe)
    driver.switch_to.frame(iframe)
    iframe_inner = driver.find_element(By.CSS_SELECTOR, "body > iframe:nth-child(1)")
    print("iframe_inner:", iframe_inner)
    driver.switch_to.frame(iframe_inner)
    # 在 iframe 内找到并点击某个链接
    nav_link = driver.find_element(By.CSS_SELECTOR, "#index-nav > li:nth-child(2) > a:nth-child(1)")
    print("nav_link:", nav_link)
    nav_link.click()
    # 打印所有窗口句柄
    all_handles = driver.window_handles
    print("所有窗口句柄:", all_handles)

finally:
    # 关闭浏览器
    driver.quit()