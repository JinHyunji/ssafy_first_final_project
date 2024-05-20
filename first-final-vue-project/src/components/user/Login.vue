<template>
    <div onload="noBack();" onpageshow="if(event.persisted) noBack();" onunload="">
        <div class="position-absolute top-50 start-50 translate-middle">
            <div class="d-flex flex-column align-items-start">
                <div class="mx-auto p-2 bd-highlight">
                <h2 >Sign In</h2></div>
                <div class="p-2 bd-highlight" id="login_forms" >
                    
                    <div class="input-group mb-2">
                        <span class="input-group-text bi bi-person-heart" id="basic-addon1"></span>
                        <input type="text" class="form-control" placeholder="Username" aria-label="Username"
                            aria-describedby="basic-addon1" v-model="loginUser.userId">
                    </div>
                    <div class="input-group mb-2">
                        <span class="input-group-text bi bi-lock-fill" id="basic-addon1"></span>
                        <input type="text" class="form-control" placeholder="Password" aria-label="Username"
                            aria-describedby="basic-addon1" v-model="loginUser.password">
                    </div>
                    <div class="d-grid gap-2">
                    <button type="button" class="btn btn-outline-warning" @click="loginTry" id="login_button">로그인</button></div>
                    <div id="signupLink" class="d-flex flex-row-reverse bd-highlight">
                    <RouterLink :to="{name:'signup'}" class="ms-auto p-2 bd-highlight text-warning">회원가입</RouterLink></div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user';
import { ref } from 'vue';
import Signup from './Signup.vue';

const store = useUserStore();
const loginUser = ref({
    userId: '',
    password: '',
    nickname: 'nickname',
    email: 'email',
})

const loginTry = function () {
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
function noBack() { window.history.forward(); }

</script>

<style scoped>
</style>