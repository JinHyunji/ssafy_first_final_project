<template>
    <div>
        <h2>Signup</h2>
        <div>
            <label>아이디</label>
            <input @blur="checkId" v-model="user.userId" type="text" id="userId" placeholder="아이디를 입력해주세요.">
            <span v-if="store.isDuplicate">아이디가 이미 존재합니다.</span>
        </div>
        <div>
            <label>비밀번호</label>
            <input v-model="user.password" type="text" id="password" placeholder="비밀번호를 입력해주세요.">
        </div>
        <div>
            <label>닉네임</label>
            <input v-model="user.nickname" type="text" id="nickname" placeholder="닉네임을 입력해주세요.">
        </div>
        <div>
            <label>이메일</label>
            <input @blur="checkEmail" v-model="user.email" type="email" id="email" placeholder="이메일을 입력해주세요.">
            <span v-if="emailError">올바른 이메일을 입력해주세요.</span>
        </div>
        <div>
            <button @click="createUser(user)">가입하기</button>
        </div>
    </div>
</template>
 
<script setup>
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
</script>

<style scoped>

</style>