<template>
    <nav class="navbar">
        <div class="container">
            <RouterLink class="navbar-brand" to="/">
                <img src="/src/assets/img/Logo10.png" width="40">
                <span style="color: #FFC107;"> Alarm</span>
            </RouterLink>
            <div class="nav-icons" v-if="isLogined">
                <RouterLink to="/alarm/list">
                    <button type="button" class="btn btn-outline-warning">나의 알람 목록</button>
                </RouterLink>
                <RouterLink @click.prevent="logout" to="/">
                    <button type="button" class="btn btn-outline-secondary">로그아웃</button>
                </RouterLink>
            </div>
        </div>
    </nav>
</template>

<script setup>
import { ref, onMounted } from "vue";
import router from '@/router';
import { useUserStore } from "@/stores/user";

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
    a {
        text-decoration: none;
        margin-left: 20px;
    }

    .navbar {
        border-bottom: 1px solid #e7e7e7;
    }
</style>