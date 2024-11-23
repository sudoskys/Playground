import unittest
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

class LoginTest(unittest.TestCase):
    def setUp(self):
        logging.info("=== 开始测试用例执行 ===")
        chrome_options = Options()
        service = Service('/usr/bin/chromedriver')
        self.driver = webdriver.Chrome(service=service, options=chrome_options)
        self.wait = WebDriverWait(self.driver, 10)
        self.base_url = "https://login3.scrape.center/login"
        logging.info("浏览器启动成功")

    def test_case_001_successful_login(self):
        """测试用例1：使用正确的账号密码登录"""
        logging.info("开始执行测试用例1：正确账号密码登录测试")
        self.driver.get(self.base_url)
        logging.info(f"访问登录页面：{self.base_url}")
        
        try:
            username = self.wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, "input[type='text']")))
            username.send_keys("admin")
            logging.info("输入用户名：admin")
            
            password = self.wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, "input[type='password']")))
            password.send_keys("admin")
            logging.info("输入密码：******")
            
            login_button = self.wait.until(EC.element_to_be_clickable((By.CSS_SELECTOR, "div.el-form-item__content:nth-child(1) > button:nth-child(1)")))
            login_button.click()
            logging.info("点击登录按钮")
            
            self.wait.until(EC.url_to_be("https://login3.scrape.center/"))
            self.assertEqual(self.driver.current_url, "https://login3.scrape.center/")
            logging.info("登录成功，URL验证通过")
        except Exception as e:
            logging.error(f"测试用例1执行失败：{str(e)}")
            raise

    def test_case_002_invalid_password(self):
        """测试用例2：使用错误密码登录"""
        logging.info("开始执行测试用例2：错误密码登录测试")
        try:
            self.driver.get(self.base_url)
            
            username = self.wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, "input[type='text']")))
            username.send_keys("admin")
            logging.info("输入用户名：admin")
            
            password = self.wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, "input[type='password']")))
            password.send_keys("wrong_password")
            logging.info("输入错误密码")
            
            login_button = self.wait.until(EC.element_to_be_clickable((By.CSS_SELECTOR, "div.el-form-item__content:nth-child(1) > button:nth-child(1)")))
            login_button.click()
            logging.info("点击登录按钮")
            
            error_message = self.wait.until(EC.presence_of_element_located((By.CSS_SELECTOR , ".el-message--error")))
            self.assertIn("失败", error_message.text)
            logging.info("成功捕获错误提示信息")
        except Exception as e:
            logging.error(f"测试用例2执行失败：{str(e)}")
            raise

    def test_case_003_empty_fields(self):
        """测试用例3：空字段登录测试"""
        logging.info("开始执行测试用例3：空字段登录测试")
        try:
            self.driver.get(self.base_url)
            
            login_button = self.wait.until(EC.element_to_be_clickable((By.CSS_SELECTOR, "div.el-form-item__content:nth-child(1) > button:nth-child(1)")))
            login_button.click()
            logging.info("点击登录按钮")
            
            error_message = self.wait.until(EC.presence_of_element_located((By.CSS_SELECTOR , ".el-message--error")))
            self.assertIn("失败", error_message.text)
            logging.info("成功捕获表单验证提示")
        except Exception as e:
            logging.error(f"测试用例3执行失败：{str(e)}")
            raise

    def test_case_004_logout_function(self):
        """测试用例4：登出功能测试"""
        logging.info("开始执行测试用例4：登出功能测试")
        try:
            # 先登录
            self.test_case_001_successful_login()
            
            # 先找到并悬停在触发下拉菜单的元素上
            dropdown_trigger = self.wait.until(
                EC.presence_of_element_located((By.CSS_SELECTOR, ".logout"))
            )
            logging.info("找到下拉菜单触发器")
            
            # 使用 ActionChains 模拟鼠标悬停
            ActionChains(self.driver).move_to_element(dropdown_trigger).perform()
            logging.info("触发下拉菜单显示")
            
            # 等待下拉菜单显示并点击登出按钮
            logout_button = self.wait.until(
                EC.element_to_be_clickable((By.XPATH, "//span[text()='登出']"))
            )
            logout_button.click()
            logging.info("点击登出按钮")
            
            # 验证是否返回登录页
            self.wait.until(EC.url_to_be(self.base_url))
            self.assertEqual(self.driver.current_url, self.base_url)
            logging.info("成功验证返回登录页")
        except Exception as e:
            logging.error(f"测试用例4执行失败：{str(e)}")
            raise

    def test_case_005_remember_me(self):
        """测试用例5：记住密码功能测试"""
        logging.info("开始执行测试用例5：记住密码功能测试")
        try:
            self.driver.get(self.base_url)
            
            # 登录
            username = self.wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, "input[type='text']")))
            username.send_keys("admin")
            logging.info("输入用户名：admin")
            
            password = self.wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, "input[type='password']")))
            password.send_keys("admin")
            logging.info("输入密码：******")
            
            login_button = self.wait.until(EC.element_to_be_clickable((By.CSS_SELECTOR, "div.el-form-item__content:nth-child(1) > button:nth-child(1)")))
            login_button.click()
            logging.info("点击登录按钮")
            
            # 验证登录状态保持
            self.wait.until(EC.url_to_be("https://login3.scrape.center/"))
            
            # 关闭浏览器后重新打开（模拟）
            cookies = self.driver.get_cookies()
            self.driver.delete_all_cookies()
            for cookie in cookies:
                self.driver.add_cookie(cookie)
            
            self.driver.refresh()
            self.assertEqual(self.driver.current_url, "https://login3.scrape.center/")
            logging.info("成功验证登录状态保持")
        except Exception as e:
            logging.error(f"测试用例5执行失败：{str(e)}")
            raise

    def tearDown(self):
        logging.info("测试用例执行完成，关闭浏览器")
        self.driver.quit()
        logging.info("=== 测试用例执行结束 ===\n")

if __name__ == "__main__":
    unittest.main() 