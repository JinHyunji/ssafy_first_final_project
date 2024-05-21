<template>
    <TheHeaderNav/>
    <div class="container">
        <h2>Signup</h2>
        <div class="signup-inputs">
            <div class="input">
                <div class="row g-3 align-items-center">
                    <div class="col-auto">
                        <input @blur="checkId" v-model="user.userId" type="text" id="userId" placeholder="아이디 입력" class="form-control" aria-describedby="passwordHelpInline">
                    </div>
                    <div class="col-auto">
                        <span v-show="store.isDuplicate" id="passwordHelpInline" class="form-text" style="color: red;">
                            <i class="bi bi-ban" style="color: red;"> 아이디가 이미 존재합니다.</i>
                        </span>
                    </div>
                    <div id="passwordHelpBlock" class="form-text">
                        아이디는 0 ~ 20자 내로 입력해주세요.
                    </div>
                </div>
            </div>
            <div class="input">
                <div class="row g-3 align-items-center">
                    <div class="col-auto">
                        <input v-model="user.password" type="text" id="password" placeholder="비밀번호 입력" class="form-control" aria-describedby="passwordHelpInline">
                    </div>
                    <div id="passwordHelpBlock" class="form-text">
                        패스워드는 0 ~ 20자 내로 입력해주세요.
                    </div>
                </div>
            </div>
            <div class="input">
                <div class="row g-3 align-items-center">
                    <div class="col-auto">
                        <input v-model="user.nickname" type="text" id="nickname" placeholder="닉네임 입력" class="form-control" aria-describedby="passwordHelpInline">
                    </div>
                </div>
            </div>
            <div class="input">
                <div class="row g-3 align-items-center">
                    <div class="col-auto">
                        <input @blur="checkEmail" v-model="user.email" type="email" id="email" placeholder="이메일 입력" class="form-control" aria-describedby="passwordHelpInline">
                    </div>
                    <div class="col-auto">
                        <span v-show="emailError" id="passwordHelpInline" class="form-text" style="color: red;">
                            <i class="bi bi-ban" style="color: red;"> 올바른 이메일을 입력해주세요.</i>
                        </span>
                    </div>
                </div>
            </div>
        </div>
        <div class="signup-btns">
            <button @click="goBack" class="btn btn-outline-warning">취소</button>
            <button @click="createUser(user)" class="btn btn-outline-warning">가입하기</button>
        </div>
    </div>

    
    
</template>
 
<script setup>
import TheHeaderNav from '@/components/common/TheHeaderNav.vue';
import { ref } from 'vue';
import { useUserStore } from "@/stores/user";

const store = useUserStore();

const user = ref({
    userId: "",
    password: "",
    nickname: "",
    email: ""
});

const checkId = function() {
    store.checkId(user.value);
};

const emailError = ref(false);
const checkEmail = function() {
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    emailError.value = !emailPattern.test(user.value.email);
}

const createUser = function(user) {
    if (user.userId === '' || user.password === '' || user.nickname === '' || user.email === '') {
        alert("모든 항목을 입력해주세요");
        return;
    } else if (emailError.value||store.isDuplicate){
        alert("항목을 바르게 입력해주세요")
        return;
    } 
    store.createUser(user);
};

const goBack = function() {
    window.history.back();
}
</script>

<style scoped>
    .container {
        display: flex;
        flex-direction: column;
        align-items: center;
        margin: 60px;
    }
    .signup-inputs {
        display: flex;
        flex-direction: column;
        align-items: start;
        margin: 20px 0px 30px 300px;
        width: 630px;
    }
    .input {
        margin: 10px;
        /* height: 70px; */
    }
    input {
        width: 300px;
    }
    button {
        width: 145px;
        margin-right: 10px;
    }

    
</style>