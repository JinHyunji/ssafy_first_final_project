<template>
    <div>
        <h2>Logo</h2>
        <div v-show="store.isLogined">
            <RouterLink to="/alarm/list">나의 알람 목록</RouterLink>
            <RouterLink @click="logout" to="/logout">로그아웃</RouterLink>
        </div>
    </div>
</template>

<script setup>
import { ref, computed } from "vue";
import router from '@/router';
import { useUserStore } from "@/stores/user";

// const loginUserCheck = ref(sessionStorage.getItem('loginUser'));

const store = useUserStore();

const logout = function() {
    // sessionStorage.clear();
    store.userLogout();
}

</script>

<style scoped>
    
</style>