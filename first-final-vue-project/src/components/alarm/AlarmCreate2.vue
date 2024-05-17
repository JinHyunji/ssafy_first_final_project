<template>
    <div>
        <h2>운동 설정하기</h2>
        <div>
            <div>
                <label for="">알람 제목</label>
                <input type="text">
            </div>
            <div>
                <label for="">시작 시간</label>
                <input type="time">
            </div>
            <div>
                <label for="">종료 시간</label>
                <input type="time">
            </div>
            <div>
                <label for="">간격</label>
                <input type="number">
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
                <button @click="saveAlarm"><RouterLink :to="{name:'alarmCreate'}">&lt;</RouterLink></button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useAlarmStore } from '@/stores/alarm';

const selectedDay = ref([]);
const alarm = ref({
    title: "",
    startTime: "",
    endTime: "",
    term: "",
    cycle: ""
});

const saveAlarm = function() {
    alarm.value.cycle = selectedDay.join(' ');
    store.saveAlarm.value = alarm.value;
}

</script>

<style scoped></style>