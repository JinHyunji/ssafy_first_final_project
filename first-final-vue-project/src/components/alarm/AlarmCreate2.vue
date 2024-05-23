<template>
    <button @click="goCancel" style="margin-top: 30px; margin-left: 1120px;" class="btn-close" data-bs-dismiss="modal"
        aria-label="Close"></button>
    <div class="exer-setting-all">
        <AlarmTemplateList />
        <div class="exer-setting">
            <h3>운동 설정하기</h3>
            <div class="inputs">
                <div class="input">
                    <label for="inputPassword5" class="form-label">운동 부위</label>
                    <input v-model="store.savedAlarm.exerType" type="text" id="inputPassword5" class="form-control"
                        aria-describedby="passwordHelpBlock">
                </div>
                <div class="input">
                    <label class="form-label">영상 추가</label>
                    <br>
                    <div class="d-flex flex-column mb-3">
                        <button type="button" class="btn btn-secondary m-1" data-bs-toggle="modal"
                            data-bs-target="#exampleModal">
                            영상 검색
                        </button>
                        <iframe v-if="store.savedAlarm.videoId != null && store.savedAlarm.videoId.length === 11" width="500px" height="280px"
                            :src="getVideoSrc(store.savedAlarm.videoId)" title="YouTube video player" frameborder="0"
                            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                            referrerpolicy="strict-origin-when-cross-origin" allowfullscreen>
                        </iframe>
                    </div>
                    <button @click="deleteVideo" v-if="youtubeStore.checkedVideoTitle" class="btn-close"
                        aria-label="Close"></button>

                    <!-- Modal -->
                    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                        aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h1 class="modal-title fs-5" id="exampleModalLabel">영상 검색</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div class="container">
                                        <div class="input-group mb-3">
                                            <span class="input-group-text"><i class="bi bi-search"></i></span>
                                            <input type="text" class="form-control" placeholder="검색어 입력"
                                                v-model="keyword" @keyup.enter="search" />
                                            <button class="btn btn-outline-secondary" @click="search">검색</button>
                                        </div>
                                        <YoutubeListItem v-for="(video, index) in youtubeStore.videos"
                                            :key="video.id.videoId" :video="video" :index="index" :current="current">
                                        </YoutubeListItem>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="input">
                    <div class="mb-3">
                        <labe class="form-label">이미지 추가</labe>
                        <input multiple type="file" name="img" @change="imageUpload($event.target.files)"
                            ref="imageInput" class="form-control" id="formFile">
                    </div>
                    <div class="btns">
                        <RouterLink :to="{ name: 'alarmCreate' }"><button type="button" class="btn btn-dark">
                                <i class="bi bi-arrow-left"></i></button>
                        </RouterLink>
                        <button @click="createAlarm" class="btn btn-dark">
                            <i class="bi bi-check-lg"></i>
                        </button>
                    </div>
                </div>
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
import router from '@/router';

const store = useAlarmStore();
const youtubeStore = useYoutubeStore();

const createAlarm = function () {
    store.createAlarm();
    youtubeStore.checkedVideoTitle = null;
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

const getVideoSrc = function (videoLink) {
    console.log('www.youtube.com?v=' + videoLink);
    return "https://www.youtube.com/embed/" + videoLink;
}

const keyword = ref('');

const search = function () {
    youtubeStore.youtubeSearch(keyword.value);
};

const deleteVideo = function () {
    youtubeStore.checkedVideoTitle = null;
    store.savedAlarm.videoId = null;
}

const goCancel = function () {
    router.push({ name:'alarmList'});
}
</script>

<style scoped>
.exer-setting-all {
    display: flex;
    margin-top: 10px;
}

.exer-setting {
    display: inline-flex;
    flex-direction: column;
    align-items: center;
    /* margin: 0px auto; */
}

.inputs {
    margin-top: 10px;
    width: 500px;
}

.input {
    margin-bottom: 20px;
}

.btns {
    text-align: end;
    margin-top: 30px;
}

.btns>button {
    margin-left: 10px;
}
</style>