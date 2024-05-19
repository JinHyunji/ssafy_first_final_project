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
                <label for="">영상 추가</label>
                <!-- <input type="text" name="videoId" v-model="store.savedAlarm.videoId"> -->
                <!-- Button trigger modal -->
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                영상 검색
                </button>

            <!-- Modal -->
            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">영상 검색</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="container">
                        <div class="input-group mb-3">
                        <span class="input-group-text"><i class="bi bi-search"></i></span>
                        <input
                            type="text"
                            class="form-control"
                            placeholder="검색어 입력"
                            v-model="keyword"
                            @keyup.enter="search"
                        />
                        <button class="btn btn-outline-primary" @click="search">검색</button>
                        </div>
                        <YoutubeListItem
                        v-for="(video,index) in store.videos"
                        :key="video.id.videoId"
                        :video="video"
                        :index="index"
                        :current="current"
                        ></YoutubeListItem>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                    <button type="button" class="btn btn-primary">저장</button>
                </div>
                </div>
            </div>
            </div>
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
import { useYoutubeStore } from '@/stores/youtube';
import YoutubeListItem from '@/components/youtube/YoutubeListItem.vue';

const store = useAlarmStore();
const youtubeStore = useYoutubeStore();

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

const keyword = ref('');

const search = function () {
  youtubeStore.youtubeSearch(keyword.value);
};

</script>

<style scoped></style>