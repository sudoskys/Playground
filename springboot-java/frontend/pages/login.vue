<template>
  <div class="min-h-screen w-full flex relative">
    <!-- 背景图片 -->
    <div class="absolute inset-0 z-0">
      <img
        src="/background.jpg"
        alt="背景"
        class="w-full h-full object-cover opacity-50"
      />
    </div>

    <!-- 主容器 -->
    <div class="relative z-10 w-full flex">
      <!-- 左侧登录面板 -->
      <div
        :class="[
          'w-full lg:w-1/2 p-8 flex items-center justify-center transition-all duration-500',
          { 'translate-x-0 opacity-100': !isRegister, 'translate-x-full opacity-0': isRegister }
        ]"
      >
        <div class="w-full max-w-sm space-y-8 bg-white/80 backdrop-blur-sm p-8 rounded-lg shadow-lg">
          <div class="text-center">
            <h2 class="text-2xl font-bold text-gray-900">登录账户</h2>
            <p class="mt-2 text-sm text-gray-600">欢迎回来！请登录您的账户</p>
          </div>

          <form @submit.prevent="handleLogin" class="space-y-6">
            <ElCard label="邮箱地址">
              <UInput
                v-model="loginForm.email"
                type="email"
                placeholder="请输入邮箱"
                :ui="{ base: 'transition-all duration-300' }"
              />
            </ElCard>

            <UFormGroup label="密码">
              <UInput
                v-model="loginForm.password"
                type="password"
                placeholder="请输入密码"
                :ui="{ base: 'transition-all duration-300' }"
              />
            </UFormGroup>

            <UButton
              type="submit"
              color="black"
              variant="solid"
              class="w-full"
              :loading="loginLoading"
            >
              登录
            </UButton>
          </form>

          <div class="text-center">
            <UButton
              variant="link"
              color="gray"
              @click="isRegister = true"
            >
              还没有账户？立即注册
            </UButton>
          </div>
        </div>
      </div>

      <!-- 右侧注册面板 -->
      <div
        :class="[
          'w-full lg:w-1/2 p-8 flex items-center justify-center transition-all duration-500',
          { 'translate-x-0 opacity-100': isRegister, '-translate-x-full opacity-0': !isRegister }
        ]"
      >
        <div class="w-full max-w-sm space-y-8 bg-white/80 backdrop-blur-sm p-8 rounded-lg shadow-lg">
          <div class="text-center">
            <h2 class="text-2xl font-bold text-gray-900">创建账户</h2>
            <p class="mt-2 text-sm text-gray-600">请填写以下信息完成注册</p>
          </div>

          <form @submit.prevent="handleRegister" class="space-y-6">
            <UFormGroup label="邮箱地址">
              <UInput
                v-model="registerForm.email"
                type="email"
                placeholder="请输入邮箱"
                :ui="{ base: 'transition-all duration-300' }"
              />
            </UFormGroup>

            <UFormGroup label="密码">
              <UInput
                v-model="registerForm.password"
                type="password"
                placeholder="请输入密码"
                :ui="{ base: 'transition-all duration-300' }"
              />
            </UFormGroup>

            <UButton
              type="submit"
              color="black"
              variant="solid"
              class="w-full"
              :loading="registerLoading"
            >
              注册
            </UButton>
          </form>

          <div class="text-center">
            <UButton
              variant="link"
              color="gray"
              @click="isRegister = false"
            >
              已有账户？立即登录
            </UButton>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { login, register } from '~/lib/api'

const isRegister = ref(false)
const loginLoading = ref(false)
const registerLoading = ref(false)

const loginForm = ref({
  email: '',
  password: '',
})

const registerForm = ref({
  email: '',
  password: '',
})

const handleLogin = async () => {
  try {
    loginLoading.value = true
    const response = await login(loginForm.value.email, loginForm.value.password)
    // 存储 token
    localStorage.setItem('token', response.token)
    // 跳转到首页
    navigateTo('/')
  }
  catch (error: any) {
    console.error(error)
    useToast().add({
      title: '登录失败',
      description: error.message,
      color: 'red',
    })
  }
  finally {
    loginLoading.value = false
  }
}

const handleRegister = async () => {
  try {
    registerLoading.value = true
    const response = await register(registerForm.value.email, registerForm.value.password)
    useToast().add({
      title: '注册成功',
      description: response.message,
      color: 'green',
    })
    // 自动切换到登录面板
    isRegister.value = false
  }
  catch (error: any) {
    console.error(error)
    useToast().add({
      title: '注册失败',
      description: error.message,
      color: 'red',
    })
  }
  finally {
    registerLoading.value = false
  }
}
</script>
