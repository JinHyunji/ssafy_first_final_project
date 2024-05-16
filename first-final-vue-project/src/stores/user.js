import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import axios from 'axios'
import AlarmList from '@/components/alarm/AlarmList.vue'

const userUrl = "http://localhost:8080/api-user"

export const useUserStore = defineStore('user', () => {
 
  const user = ref('');

  const userLogin = function(loginUser){
    axios.post(`${userUrl}/login`, loginUser)
    .then((res)=>{
      console.log(res);
      user.value = res.data;
      sessionStorage.setItem('loginUser', res.data['userId']);
      // sessionStorage.setMaxInactiveInterval(0);

      router.push({name: 'alarmList'});
      console.log(sessionStorage.getItem("loginUser"))
    
    })
  }

  return { user, userLogin }
})
