import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';
import router from '@/router';
import AlarmList from '@/components/alarm/AlarmList.vue';

const REST_USER_API = 'http://localhost:8080/api-user';

export const useUserStore = defineStore('user', () => {

  const isDuplicate = ref(false);
  const checkId = function(user) {
    axios({
      url: `${REST_USER_API}/dupl`,
      method: 'POST',
      data: user
    })
    .then((res) => {
      isDuplicate.value = false;
    })
    .catch((err) => {
      isDuplicate.value = true;
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

  const userLogin = function(loginUser){
    axios.post(`${REST_USER_API}/login`, loginUser)
    .then((res)=>{
      sessionStorage.setItem('loginUser', res.data.userId);
      router.push({name: 'alarmList'});
    })
    .catch((err) => {
      alert('로그인 실패');
    })
  }

  const userLogout = function() {
    axios.get(`${REST_USER_API}/logout`)
    .then((res) => {
      sessionStorage.clear();
      window.location.reload();
    })
    .catch((err) => {
      alert('로그아웃 실패');
    });
  }

  return { 
    isDuplicate,
    checkId,
    createUser, 
    userLogin,
    userLogout,
  }
})
