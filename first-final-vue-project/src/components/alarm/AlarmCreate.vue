<template>
    <button @click="goCancel" style="margin-top: 20px; margin-left: 970px;" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
    <div class="time-setting">
        <h3>시간 설정하기</h3>
        <div class="inputs">
            <div class="input">
                <label for="inputPassword5" class="form-label">알람 제목</label>
                <input v-model="alarm.title" type="text" id="inputPassword5" class="form-control" aria-describedby="passwordHelpBlock">
            </div>
            <div class="input">
                <label for="inputPassword5" class="form-label">시작 시간</label>
                <input v-model="alarm.startTime" type="time" id="inputPassword5" class="form-control" aria-describedby="passwordHelpBlock">
            </div>
            <div class="input">
                <label for="inputPassword5" class="form-label">종료 시간</label>
                <input v-model="alarm.endTime" type="time" id="inputPassword5" class="form-control" aria-describedby="passwordHelpBlock">
            </div>
            <div class="input">
                <label for="inputPassword5" class="form-label">간격</label>        
                <input v-model="alarm.term" type="number" class="form-control" placeholder="분 마다" aria-label="default input example">
            </div>
            <div class="input">
                <label for="inputPassword5" class="form-label">반복</label>
                <div class="checkboxes">
                    <div class="form-check">
                        <input v-model="selectedDay" value="1" class="form-check-input" type="checkbox" id="flexCheckDefault">
                        <label class="form-check-label" for="flexCheckDefault">
                            월
                        </label>
                    </div>
                    <div class="form-check">
                        <input v-model="selectedDay" value="2" class="form-check-input" type="checkbox" id="flexCheckDefault">
                        <label class="form-check-label" for="flexCheckDefault">
                            화
                        </label>
                    </div>
                    <div class="form-check">
                        <input v-model="selectedDay" value="3" class="form-check-input" type="checkbox" id="flexCheckDefault">
                        <label class="form-check-label" for="flexCheckDefault">
                            수
                        </label>
                    </div>
                    <div class="form-check">
                        <input v-model="selectedDay" value="4" class="form-check-input" type="checkbox" id="flexCheckDefault">
                        <label class="form-check-label" for="flexCheckDefault">
                            목
                        </label>
                    </div>
                    <div class="form-check">
                        <input v-model="selectedDay" value="5" class="form-check-input" type="checkbox" id="flexCheckDefault">
                        <label class="form-check-label" for="flexCheckDefault">
                            금
                        </label>
                    </div>
                    <div class="form-check">
                        <input v-model="selectedDay" value="6" class="form-check-input" type="checkbox" id="flexCheckDefault">
                        <label class="form-check-label" for="flexCheckDefault">
                            토
                        </label>
                    </div>
                    <div class="form-check">
                        <input v-model="selectedDay" value="7" class="form-check-input" type="checkbox" id="flexCheckDefault">
                        <label class="form-check-label" for="flexCheckDefault">
                            일
                        </label>
                    </div>
                </div>
            </div>
            <div class="input-btn">
                <RouterLink @click="saveAlarm" :to="{name:'alarmCreate2'}">
                    <button type="button" class="btn btn-dark"><i class="bi bi-arrow-right"></i></button>
                </RouterLink>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useAlarmStore } from '@/stores/alarm';
import router from '@/router';

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

const goCancel = function() {
    router.push({name: 'alarmList'})
}

</script>

<style scoped>

 .checkboxes {
    display: flex;
    justify-content: space-evenly;
    width: 400px;
 }

 .time-setting {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 10px;
 }

 .inputs {
    margin-top: 10px;
 }

 .input {
    margin-bottom: 20px;
 }

 .input-btn {
    text-align: end;
 }

</style>