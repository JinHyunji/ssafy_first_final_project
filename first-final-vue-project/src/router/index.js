import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import AlarmView from '@/views/AlarmView.vue';
import Signup from '@/components/user/Signup.vue';
import AlarmList from '@/components/alarm/AlarmList.vue';
import Login from '@/components/user/Login.vue';


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/signup',
      name: 'signup',
      component: Signup
    },
    {
      path: '/login',
      name: 'login',
      component: Login,
    },
    {
      path: '/alarm',
      name: 'alarm',
      component: AlarmView,
      children: [
        {
          path: 'list',
          name: 'alarmList',
          component: AlarmList
        }
      ]
    }
  ]
})

export default router
