import {createRouter, createWebHistory} from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/home'
    },
    {
      path: '/tabs',
      name: 'tabs',
      component: () => import('@/views/tabs/TabsView.vue'),
      children: [
        {name: 'home', path: '/home', component: () => import('@/views/tabs/home/HomeView.vue')},
        {name: 'me', path: '/me', component: () => import('@/views/tabs/me/MeView.vue')},
        {name: 'myReg', path: '/myReg', component: () => import('@/views/tabs/me/registration/index.vue')},
        {name: 'doc', path: '/doc', component: () => import('@/views/tabs/doc/index.vue')}
      ]
    },
    {
      path: '/doctorList',
      name:'doctorList',
      component: () => import('@/views/doctor/index.vue'),
    },
    {
      path: '/registration',
      name:'registration',
      component: () => import('@/views/doctor/registration/index.vue'),
    },
    {
      path: '/login',
      name:'login',
      component: () => import('@/views/auth/Login/index.vue'),
    },
    {
      path: '/register',
      name:'register',
      component: () => import('@/views/auth/Register/index.vue'),
    },
    {
      path: '/post/detail',
      name:'postDetail',
      component: () => import('@/views/tabs/home/componens/NoticeDetail.vue'),
    },

    {
      path: '/doctorRate',
      name:'doctorRate',
      component: () => import('@/views/tabs/me/registration/Evaluate.vue'),
    },
  ]
})

export default router
