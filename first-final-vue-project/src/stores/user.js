import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';
import router from '@/router';
import AlarmList from '@/components/alarm/AlarmList.vue';

const REST_USER_API = 'http://localhost:8080/api-user';
const isLogined = ref(false);

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

  const user = ref('');


  const userLogin = function(loginUser){
    axios.post(`${REST_USER_API}/login`, loginUser)
    .then((res)=>{
      console.log(res.data);
      isLogined.value = true;
      user.value = res.data;
      // sessionStorage.setItem('loginUser', res.data['userId']);
      // sessionStorage.setMaxInactiveInterval(0);

      router.push({name: 'alarmList'});
      // console.log(sessionStorage.getItem("loginUser"))
      
    })
  }


  const userLogout = function() {
    axios.get(`${REST_USER_API}/logout`)
    .then((res) => {
      console.log(res.data);
      isLogined.value = false;
      user.value = '';
      console.log("로그아웃 응답을 받음");
    })
  }

  return { 
    isDuplicate,
    checkId,
    createUser,
    user, 
    userLogin,
    isLogined,
    userLogout,

  }
})
