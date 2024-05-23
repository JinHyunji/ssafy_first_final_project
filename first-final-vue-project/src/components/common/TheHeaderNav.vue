<template>
    <nav class="navbar shadow-sm p-3 mb-3 bg-body rounded">
        <div class="container">

            <RouterLink class="navbar-brand" :to="{name:'alarmList'}">

                <div class="logos">
                    <img src="/src/assets/img/Logo10.png" width="30px
                    
                    ">
                    <h3 style="color: #FFC107;"> 알써 알써</h3>
                </div>

            </RouterLink>
            <div class="nav-icons" v-if="isLogined">
                <RouterLink to="/alarm/list">
                    <button type="button" class="btn btn-outline-warning">나의 알람 목록</button>
                </RouterLink>
                <RouterLink>
                    <button  @click="logout" class="btn btn-outline-secondary">로그아웃</button>
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

const logoLink = isLogined === true ? "/" : "/alarm/list";

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
    Swal.fire({
        text: "로그아웃 하시겠습니까?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes"
    }).then((result) => {
    if (result.isConfirmed) {
        Swal.fire({
        text: "로그아웃 되었습니다.",
        icon: "success"
        });
        store.userLogout();
    }
    });
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


    .logos {
        display: flex;
        align-items: center;
    }

    h3 {
        margin-left: 10px;
        margin-bottom: 0px;
    }

</style>