<template>
    <div>
        <div>
            <h2>AlarmList</h2>
        </div>
        <div v-for="alarm in store.alarmList" :key="alarm.alarmId">
            <div>{{ alarm.title }}</div>
            <div>{{ alarm.exerType }}</div>
            <button @click="modifyAlarm(alarm.alarmId)">수정</button>
            <button @click="infoAlarm(alarm.alarmId)">상세</button>
            <button @click="deleteAlarm(alarm.alarmId)">삭제</button>
            <button @click="previewAlarm(alarm)">미리보기</button>
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

const previewAlarm = (alarm) => {
    if (!("Notification" in window)) {
        alert("이 브라우저는 알림 기능이 지원되지 않습니다.");
    } else if (Notification.permission === "granted") {
        const notification = new Notification(
            alarm.title,
            {
                image: "/public/images/" + alarm.img, // 경로를 바꾸라는 경고문이 뜨지만 바꾸면 이미지가 안뜸..
                body: "alarm.duration" + " | 척추수술 1700만원",
                requireInteraction: false // true -> 사용자가 동작하기 전까지 꺼지지 않음
            });
        notification.onclick = (event) => {
            event.preventDefault(); // prevent the browser from focusing the Notification's tab
            window.open("http://localhost:5173/popup", "_blank");
        };
    } else if (Notification.permission !== "denied") {
        Notification.requestPermission().then((permission) => {
            if (permission === "granted") {
                const notification = new Notification("알람이 허용되었습니다!", { body: "미리보기를 다시 눌러주세요!" });
            }
        });
    }
}

</script>

<style scoped>

</style>