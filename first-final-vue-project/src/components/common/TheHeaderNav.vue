<template>
    <div>
        <h2>Logo</h2>
        <div v-if="loginUserCheck != null">
            <RouterLink to="/alarm/list">나의 알람 목록</RouterLink>
            <RouterLink @click.native="logout" to="/">로그아웃</RouterLink>
        </div>
    </div>
</template>

<script setup>
import { ref } from "vue";
import router from '@/router';

const loginUserCheck = sessionStorage.getItem('loginUser');

const logout = function() {
    sessionStorage.clear();
    router.push({name: 'home'}); 
}
</script>

<style scoped>
    
</style>