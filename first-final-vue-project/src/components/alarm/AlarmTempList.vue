<template>
    <div>
        <h3>TemplateList</h3>
        <div v-for="template in store.templates" 
        :key="template.tempId">
            <button @click="clickTemp(template.tempId)">{{ template.exerType }}</button>
        </div>
    </div>
</template>

<script setup>
import { useAlarmStore } from '@/stores/alarm';
import { onMounted, ref } from 'vue';
const store = useAlarmStore();

onMounted(() => {
  store.getTemplates();
});

const clickTemp = function(tempId) {
    store.clickTemp(tempId);
}

</script>

<style scoped>

</style>