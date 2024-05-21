<template>
    <TheHeaderNav/>
    <div onload="noBack();" onpageshow="if(event.persisted) noBack();" onunload="">
        <div class="position-absolute top-50 start-50 translate-middle">
            <div class="d-flex flex-column align-items-start" id="loginBox">
                <div class="mx-auto p-2 bd-highlight">


                    <h2>Login</h2>
                </div>
                <div class="d-flex flex-column align-items-start w-100" id="login_forms">
                    <div class="p-2 input-group mt-1">
                        <span class="input-group-text bi bi-person-heart" id="basic-addon1"></span>
                        <input type="text" class="form-control" placeholder="Username" aria-label="Username"
                            aria-describedby="basic-addon1" v-model="loginUser.userId">
                    </div>
                    <div class="p-2 input-group mt-1">
                        <span class="input-group-text bi bi-lock-fill" id="basic-addon1"></span>
                        <input @keyup.enter="loginTry" type="password" class="form-control" placeholder="Password" aria-label="Username"
                            aria-describedby="basic-addon1" v-model="loginUser.password">
                    </div>


                    <div class="p-2 d-grid gap-2 w-100" id="login_div">
                        <button type="button" class="btn btn-outline-warning w-100" @click="loginTry"
                            id="login_button">로그인</button>
                    </div>
                    <div id="signupLink" class="ms-auto d-flex flex-row-reverse bd-highlight">
                        <RouterLink :to="{ name: 'signup' }"
                            class="ms-auto p-2 bd-highlight text-decoration-none text-secondary">회원가입</RouterLink>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user';
import { ref } from 'vue';
import Signup from './Signup.vue';
import TheHeaderNav from '@/components/common/TheHeaderNav.vue';

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
#login_button{
    width: 260px;
}

#loginBox{
    width: 300px;
}
</style>