<template>
<div>
    <h2>AlarmModify</h2>
    <div>
        <h2>알람 설정 하기</h2>
        <div>
            <div>
                <label for="">알람 제목</label>
                <input type="text" v-model="store.alarmObject.title">
            </div>
            <div>
                <label for="">시작 시간</label>
                <input type="text" v-model="store.alarmObject.startTime">
            </div>
            <div>
                <label for="">종료 시간</label>
                <input type="text" v-model="store.alarmObject.endTime">
            </div>
            <div>
                <label for="">간격</label>
                <input type="text" v-model="store.alarmObject.term">
            </div>
            <div>
                <label for="">반복</label>
                <input type="text" v-model="store.alarmObject.cycle">
            </div>
            <div>
                <button @click="modified">수정</button>
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

const modified = function(){
    store.modifyAlarm();
}

const declined = function(){
    router.go(-1);
}

</script>

<style scoped>

</style>