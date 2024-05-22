<template>
  <!-- <TheHeaderNav/> -->
  <div class="container"> 
    <img class="homeImg" src="/src/assets/img/Logo10.png">
    <h1 class="logo">Alarm Service</h1>
    <p>맞춤 운동 알람을 경험해보세요.</p>
    <div class="home-btns">
      <RouterLink to="/login">
        <button type="button" class="btn btn-outline-warning">로그인</button>
      </RouterLink>
      <RouterLink to="/signup">
        <button type="button" class="btn btn-outline-warning">회원가입</button>
      </RouterLink>
    </div>
  </div>
</template>

<script setup>
import TheHeaderNav from '@/components/common/TheHeaderNav.vue';
</script>

<style scoped>
  .container {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 150px;
  }
  .homeImg {
    width: 150px;

    /* height: 300px; */
    /* margin: 40px; */
  }
  .home-btns {
    margin-top: 70px;
  }
  button {
    margin: 5px;
    width: 170px;
  }
  .logo {
    margin-top: 10px;
    color: #FFC107;
    font-weight: 600
  }
  p {
    color: grey;;
  }
</style>
