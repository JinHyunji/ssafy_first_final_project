import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import router from '@/router'

const REST_USER_API = 'http://localhost:8080/api-user';

export const useUserStore = defineStore('user', () => {
  
  const createUser = function(user) {
    axios({
      url: `${REST_USER_API}/signup`,
      method: 'POST',
      data: user
    })
    .then((res) => {
      console.log(res.data);
      router.push({name: 'home'});
    })
    .catch((err) => {
      console.log(err);
    })
  }

  return { 
    createUser,

  }
})
