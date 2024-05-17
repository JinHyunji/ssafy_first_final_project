<template>
    <div>
    <div>
        <h2>AlarmList</h2> 
    </div>
    <div v-for="alarm in store.alarmList" :key="alarm.alarmId">
            <div>{{ alarm.title }}</div>
            <button @click="modifyAlarm(alarm.alarmId)">수정</button>
            <button @click="infoAlarm(alarm.alarmId)">상세</button>
            <button @click="deleteAlarm(alarm.alarmId)">삭제</button>
            <!-- <RouterLink :to="{ name: 'alarmModify'}">수정</RouterLink> -->
            <!-- <RouterLink :to="{ name: 'alarmInfo' }">상세</RouterLink> -->     
    </div>
    <button @click="createAlarm">새로운 알람 생성</button>
</div>
</template>

<script setup>

import TheHeaderNav from '../common/TheHeaderNav.vue';
import { onMounted } from 'vue';
import { useAlarmStore } from '@/stores/alarm';
import {useRouter} from 'vue-router';

const store = useAlarmStore();
const router = useRouter();

onMounted(()=>{
    store.getAlarmList();
})

const modifyAlarm = function(id){
    router.push({ name: 'alarmModify', params: { alarmId: id} });
}

const infoAlarm = function(id){
    router.push({ name: 'alarmInfo', params: { alarmId: id} });
}

const deleteAlarm = function(id){
    store.deleteAlarm(id);
}

const createAlarm = function(){
    router.push({name: 'alarmCreate'});
}

</script>

<style scoped>

</style>