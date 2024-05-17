<template>
    <div>
        <h2>시간 설정하기</h2>
        <div>
            <div>
                <label for="">알람 제목</label>
                <input v-model="alarm.title" type="text">
            </div>
            <div>
                <label for="">시작 시간</label>
                <input v-model="alarm.startTime" type="time">
            </div>
            <div>
                <label for="">종료 시간</label>
                <input v-model="alarm.endTime" type="time">
            </div>
            <div>
                <label for="">간격</label>
                <input v-model="alarm.term" type="number">
            </div>
            <div>
                <label for="">반복</label>
                <div>
                    <label>월</label>
                    <input v-model="selectedDay" value="1" name="mon" type="checkbox">
                    <label>화</label>
                    <input v-model="selectedDay" value="2" name="tue" type="checkbox">
                    <label>수</label>
                    <input v-model="selectedDay" value="3" name="wed" type="checkbox">
                    <label>목</label>
                    <input v-model="selectedDay" value="4" name="thur" type="checkbox">
                    <label>금</label>
                    <input v-model="selectedDay" value="5" name="fri" type="checkbox">
                    <label>토</label>
                    <input v-model="selectedDay" value="6" name="sat" type="checkbox">
                    <label>일</label>
                    <input v-model="selectedDay" value="7" name="sun" type="checkbox">
                </div>
            </div>
            <div>
                <RouterLink @click="saveAlarm" :to="{name:'alarmCreate2'}">&gt;</RouterLink>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useAlarmStore } from '@/stores/alarm';

const store = useAlarmStore();

const selectedDay = ref([]);
const alarm = ref({
    title: "",
    startTime: "",
    endTime: "",
    term: "",
    cycle: "",
    exerType: "",
    videoId: "",
    img: "",
    userId: ""
})

const saveAlarm = function() {
    alarm.value.cycle = selectedDay.value.join('');
    store.saveAlarm(alarm);
}

</script>

<style scoped></style>