<template>
  <div>
    <TheHeaderNav />
      <h2>"HomeView"</h2>
    <RouterView />
  </div>
</template>

<script setup>
import Login from '@/components/user/Login.vue';
import Signup from '@/components/user/Signup.vue';
import TheHeaderNav from '../components/common/TheHeaderNav.vue';
</script>

<style scoped>

</style>
