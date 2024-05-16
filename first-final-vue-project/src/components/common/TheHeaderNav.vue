<!-- <template>
    <div>
        <h2>Logo</h2>
        <div v-show="loginUserCheck != null">
            <RouterLink to="/alarm/list">나의 알람 목록</RouterLink>
            <RouterLink @click="logout" to="/">로그아웃</RouterLink>
        </div>
    </div>
</template>

<script setup>
import { ref, computed } from "vue";
import router from '@/router';

const loginUserCheck = ref(sessionStorage.getItem('loginUser'));


const logout = function() {
    sessionStorage.clear();
}

</script>

<style scoped>
    
</style> -->

<template>
    <div>
      <h2>Logo</h2>
      <div v-if="isLoggedIn">
        <RouterLink :to="{ name: 'alarmList' }">나의 알람 목록</RouterLink>
        <RouterLink @click="logout" to="/">로그아웃</RouterLink>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted, onUnmounted } from 'vue';
  import { useRouter } from 'vue-router';
  
  const isLoggedIn = ref(sessionStorage.getItem('loginUser') !== null);
  const router = useRouter();
  
  const updateLoginStatus = () => {
    isLoggedIn.value = sessionStorage.getItem('loginUser') !== null;
  };
  
  const handleStorageChange = (event) => {
    if (event.key === 'loginUser') {
      updateLoginStatus();
    }
  };
  
  const logout = () => {
    sessionStorage.removeItem('loginUser');
    updateLoginStatus();
    router.push({ name: 'home' });
  };
  
  onMounted(() => {
    window.addEventListener('storage', handleStorageChange);
  });
  
  onUnmounted(() => {
    window.removeEventListener('storage', handleStorageChange);
  });
  </script>
  
  <style scoped>
  /* 스타일을 여기에 추가할 수 있습니다 */
  </style>
  