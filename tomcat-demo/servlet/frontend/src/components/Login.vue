<template>
  <div class="card">
    <h1 class="text-5xl font-bold mb-4 text-center">Login</h1>
    <form @submit.prevent="login">
      <input type="text" v-model="username" placeholder="Username" required>
      <input type="password" v-model="password" placeholder="Password" required>
      <button class="hover:bg-gray-300" type="submit">Login</button>
    </form>

    <div class="status" id="status">{{ statusMessage }}</div>

    <div>
      <button @click="logout">Logout</button>
      <button @click="goToDashboard">Go to Dashboard</button>
    </div>
  </div>

  <div id="notification" class="notification" :class="{ show: notification.show }">{{ notification.message }}</div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'

const username = ref('')
const password = ref('')
const statusMessage = ref('Loading...')
const notification = ref({ show: false, message: '', isError: false })

const showNotification = (message: string, isError = false) => {
  notification.value = { show: true, message, isError }
  setTimeout(() => {
    notification.value.show = false
  }, 3000)
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
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`)
    }
    const data = await response.text()
    showNotification(data)
    updateLoginStatus()
  } catch (error) {
    console.error('Error:', error)
    showNotification('Login failed, please try again.', true)
  }
}

const updateLoginStatus = () => {
  const cookies = document.cookie.split(';').reduce((acc, cookie) => {
    const [name, value] = cookie.split('=').map(c => c.trim())
    acc[name] = value
    return acc
  }, {} as Record<string, string>)

  const username = cookies['token'] ? decodeToken(cookies['token']) : null
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
body {
  background-color: #e5e5e5;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  margin: 0;
}

.notification {
  display: none;
  position: fixed;
  top: 1rem;
  right: 1rem;
  background-color: rgba(0, 0, 0, 0.7);
  color: white;
  padding: 1rem;
  border-radius: 0.25rem;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  z-index: 1000;
  transition: opacity 0.3s ease;
}

.notification.show {
  display: block;
  opacity: 1;
}

.card {
  background-color: white;
  padding: 2rem;
  border-radius: 0.5rem;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;
  text-align: center;
}

form button {
  background-color: white;
  color: black;
  border: 1px solid #000;
}

button {
  background-color: black;
  color: white;
  border: 1px solid #000;
  padding: 0.75rem 1.5rem;
  margin-top: 1rem;
  border-radius: 0.25rem;
  cursor: pointer;
  transition: background-color 0.3s, transform 0.2s;
  width: 100%;
}

button:hover {
  background-color: #444;
}

button:active {
  transform: scale(0.98);
}

input {
  width: 100%;
  padding: 0.75rem;
  margin-top: 0.5rem;
  margin-bottom: 1rem;
  border: 1px solid #ccc;
  border-radius: 0.25rem;
}

.status {
  margin-top: 1.5rem;
  color: #555;
}
</style>