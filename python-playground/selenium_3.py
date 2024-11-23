from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
# 设置 Chrome 选项
chrome_options = Options()
# 可选：添加无头模式（在后台运行）
# chrome_options.add_argument('--headless')
# 初始化 WebDriver
service = Service('/usr/bin/chromedriver')
driver = webdriver.Chrome(service=service, options=chrome_options)
try:
    # 打开百度页面
    driver.get("https://www.baidu.com")

    # 等待并点击登录按钮
    login_button = WebDriverWait(driver, 10).until(
        EC.element_to_be_clickable((By.CSS_SELECTOR, "#s-top-loginbtn"))
    )
    login_button.click()

    # 等待并点击“立即注册”
    register_link = WebDriverWait(driver, 10).until(
        EC.element_to_be_clickable((By.CSS_SELECTOR, "#TANGRAM__PSP_11__regLink"))
    )
    register_link.click()

    # 获取所有窗口句柄
    all_handles = driver.window_handles
    # 切换到新的注册窗口
    driver.switch_to.window(all_handles[1])

    # 输入用户名和手机号码
    user_name = WebDriverWait(driver, 10).until(
        EC.presence_of_element_located((By.CSS_SELECTOR, "#TANGRAM__PSP_4__userName"))
    )
    user_name.send_keys("用户名")

    phone_number = driver.find_element(By.CSS_SELECTOR, "#TANGRAM__PSP_4__phone")
    phone_number.send_keys("电话")

    # 切换回登录窗口
    driver.switch_to.window(all_handles[0])

    # 输入用户名和密码
    login_user_name = WebDriverWait(driver, 10).until(
        EC.presence_of_element_located((By.CSS_SELECTOR, "#TANGRAM__PSP_11__userName"))
    )
    login_user_name.send_keys("用户名")

    login_password = driver.find_element(By.CSS_SELECTOR, "#TANGRAM__PSP_11__password")
    login_password.send_keys("密码")

finally:
    # 关闭浏览器
    driver.quit()