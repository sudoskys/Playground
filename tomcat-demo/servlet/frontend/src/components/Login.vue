<template>
  <div class="flex justify-center mt-5">
    <!-- Login Card -->
    <el-card
        class="p-7 w-100 mt-6 rounded-lg"
        shadow="always"
        v-if="state === STATES.LOGIN"
    >
      <div>
        <el-text class="my-5 w-full text-3xl flex justify-center color-black">
          登录
        </el-text>
        <el-form @submit.prevent="login">
          <el-form-item>
            <el-input
                size="large"
                class="h-12"
                v-model="username"
                placeholder="用户名"
                required
            ></el-input>
          </el-form-item>

          <el-form-item>
            <el-input
                size="large"
                class="h-12"
                type="password"
                v-model="password"
                placeholder="密码"
                required
            ></el-input>
          </el-form-item>

          <el-form-item>
            <el-button
                type="primary"
                color="#00A8E9"
                native-type="submit"
                style="width: 100%"
                class="h-10 font-bold text-md"
            >
              登录
            </el-button>
          </el-form-item>

          <!-- Outline Register Button -->
          <div class="flex justify-end mt-5">
            <el-button
                type="text"
                @click="state = STATES.REGISTER"
            >
              没有账户？注册
            </el-button>
          </div>
        </el-form>
      </div>
    </el-card>

    <!-- Register Card -->
    <el-card
        class="p-7 w-100 mt-6 rounded-lg"
        shadow="always"
        v-if="state === STATES.REGISTER"
    >
      <el-text class="my-5 w-full text-3xl flex justify-center color-black">
        注册
      </el-text>
      <el-form @submit.prevent="register">
        <el-form-item>
          <el-input
              size="large"
              class="h-12"
              v-model="username"
              placeholder="用户名"
              required
          ></el-input>
        </el-form-item>

        <el-form-item>
          <el-input
              size="large"
              class="h-12"
              type="password"
              v-model="password"
              placeholder="密码"
              required
          ></el-input>
        </el-form-item>

        <!-- Email -->
        <el-form-item>
          <el-input
              size="large"
              class="h-12"
              v-model="email"
              placeholder="邮箱"
              required
          ></el-input>
        </el-form-item>

        <!-- User Role -->
        <el-form-item>
          <el-select
              v-model="role"
              placeholder="用户角色"
              style="width: 100%"
              class="h-12"
          >
            <el-option
                label="设计师"
                value="admin"
            ></el-option>
            <el-option
                label="用户"
                value="user"
            ></el-option>
          </el-select>
        </el-form-item>

        <!-- Register Button -->
        <el-form-item>
          <el-button
              type="primary"
              color="#00A8E9"
              native-type="submit"
              style="width: 100%"
              class="h-10 font-bold text-md"
          >
            注册
          </el-button>
        </el-form-item>

        <!-- Outline Login Button -->
        <div class="flex justify-end mt-5">
          <el-button
              type="text"
              @click="state = STATES.LOGIN"
          >
            已有账户？登录
          </el-button>
        </div>
      </el-form>
    </el-card>

    <!-- Already Logged In -->
    <div
        v-if="state === STATES.LOGGED_IN"
        class="flex justify-center mt-5"
    >
      <el-card
          class="p-7 w-100 mt-6 rounded-lg"
          shadow="always"
      >
        <el-result
            icon="success"
            title="You are logged in"
            :sub-title="statusMessage"
        >
          <template #extra>
            <el-button
                type="primary"
                color="#00A8E9"
                style="width: 100%"
                class="h-10 font-bold text-md"
                @click="logout"
            >
              登出
            </el-button>
          </template>
        </el-result>
        <el-button
            class="mt-3"
            @click="goToDashboard"
        >
          Go to Dashboard
        </el-button>
      </el-card>
    </div>
  </div>

  <el-notification
      v-if="notification.show"
      :title="notification.isError ? 'Error' : 'Success'"
      :message="notification.message"
      :type="notification.isError ? 'error' : 'success'"
  ></el-notification>
</template>
<script setup lang="ts">
import {ref, onMounted} from 'vue'
import {ElNotification} from 'element-plus'

// Define possible states
const STATES = {
  LOGIN: 'login',
  REGISTER: 'register',
  LOGGED_IN: 'logged_in',
} as const
// TypeScript type for state
type StateType = typeof STATES[keyof typeof STATES]
// Use the specific type `StateType` for state
const state = ref<StateType>(STATES.LOGIN)

const username = ref('')
const password = ref('')
const email = ref('')
const role = ref('')
const statusMessage = ref('Loading...')
const notification = ref({show: false, message: '', isError: false})

const showNotification = (message: string, isError = false) => {
  notification.value = {show: true, message, isError}
  ElNotification({
    title: isError ? 'Error' : 'Success',
    message,
    type: isError ? 'error' : 'success',
  })
}

const login = async () => {
  try {
    const response = await fetch('/api/login', {
      method: 'POST',
      headers: {'Content-Type': 'application/x-www-form-urlencoded'},
      body: new URLSearchParams({
        username: username.value,
        password: password.value,
      }),
    })
    const data = await response.text()
    if (response.ok) {
      state.value = STATES.LOGGED_IN
      showNotification('Login successful')
      updateLoginStatus()
    } else {
      showNotification(data, true)
    }
  } catch (error) {
    console.error('Error:', error)
    showNotification('Login failed, please try again.', true)
  }
}

const register = async () => {
  try {
    const response = await fetch('/api/register', {
      method: 'POST',
      headers: {'Content-Type': 'application/x-www-form-urlencoded'},
      body: new URLSearchParams({
        username: username.value,
        password: password.value,
        email: email.value,
        role: role.value,
      }),
    })
    if (response.ok) {
      showNotification('Registration successful')
      state.value = STATES.LOGIN
    } else {
      showNotification(response.statusText, true)
    }
  } catch (error) {
    console.error('Error:', error)
    showNotification('Registration failed, please try again.', true)
  }
}

const updateLoginStatus = () => {
  const cookies = document.cookie
      .split(';')
      .reduce((acc, cookie) => {
        const [name, value] = cookie.split('=').map((c) => c.trim())
        acc[name] = value
        return acc
      }, {} as Record<string, string>)

  const loggedInUser = cookies['token'] ? decodeToken(cookies['token']) : null
  if (loggedInUser) {
    state.value = STATES.LOGGED_IN
    statusMessage.value = `Logged in as: ${loggedInUser}`
  } else {
    state.value = STATES.LOGIN
    statusMessage.value = 'Not logged in'
  }
}

const decodeToken = (token: string) => {
  if (!token) return null
  try {
    const payloadEncoded = token.split('.')[1]
    const payloadDecoded = atob(payloadEncoded)
    const payload = JSON.parse(payloadDecoded)
    return payload.username
  } catch (error) {
    console.error('Error decoding token:', error)
    return null
  }
}

const logout = async () => {
  try {
    const response = await fetch('/api/logout', {
      method: 'POST',
      headers: {'Content-Type': 'application/x-www-form-urlencoded'},
    })
    const data = await response.text()
    if (response.ok) {
      showNotification(data)
      state.value = STATES.LOGIN
    } else {
      showNotification(data, true)
    }
    updateLoginStatus()
  } catch (error) {
    console.error('Error:', error)
    showNotification('Logout failed, please try again.', true)
  }
}

const goToDashboard = () => {
  window.location.href = '/protected/dashboard'
}

onMounted(updateLoginStatus)
</script>

<style scoped>
/* Optional styles */
.flex {
  display: flex;
}

.justify-center {
  justify-content: center;
}

.mt-5 {
  margin-top: 1.25rem;
}
</style>