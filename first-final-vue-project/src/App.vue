<template>
  <RouterView />
  <!-- <div v-for="item in text" :key="item.t">
    <input type="checkbox" v-model="ch" :value="item.v">{{ item.t }}
  </div> -->
</template>

<script setup>
  import { ref } from 'vue';
  const text=[{t:"봄", v:"spring"}, {t:"여름", v:"summer"}]
  const ch=ref([text[0].v]);

</script>

<style scoped>

</style>
