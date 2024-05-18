<template>
  <TheHeaderNav/>
  <div>
      <h2>HomeView</h2>
      <RouterLink to="/login">login</RouterLink>
      <RouterLink to="/signup">Signup</RouterLink>
  </div>
</template>

<script setup>
import TheHeaderNav from '@/components/common/TheHeaderNav.vue';
</script>

<style scoped>

</style>
