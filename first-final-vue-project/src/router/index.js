import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import AlarmView from '@/views/AlarmView.vue';
<<<<<<< HEAD
import Signup from '@/components/user/Signup.vue';
import AlarmList from '@/components/alarm/AlarmList.vue';
=======
import AlarmList from '@/components/alarm/AlarmList.vue';
import Signup from '@/components/user/Signup.vue';
import Login from '@/components/user/Login.vue';
>>>>>>> seongyunoh

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
<<<<<<< HEAD
      component: HomeView
    },
    {
      path: '/signup',
      name: 'signup',
      component: Signup
=======
      component: HomeView,
      children:[
        {
          path: '/login',
          name: 'login',
          component: Login,
        },
        {
          path: '/signup',
          name: 'signup',
          component: Signup,
        }
      ]
>>>>>>> seongyunoh
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
<<<<<<< HEAD
    }
=======
    },
    
    
>>>>>>> seongyunoh
  ]
})

export default router
