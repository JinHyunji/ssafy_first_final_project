<template>
    <div class="temp">
        <h3>템플릿 선택하기</h3>
        <div v-for="template in store.templates" 
            :key="template.tempId" class="list-group ">
            <a @click="clickTemp(template.tempId)" class="list-group-item list-group-item-action list-group-item-light">
                {{ template.exerType }} 템플릿
            </a>
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
    h3 {
        margin-bottom: 30px;
    }
    .temp {
        margin-left: 250px;
        margin-right: 100px;
        text-align: center;
    }

    .list-group {
        margin-top: 20px;
    }
</style>