<template>
    <div>
        <h2>AlarmInfo</h2>
        <div>
            <div>
                <div>{{store.alarmObject.title}}</div>
                <div>{{store.alarmObject.startTime}}</div>
                <div>{{store.alarmObject.endTime}}</div>
                <div>{{store.alarmObject.term}}</div>
                <div>{{store.alarmObject.cycle}}</div>
                <div>
                    <button @click="modifyAlarm(store.alarmObject.alarmId)">수정</button>
                    <button @click="declined">취소</button>
                </div>
            </div>
        </div>
    </div>
    </template>
    
    <script setup>
    import { useRoute, useRouter } from 'vue-router';
    import { useAlarmStore } from '@/stores/alarm';
    import { onMounted } from 'vue';
    
    
    const route = useRoute();
    const router = useRouter();
    const store = useAlarmStore();
    
    onMounted(()=>{
        store.getAlarm(route.params.alarmId);
    })
    
    const modifyAlarm = function(id){
    router.push({ name: 'alarmModify', params: { alarmId: id} });
}
    
    const declined = function(){
        router.go(-1);
    }
    
    </script>
    
    <style scoped>
    
    </style>