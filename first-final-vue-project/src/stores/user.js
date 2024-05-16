import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import router from '@/router'

const REST_USER_API = 'http://localhost:8080/api-user';

export const useUserStore = defineStore('user', () => {

  const isDuplicate = ref(null);
  const checkId = function(user) {
    axios({
      url: `${REST_USER_API}/signup`,
      method: 'POST',
      data: user
    })
    .then((res) => {
      isDuplicate.value = res.status === 204;
    })
    .catch((err) => {
      console.log('중복검사 오류');
    })
  }
  
  const createUser = function(user) {
    axios({
      url: `${REST_USER_API}/signup`,
      method: 'POST',
      data: user
    })
    .then((res) => {
      alert('회원가입을 축하합니다!');
      router.push({name: 'home'});
    })
    .catch((err) => {
      alert('회원가입을 실패했습니다.');
    })
  }

  return { 
    isDuplicate,
    checkId,
    createUser,

  }

  
})
