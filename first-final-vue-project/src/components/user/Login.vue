<template>
    <div>
        <h2>Login</h2>
        <input type="text" v-model="loginUser.userId">
        <input type="password" v-model="loginUser.password">
        <button @click="loginTry">로그인</button>
    </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user';
import { ref } from 'vue';

const store = useUserStore();
const loginUser = ref({
    userId: '',
    password: '',
    nickname: 'nickname',
    email:'email',
})

const loginTry = function(){
    // if(loginUser.userId.length === 0){
    //     return alert("아이디 입력");
    // }
    // if(loginUser.password.length === 0){
    //     return alert("비밀번호 입력");
    // }
    console.log(loginUser);
    console.log("로그인 시도");
    store.userLogin(loginUser.value);
}

</script>

<style scoped>

</style>