<template>
    <nav class="navbar shadow-sm p-3 mb-3y bg-body rounded">
        <div class="container">
            <RouterLink class="navbar-brand" to="/">
                <div class="logos">
                    <img src="/src/assets/img/Logo10.png" width="30px">
                    <h3 style="color: #FFC107;"> 알써 알써</h3>
                </div>
            </RouterLink>
        </div>
    </nav>
</template>

<script setup>
import { ref, onMounted } from "vue";
import router from '@/router';
import { useUserStore } from "@/stores/user";

const store = useUserStore();
const isLogined = ref(false);

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