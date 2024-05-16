<template>
    <div>
        <h2>Nav bar</h2>
        <RouterLink v-if="loginUserCheck == null" :to="{name: 'login'}">login</RouterLink>
        <RouterLink v-else to="/alarm/list">AlarmList</RouterLink>
    </div>
</template>

<script setup>
import { ref } from "vue";
    const loginUserCheck = sessionStorage.getItem('loginUser');
    console.log(loginUserCheck )
    const logincheck = ref("")
</script>

<style scoped>

</style>