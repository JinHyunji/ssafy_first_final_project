<template>
    <div>
        <h2>Nav bar</h2>
<<<<<<< HEAD
        <RouterLink to="/alarm/list">AlarmList</RouterLink>
=======
        <RouterLink v-if="loginUserCheck == null" :to="{name: 'login'}">login</RouterLink>
        <RouterLink v-else to="/alarm/list">AlarmList</RouterLink>
>>>>>>> seongyunoh
    </div>
</template>

<script setup>
<<<<<<< HEAD

=======
import { ref } from "vue";
    const loginUserCheck = sessionStorage.getItem('loginUser');
    console.log(loginUserCheck )
    const logincheck = ref("")
>>>>>>> seongyunoh
</script>

<style scoped>

</style>