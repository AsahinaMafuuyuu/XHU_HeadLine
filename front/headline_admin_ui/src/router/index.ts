import { createRouter, createWebHistory } from 'vue-router'
import LayoutView from '@/views/layout/index.vue'
import UserView from '@/views/user/index.vue'
import LoginView from '@/views/Login/index.vue'

const routes = [
  {
    path: '/',
    component: LayoutView,
    redirect: '/user', // 根访问自动跳到 /user
    children: [
      { path: 'user', name: 'user', component: UserView },
      { path: 'login', name: 'login', component: LoginView },
    ],
  },
]

export default createRouter({
  history: createWebHistory(),
  routes,
})
