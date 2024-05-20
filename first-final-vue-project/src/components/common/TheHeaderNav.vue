<template>
    <div>
        <h2>Logo</h2>
        <div v-if="isLogined">
            <RouterLink to="/alarm/list">나의 알람 목록</RouterLink>
            <RouterLink @click.prevent="logout" to="/">로그아웃</RouterLink>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import router from '@/router';
import { useUserStore } from "@/stores/user";

// const loginUserCheck = ref(sessionStorage.getItem('loginUser'));

const store = useUserStore();
const isLogined = ref(false);

const restoreSession = function() {
    const savedUser = sessionStorage.getItem('loginUser');
    if (savedUser) {
        isLogined.value = true;
    }
}

onMounted(() => {
    restoreSession();
})

const logout = function() {
    store.userLogout();
}

</script>

<style scoped>
    
</style>