import type { AxiosError, AxiosRequestConfig, InternalAxiosRequestConfig } from 'axios'
import { useRuntimeConfig } from '#app'

import axios from 'axios'
import { z } from 'zod'
// Error handling
export class ApiError extends Error {
  constructor(
    public status: number,
    message: string,
    public errorType?: string | undefined,
    public caller?: string,
  ) {
    super(`${caller ? `[${caller}] ` : ''}${message || status}`)
    this.name = 'ApiError'
  }
}

// API instance
const api = axios.create({
  baseURL: 'http://127.0.0.1:8080/api',
  headers: {
    'Content-Type': 'application/json',
  },
})

api.interceptors.request.use((config: InternalAxiosRequestConfig) => {
  const token = localStorage.getItem('token')
  if (token) {
    config.headers = config.headers ?? {}
    config.headers.Authorization = `Bearer ${token}`
  }
  return config
})

// 创建一个函数来更新 baseURL
export function updateApiBaseUrl() {
  const config = useRuntimeConfig()
  if (config.public.apiBase) {
    api.defaults.baseURL = config.public.apiBase as string
  }
}

// Generic API call function
async function apiCall<T>(
  method: 'get' | 'post' | 'put' | 'delete',
  url: string,
  data?: any,
  schema?: z.ZodSchema<T>,
  config?: AxiosRequestConfig,
  caller?: string,
): Promise<T> {
  try {
    const response = await api[method](url, method === 'get' ? { params: data } : data, config)
    return schema ? schema.parse(response.data?.data) : response.data?.data
  }
  catch (error) {
    if (error instanceof z.ZodError) {
      throw new ApiError(400, error.errors.map(err => err.message).join(', '), 'validation_error', caller)
    }
    if (axios.isAxiosError(error)) {
      const axiosError = error as AxiosError<{ message: string, errorType: string }>
      throw new ApiError(
        axiosError.response?.status || 500,
        axiosError.response?.data?.message || '',
        axiosError.response?.data?.errorType,
        caller,
      )
    }
    throw new ApiError(500, '', undefined, caller)
  }
}
export function login(email: string, password: string) {
  return apiCall('post', '/auth/login', { email, password }, z.object({
    token: z.string(),
    user: z.object({
      id: z.number(),
      email: z.string(),
      role: z.string(),
    }),
  }), undefined, 'login')
}

export function register(email: string, password: string) {
  return apiCall('post', '/auth/register', { email, password }, z.object({
    token: z.string(),
    message: z.string(),
  }), undefined, 'register')
}

export function logout() {
  return apiCall('post', '/auth/logout', undefined, z.object({
    message: z.string(),
  }), undefined, 'logout')
}

export function getCurrentUser() {
  return apiCall('get', '/auth/user', undefined, z.object({
    id: z.string(),
    email: z.string(),
    role: z.string(),
  }), undefined, 'getCurrentUser')
}

export function ping() {
  return apiCall('post', '/auth/ping', undefined, z.object({
    message: z.string(),
    token: z.string(),
  }), undefined, 'ping')
}

export default defineNuxtPlugin(() => {
  updateApiBaseUrl()
})
