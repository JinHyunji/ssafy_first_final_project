<template>
    <div >
        <h2>운동 설정하기</h2>
        <div>
            <AlarmTemplateList />
        </div>
        <div>
            <div>
                <label for="">운동 부위</label>
                <input type="text" v-model="store.savedAlarm.exerType">
            </div>
            <div>
                <label for="" >영상 검색</label>
                <input type="text" name="videoId" v-model="store.savedAlarm.videoId">
            </div>
            <div>
                <label for="">이미지 추가</label>
                <input type="text" name="img" v-model="store.savedAlarm.img">
            </div>
            <div>
                <RouterLink :to="{name:'alarmCreate'}">&lt;</RouterLink>
                <button @click="createAlarm">생성</button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useAlarmStore } from '@/stores/alarm';
import AlarmTemplateList from '@/components/alarm/AlarmTempList.vue';

const store = useAlarmStore();

const createAlarm = function() {
    store.createAlarm();
}

</script>

<style scoped></style>