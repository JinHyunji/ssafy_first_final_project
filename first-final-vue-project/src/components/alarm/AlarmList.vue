<template>
    <div>
        <h2>AlarmList</h2> 
    </div>
    <div v-for="alarm in store.alarmList" :key="alarm.alarmId">
            {{ alarm.title }}      
    </div>
    <button @click="createAlarm">새로운 알람 생성</button>
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

const createAlarm = function(){
    router.push({name: 'alarmCreate'});
}

</script>

<style scoped>

</style>