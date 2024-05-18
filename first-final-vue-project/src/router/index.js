import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import AlarmView from '@/views/AlarmView.vue';
import Signup from '@/components/user/Signup.vue';
import AlarmList from '@/components/alarm/AlarmList.vue';
import Login from '@/components/user/Login.vue';
import AlarmCreate from '@/components/alarm/AlarmCreate.vue';
import PopupView from '@/views/PopupView.vue';

import AlarmModify from '@/components/alarm/AlarmModify.vue';
import AlarmInfo from '@/components/alarm/AlarmInfo.vue';

import AlarmCreate2 from '@/components/alarm/AlarmCreate2.vue';


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
      path: '/logout',
      redirect: '/'
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
        },
        {
          path: 'create',
          name: 'alarmCreate',
          component: AlarmCreate
        },
        {
          path: 'modify/:alarmId',
          name: 'alarmModify',
          component: AlarmModify
        },
        {
          path: 'info/:alarmId',
          name: 'alarmInfo',
          component: AlarmInfo
        },
        {
          path: 'create',
          name: 'alarmCreate2',
          component: AlarmCreate2
        },
      ]
    },
    {
      path: '/popup',
      name: 'popUp',
      component: PopupView,
    }
  ]
})

export default router
