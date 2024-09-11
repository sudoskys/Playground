<template>
  <div class="flex">
    <el-card
        class="p-7 w-100 mt-6 rounded-lg"
        shadow="always"
    >

      <div v-if="isLoggingIn">
        <el-text class="my-5 w-full text-3xl flex justify-center color-black">
          登录
        </el-text>
        <el-form @submit.prevent="login">
          <el-form-item>
            <el-input
                size="large"
                class="h-12"
                v-model="username"
                placeholder="用户名" required>
            </el-input>
          </el-form-item>

          <el-form-item>
            <el-input
                size="large"
                class="h-12"
                type="password"
                v-model="password"
                placeholder="密码" required>

            </el-input>
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

        </el-form>
      </div>
      <div
          v-if="!isLoggingIn"
          class="flex justify-center mt-5"
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
      </div>
      <div
          v-if="!isLoggingIn"
          class="flex justify-center mt-5"
      >
        <el-button @click="goToDashboard"
        >
          Go to Dashboard
        </el-button>
      </div>
    </el-card>
  </div>
  <el-notification v-if="notification.show" :title="notification.isError ? 'Error' : 'Success'"
                   :message="notification.message" :type="notification.isError ? 'error' : 'success'"></el-notification>
</template>

<script setup lang="ts">
import {ref, onMounted} from 'vue'
import {ElNotification} from 'element-plus'

const username = ref('')
const password = ref('')
const statusMessage = ref('Loading...')
const notification = ref({show: false, message: '', isError: false})
const isLoggingIn = ref(false)

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
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
      body: new URLSearchParams({
        'username': username.value,
        'password': password.value
      })
    })
    const data = await response.text()
    showNotification(data, !response.ok)
    updateLoginStatus()
  } catch (error) {
    console.error('Error:', error)
    showNotification('Login failed, please try again.', true)
  } finally {
  }
}

const updateLoginStatus = () => {
  const cookies = document.cookie.split(';').reduce((acc, cookie) => {
    const [name, value] = cookie.split('=').map(c => c.trim())
    acc[name] = value
    return acc
  }, {} as Record<string, string>)

  const username = cookies['token'] ? decodeToken(cookies['token']) : null
  isLoggingIn.value = !username
  statusMessage.value = username ? `Logged in as: ${username}` : 'Not logged in'
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
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
      body: new URLSearchParams({})
    })
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`)
    }
    const data = await response.text()
    showNotification(data)
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
</style>