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
      Swal.fire({
        text: "회원가입을 축하합니다!",
        icon: "success"
      });
      router.push({name: 'login'});
    })
    .catch((err) => {
      Swal.fire({
        text: "회원가입을 실패했습니다.",
        icon: "error"
      });
    })
  }

  const userLogin = function(loginUser){
    axios.post(`${REST_USER_API}/login`, loginUser)
    .then((res)=>{
      Swal.fire({
        text: `${res.data.nickname}` + "님 반갑습니다!",
        icon: "success"
      });

      sessionStorage.setItem('loginUser', JSON.stringify(res.data));
      router.push({name: 'alarmList'});
    })
    .catch((err) => {

      Swal.fire({
        text: "일치하는 회원 정보가 없습니다.",
        icon: "error"
      });

      alert('일치하는 회원 정보가 없습니다.');

    })
  }

  const userLogout = function() {
    axios.get(`${REST_USER_API}/logout`)
    .then((res) => {
      sessionStorage.clear();
      router.replace({name: 'home'})
    })
    .catch((err) => {
      Swal.fire({
        text: "로그아웃 실패",
        icon: "error"
      });
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
