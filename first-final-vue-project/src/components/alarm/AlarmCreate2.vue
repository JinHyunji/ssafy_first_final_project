<template>
    <div>
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
                <label for="">영상 검색</label>
                <input type="text" name="videoId" v-model="store.savedAlarm.videoId">
            </div>
            <div>
                <label for="">이미지 추가</label>
                <input multiple type="file" name="img" @change="imageUpload($event.target.files)" ref="imageInput">
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

const fileToBase64 = function (file) {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => resolve(reader.result);
        reader.onerror = error => reject(error);
    });
}

const imageUpload = async (gotImage) => {
    if (gotImage) {
        try {
            // const imageName = gotImage[0].name;
            const data = await fileToBase64(gotImage[0]);
            store.savedAlarm.img = data;
        } catch (error) {
            alert("파일을 변환하는데 실패했습니다.")
        }
    }
}

</script>

<style scoped></style>