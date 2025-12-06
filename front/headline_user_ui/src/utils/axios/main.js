import axios from 'axios'
import { ElMessage } from 'element-plus'
import router from '@/router'

// 1. 创建 axios 实例
const http= axios.create({
  // 所有请求都会以 /api 开头，通过 Vite 代理转发到后端 http://localhost:7111
  baseURL: '/api',
  timeout: 10000,
})

// 请求拦截器：在每次请求前，自动从 localStorage 读取 token，放到请求头中
http.interceptors.request.use(
  config => {
    const token = localStorage.getItem('token')
    if (token) {
      config.headers['token'] = token
    }
    return config
  },
  error => {
    console.log(error)
    return Promise.reject(error)
  }
)

// 响应拦截器
http.interceptors.response.use(
  response => {
    const res = response.data
    return res
  },
  error => {
    console.log('err' + error)
    // 如果是 401 未授权，通常意味着 token 过期或无效
    if (error.response && error.response.status === 401) {
      ElMessage.error('登录已过期，请重新登录')
      localStorage.removeItem('token')
      router.push('/login')
    } else {
      ElMessage({
        message: error.message || '请求出错',
        type: 'error',
        duration: 5 * 1000
      })
    }
    return Promise.reject(error)
  }
)

export default http
