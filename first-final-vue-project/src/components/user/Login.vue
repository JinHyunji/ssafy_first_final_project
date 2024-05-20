<template>
    <TheHeaderNav/>
    <div onload="noBack();" onpageshow="if(event.persisted) noBack();" onunload="">
        <h2>Login</h2>
        <input type="text" v-model="loginUser.userId">
        <input type="password" v-model="loginUser.password">
        <button @click="loginTry">로그인</button>
    </div>
</template>

<script setup>
import TheHeaderNav from '@/components/common/TheHeaderNav.vue';
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
    // console.log(loginUser);
    // console.log("로그인 시도");
    store.userLogin(loginUser.value);
}

window.history.forward();
function noBack(){window.history.forward();}

</script>

<style scoped>

</style>