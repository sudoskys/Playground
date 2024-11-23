import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.action_chains import ActionChains
import logging

# 配置日志
logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(levelname)s - %(message)s',
    handlers=[
        logging.FileHandler('login_test.log', encoding='utf-8'),
        logging.StreamHandler()
    ]
)


# pytest 的 fixture，初始化和清理逻辑
@pytest.fixture
def setup_driver():
    logging.info("=== 测试初始化：启动浏览器 ===")
    chrome_options = Options()
    service = Service('/usr/bin/chromedriver')
    driver = webdriver.Chrome(service=service, options=chrome_options)
    wait = WebDriverWait(driver, 10)
    base_url = "https://login3.scrape.center/login"
    logging.info("浏览器启动成功")
    yield driver, wait, base_url  # 使用 yield 关键字允许在测试用例中使用
    logging.info("=== 测试清理：关闭浏览器 ===")
    driver.quit()


# Test Case 1: 使用正确的账号和密码登录
def test_successful_login(setup_driver):
    driver, wait, base_url = setup_driver
    logging.info("开始执行测试用例1：正确账号密码登录测试")
    driver.get(base_url)
    logging.info(f"访问登录页面：{base_url}")

    username = wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, "input[type='text']")))
    username.send_keys("admin")
    logging.info("输入用户名：admin")

    password = wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, "input[type='password']")))
    password.send_keys("admin")
    logging.info("输入密码：******")

    login_button = wait.until(
        EC.element_to_be_clickable((By.CSS_SELECTOR, "div.el-form-item__content:nth-child(1) > button:nth-child(1)")))
    login_button.click()
    logging.info("点击登录按钮")

    wait.until(EC.url_to_be("https://login3.scrape.center/"))
    assert driver.current_url == "https://login3.scrape.center/"
    logging.info("登录成功，URL验证通过")


# Test Case 2: 使用错误密码登录
def test_invalid_password(setup_driver):
    driver, wait, base_url = setup_driver
    logging.info("开始执行测试用例2：错误密码登录测试")
    driver.get(base_url)

    username = wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, "input[type='text']")))
    username.send_keys("admin")
    logging.info("输入用户名：admin")

    password = wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, "input[type='password']")))
    password.send_keys("wrong_password")
    logging.info("输入错误密码")

    login_button = wait.until(
        EC.element_to_be_clickable((By.CSS_SELECTOR, "div.el-form-item__content:nth-child(1) > button:nth-child(1)")))
    login_button.click()
    logging.info("点击登录按钮")

    error_message = wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, ".el-message--error")))
    assert "失败" in error_message.text
    logging.info("成功捕获错误提示信息")


# Test Case 3: 空字段登录测试
def test_empty_fields(setup_driver):
    driver, wait, base_url = setup_driver
    logging.info("开始执行测试用例3：空字段登录测试")
    driver.get(base_url)

    login_button = wait.until(
        EC.element_to_be_clickable((By.CSS_SELECTOR, "div.el-form-item__content:nth-child(1) > button:nth-child(1)")))
    login_button.click()
    logging.info("点击登录按钮")

    error_message = wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, ".el-message--error")))
    assert "失败" in error_message.text
    logging.info("成功捕获表单验证提示")


# Test Case 4: 登出功能测试
def test_logout_function(setup_driver):
    driver, wait, base_url = setup_driver
    logging.info("开始执行测试用例4：登出功能测试")

    # 登录操作
    test_successful_login(setup_driver)

    # 触发下拉菜单并登出
    dropdown_trigger = wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, ".logout")))
    logging.info("找到下拉菜单触发器")

    ActionChains(driver).move_to_element(dropdown_trigger).perform()
    logging.info("触发下拉菜单显示")

    logout_button = wait.until(EC.element_to_be_clickable((By.XPATH, "//span[text()='登出']")))
    logout_button.click()
    logging.info("点击登出按钮")

    wait.until(EC.url_to_be(base_url))
    assert driver.current_url == base_url
    logging.info("成功验证返回登录页")


# Test Case 5: 记住密码功能测试
def test_remember_me(setup_driver):
    driver, wait, base_url = setup_driver
    logging.info("开始执行测试用例5：记住密码功能测试")
    driver.get(base_url)

    # 登录操作
    username = wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, "input[type='text']")))
    username.send_keys("admin")
    logging.info("输入用户名：admin")

    password = wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, "input[type='password']")))
    password.send_keys("admin")
    logging.info("输入密码：******")

    login_button = wait.until(
        EC.element_to_be_clickable((By.CSS_SELECTOR, "div.el-form-item__content:nth-child(1) > button:nth-child(1)")))
    login_button.click()
    logging.info("点击登录按钮")

    wait.until(EC.url_to_be("https://login3.scrape.center/"))
    cookies = driver.get_cookies()
    driver.delete_all_cookies()
    for cookie in cookies:
        driver.add_cookie(cookie)

    driver.refresh()
    assert driver.current_url == "https://login3.scrape.center/"
    logging.info("成功验证登录状态保持")