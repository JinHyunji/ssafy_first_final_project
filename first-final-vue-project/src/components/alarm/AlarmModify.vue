<template>
    <div class="exer-setting-all ">
        <div class="exer-setting position-absolute top-20 start-50 translate-middle-x">
            <h2>알람 수정하기</h2>
            <div class="inputs">
                <div class="input">
                    <label for="inputPassword5" class="form-label">알람 제목</label>
                    <input v-model="store.alarmObject.title" type="text" id="inputPassword5" class="form-control"
                        aria-describedby="passwordHelpBlock">
                </div>
                <div class="input">
                    <label for="inputPassword5" class="form-label">시작 시간</label>
                    <input v-model="store.alarmObject.startTime" type="time" id="inputPassword5" class="form-control"
                        aria-describedby="passwordHelpBlock">
                </div>
                <div class="input">
                    <label for="inputPassword5" class="form-label">종료 시간</label>
                    <input v-model="store.alarmObject.endTime" type="time" id="inputPassword5" class="form-control"
                        aria-describedby="passwordHelpBlock">
                </div>
                <div class="input">
                    <label for="inputPassword5" class="form-label">간격</label>
                    <input v-model="store.alarmObject.term" type="number" class="form-control" placeholder="분 마다"
                        aria-label="default input example">
                </div>
                <div class="input">
                    <label for="inputPassword5" class="form-label">반복</label>
                    <div class="checkboxes d-flex flex-row mx-1">
                        <div class="form-check d-flex flex-row mx-1">
                            <input v-model="selectedDay" value="1" class="form-check-input" type="checkbox" id="flexCheckDefault1">
                            <label class="form-check-label" for="flexCheckDefault1">
                                월
                            </label>
                        </div>
                        <div class="form-check d-flex flex-row mx-1">
                            <input v-model="selectedDay" value="2" class="form-check-input" type="checkbox"
                                id="flexCheckDefault2">
                            <label class="form-check-label" for="flexCheckDefault2">
                                화
                            </label>
                        </div>
                        <div class="form-check d-flex flex-row mx-1">
                            <input v-model="selectedDay" value="3" class="form-check-input" type="checkbox"
                                id="flexCheckDefault3">
                            <label class="form-check-label" for="flexCheckDefault3">
                                수
                            </label>
                        </div>
                        <div class="form-check d-flex flex-row mx-1">
                            <input v-model="selectedDay" value="4" class="form-check-input" type="checkbox"
                                id="flexCheckDefault4">
                            <label class="form-check-label" for="flexCheckDefault4">
                                목
                            </label>
                        </div>
                        <div class="form-check d-flex flex-row mx-1">
                            <input v-model="selectedDay" value="5" class="form-check-input" type="checkbox"
                                id="flexCheckDefault5">
                            <label class="form-check-label" for="flexCheckDefault5">
                                금
                            </label>
                        </div>
                        <div class="form-check d-flex flex-row mx-1">
                            <input v-model="selectedDay" value="6" class="form-check-input" type="checkbox" id="flexCheckDefault6">
                            <label class="form-check-label" for="flexCheckDefault6">
                                토
                            </label>
                        </div>
                        <div class="form-check d-flex flex-row mb-3 mx-1">
                            <input v-model="selectedDay" value="7" class="form-check-input" type="checkbox"
                                id="flexCheckDefault7">
                            <label class="form-check-label" for="flexCheckDefault7">
                                일
                            </label>
                        </div>
                    </div>
                </div>
                <div class="input">
                    <label for="inputPassword5" class="form-label">운동 부위</label>
                    <input v-model="store.alarmObject.exerType" type="text" id="inputPassword5" class="form-control"
                        aria-describedby="passwordHelpBlock">
                </div>
                <div class="input">
                    <label for="inputPassword5" class="form-label">영상 추가</label>
                    <br>
                    <button type="button" class="btn btn-secondary" data-bs-toggle="modal"
                        data-bs-target="#exampleModal">
                        영상 검색
                    </button>
                </div>
                <div class="input">
                    <div class="mb-3">
                        <label for="inputPassword5" class="form-label">이미지 추가</label>
                        <input multiple type="file" name="img" @change="imageUpload($event.target.files)"
                            ref="imageInput" class="form-control" id="formFile">
                    </div>

                    <div>
                        <button @click="modified" type="button" class="btn btn-outline-warning mx-2">수정</button>
                        <button @click="declined" type="button" class="btn btn-outline-warning mx-2">취소</button>
                    </div>
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
    selectedDay.value = store.alarmObject.cycle.split("");
    console.log(selectedDay.value)
})

const modified = function () {
    store.alarmObject.cycle = selectedDay.value.join('');
    console.log(selectedDay)
    store.modifyAlarm();
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
            store.alarmObject.img = data;
            // console.log(data);
        } catch (error) {
            alert("파일을 변환하는데 실패했습니다.")
        }
    }
}

const declined = function () {
    router.go(-1);
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