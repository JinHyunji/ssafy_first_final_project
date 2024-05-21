<template>
    <div>
        <h2>AlarmModify</h2>
        <div>
            <h2>알람 수정 하기</h2>
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
                    <div v-for="day in selectedDay" :key="day">
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
                    <label for="">운동 부위</label>
                    <input type="text" v-model="store.alarmObject.exerType">
                </div>
                <div>
                    <label for="">영상 검색</label>
                    <input type="text" name="videoId" v-model="store.alarmObject.videoId">
                </div>
                <div>
                    <label for="file">                        <div class="btn-upload">이미지 추가</div>
                    </label>
                    <input multiple type="file" accept=".png, .jpg" name="img"
                        @change="store.imageUpload($event.target.files)" ref="imageInput">
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
import { ref, onMounted, computed, onUpdated } from 'vue';


const route = useRoute();
const router = useRouter();
const store = useAlarmStore();

const selectedDay = ref([]);

onMounted(async () => {
    await store.getAlarm(route.params.alarmId);
    selectedDay.value = store.alarmObject.cycle;
    console.log(selectedDay.value)
})

const modified = function () {
    store.modifyAlarm();
}

const declined = function () {
    router.go(-1);
}

</script>

<style scoped></style>