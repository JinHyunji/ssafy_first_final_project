<template>
    <div class="temp">
        <h3>템플릿 선택하기</h3>
        <span>현재 <span class="fw-bolder" style="color: #FFC107;">{{ tempCnt }}</span>명이 이 템플릿을 사용 중입니다.</span>
        <div v-for="template in store.templates" 
        :key="template.tempId" class="list-group ">
            <a @click="clickTemp(template.tempId, template.count)" class="list-group-item list-group-item-action list-group-item-light">
                {{ template.exerType }} 템플릿
            </a>
        </div>
    </div>
</template>

<script setup>
import { useAlarmStore } from '@/stores/alarm';
import { onMounted, ref } from 'vue';
const store = useAlarmStore();
const tempCnt = ref(0);

onMounted(() => {
  store.getTemplates();
});

const clickTemp = function(tempId, tempCount) {
    store.clickTemp(tempId);
    tempCnt.value = tempCount;
}

</script>

<style scoped>
    h3 {
        margin-bottom: 30px;
    }
    .temp {
        margin-left: 220px;
        margin-right: 120px;
        text-align: center;
    }

    .list-group {
        margin-top: 20px;
    }
</style>