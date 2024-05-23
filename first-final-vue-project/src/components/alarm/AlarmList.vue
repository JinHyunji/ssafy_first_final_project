<template>
    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">ChatGPT</h1><h1 class="modal-title fs-5" id="exampleModalLabel" v-if="gptAnswer.length===0">의 답변을 기다리는 중</h1><h1 class="modal-title fs-5" id="exampleModalLabel" v-else>의 답변</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body" id="modal-box">
                    <div v-if="gptAnswer.length===0" id="loading" class="position-absolute top-50 start-50 translate-middle">
                        <div class="spinner-grow text-warning" role="status">
                            <span class="visually-hidden">Loading...</span>
                        </div>
                    </div>
                    {{ gptAnswer }}
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-outline-warning" data-bs-dismiss="modal" >확인</button>
                </div>
            </div>
        </div>
    </div>
    <div class="position-absolute top-20 start-50 translate-middle-x mt-3">
        <div>
            <h2 class="p-3 mb-2">{{ user }}님의 알람목록</h2>
        </div>
        <div class="input-group mb-3">
            <input type="text" class="form-control" placeholder="ChatGPT에게 물어보기" aria-label="Recipient's username"
                aria-describedby="button-addon2" v-model="gptInput">
            <button class="btn btn-warning " type="button" id="button-addon2" data-bs-toggle="modal"
                data-bs-target="#exampleModal" @click="getGPTResponse()">검색</button>
        </div>



        <div class="d-flex align-self-start shadow p-3 mb-5 bg-body-white rounded" id="listBox">
            <div class="d-flex flex-column mb-3 mx-3 my-1">
                <button type="button" class="btn btn btn-dark px-2 ms-auto p-1 mb-2" @click="createAlarm">새로운 알람
                    생성</button>
                <div v-for="alarm in listForAlarm" :key="alarm.alarmId" class="d-flex w-100 m-2" id="alarmBox">
                    <input type="radio" class="btn-check p-2 flex-grow-1" name="options" :id="'option' + alarm.alarmId"
                        autocomplete="off" data-bs-toggle="collapse" href="#alarmInfo" role="button"
                        aria-expanded="false" aria-controls="collapseExample" @click="changeShowAlarm(alarm)">
                    <label class="btn px-2 flex-grow-1 mx-1" :for="'option' + alarm.alarmId">{{ alarm.title }}</label>
                    <button type="button" class="btn btn-link px-2 text-decoration-none text-secondary mx-1"
                        @click="modifyAlarm(alarm.alarmId)">수정</button>
                    <!-- <button type="button" class="btn btn-link px-1" @click="infoAlarm(alarm.alarmId)">상세</button> -->
                    <button type="button" class="btn btn-link px-2 text-decoration-none text-secondary mx-1"
                        @click="deleteAlarm(alarm.alarmId)">삭제</button>
                    <button type="button" class="btn btn-link px-2 text-decoration-none text-secondary mx-1"
                        @click="previewAlarm(alarm)">미리보기</button>
                    <div class="form-check form-switch pt-2 mx-2">
                        <input class="form-check-input" type="checkbox" role="switch" id="flexSwitchCheckChecked"
                            v-model="alarm.activate" @change="changed(alarm)">
                    </div>
                </div>
            </div>

            <div class="card card-body d-flex flex-column align-items-center w-50 p-4 mx-4" id="infoBox">
                <h4 class="my-3 ">{{ targetAlarm.title }}</h4>
                <div class="my-2">{{ targetAlarm.cycle }} 요일에</div>
                <div class="my-2">{{ targetAlarm.startTime }}부터 {{ targetAlarm.endTime }}까지</div>
                <div class="my-2">{{ targetAlarm.term }}분 마다</div>
                <div class="my-2">{{ targetAlarm.exerType }} 운동</div>
                <img class="my-2" :src="targetAlarmImgSrc" style="width:300px">
            </div>

        </div>
    </div>

</template>

<script setup>
import { ref, onMounted, onUpdated, watch, computed, onBeforeMount, onUnmounted } from 'vue';
import { useAlarmStore } from '@/stores/alarm';
import { useRouter } from 'vue-router';
import OpenAI from 'openai';

const store = useAlarmStore();
const router = useRouter();
const targetAlarm = ref({
    title: "",
    startTime: "",
    endTime: "",
    term: "",
    exerType: "",
    img: "",
    videoId: "",
    cycle: "",
});
const targetAlarmImgSrc = ref("/src/assets/img/Logo10.png")

const user = JSON.parse(sessionStorage.getItem('loginUser')).nickname;

const listForAlarm = ref([]);
const alarmMap = new Map();
let gptAnswer = ref("");
const gptInput = ref("");

onMounted(async () => {
    await store.getAlarmList();
    listForAlarm.value = store.alarmList;
})

onUnmounted(async () => {
    console.log(alarmMap)
    await clearAlarm();
    console.log(alarmMap)
})

const alarmOn = function (alarm) {
    console.log(alarmMap)
    if (alarmMap.has(alarm)) {
        console.log(alarm.title, "알림을 초기화합니다.")
        clearInterval(alarm);
    }

    store.callAlarm(alarm);
    const newAlarm = setInterval(() => store.callAlarm(alarm), alarm.term * 1000 * 60);
    alarmMap.set(alarm, newAlarm);
    console.log(alarm.title, "알림 자동전송이 설정되었습니다.")
    console.log(alarmMap)
}

watch(
    listForAlarm,
    async (newValue, oldValue) => {

        if (oldValue.length === 0) {
            console.log("알림 재설정 로드")
            console.log("처음 로드되었을 때의 map 정보", alarmMap)

            //처음 로드되었을 경우, 시간을 계산해서 일회성 알림을 보내야 함
            for (var j = 0; j < newValue.length; j++) {
                if (!alarmMap.has(newValue[j])) {
                    if (newValue[j].activate === "true" || newValue[j].activate === true) {
                        if (newValue[j].term === 0) {
                            alert("알림 간격이 0인 알림을 비활성화 함");
                            changed(newValue[j]);
                            router.go(0);
                        }
                        var today = new Date().getDay();
                        const alarmDay = newValue[j].cycle.split("").map(Number);
                        if (alarmDay.includes(today)) {
                            if (store.calculateGap(newValue[j].endTime) < 0) {
                                const curAlarm = newValue[j];
                                let calTime = store.calculateGap(newValue[j].startTime);
                                const modTerm = curAlarm.term * 60 * 1000;
                                let plusGap = 0;
                                if (calTime >= 0) {
                                    plusGap = modTerm - (calTime % modTerm);
                                } else {
                                    plusGap = Math.abs(calTime);
                                }

                                alarmMap.set(curAlarm, 0);
                                setTimeout(() => { alarmOn(curAlarm) }, plusGap)
                                console.log(curAlarm.title, "알림을 활성화했습니다.", new Date(), " 알림까지 남은 시간 : ", Math.floor(plusGap / 60 / 1000), "분 ", Math.floor(plusGap / 1000) % 60, "초");
                                console.log(alarmMap)
                            }
                        }
                    }
                }
            }
        } else {
            alarmMap.forEach((newAlarm, alarm) => {
                if (!alarm.activate) {
                    console.log(alarm.title, "알림의 자동전송을 삭제합니다.")
                    clearInterval(newAlarm);  // intervalId는 newAlarm을 의미
                    alarmMap.delete(alarm);
                }
            });
        }
    },
    { deep: true },
)

const changed = function (alarm) {
    store.alarmOnOff(alarm.alarmId);
}

const clearAlarm = function () {
    console.log("clearAlarm")
    alarmMap.forEach((newAlarm, alarm) => {
        console.log(newAlarm, alarm);
        clearInterval(newAlarm);  // intervalId는 newAlarm을 의미
    });
    alarmMap.clear();
}

const transWeek = ["0", "월", "화", "수", "목", "금", "토", "일"];

const changeShowAlarm = function (alarm) {
    targetAlarm.value = { cycle: "" };
    targetAlarm.value.title = alarm.title
    targetAlarm.value.startTime = alarm.startTime.split(":")[0] + "시 " + alarm.startTime.split(":")[1] + "분";
    targetAlarm.value.endTime = alarm.endTime.split(":")[0] + "시 " + alarm.endTime.split(":")[1] + "분";
    targetAlarm.value.term = alarm.term;
    targetAlarm.value.exerType = alarm.exerType;
    const weekArr = alarm.cycle.split("").map(Number);
    weekArr.sort();

    for (var i = 0; i < weekArr.length; i++) {
        targetAlarm.value.cycle += transWeek[weekArr[i]] + ", ";
    }
    targetAlarm.value.cycle = targetAlarm.value.cycle.slice(0, -2);

    if (alarm.videoId !== null && alarm.videoId.length === 11) {
        targetAlarmImgSrc.value = 'https://img.youtube.com/vi/' + alarm.videoId + "/mqdefault.jpg";
    } else if (alarm.img.length === 17) {
        targetAlarmImgSrc.value = '/images/' + alarm.img;
    }
}

const getGPTResponse = async () => {
    try {
        gptAnswer.value = "";
        const openai = new OpenAI({
            apiKey: ``,
            dangerouslyAllowBrowser: true,
        })

        const response = await openai.chat.completions.create({
            messages: [
                {
                    role: 'user',
                    content: `${gptInput.value} 라는 질문에 대해 줄바꿈해서 답변해줘`,
                },
            ],
            model: 'gpt-3.5-turbo',
        })
        gptAnswer.value = response.choices[0].message.content;
        console.log('chatGPT 결과: ', response.choices[0].message.content)

    } catch (error) {
        console.log('chatGPT: 🚨 에러가 발생했습니다.')
    }
}

const modifyAlarm = function (id) {
    router.push({ name: 'alarmModify', params: { alarmId: id } });
}

const deleteAlarm = function (id) {
    store.deleteAlarm(id);
}

const createAlarm = function () {
    router.push({ name: 'alarmCreate' });
}

const previewAlarm = function (alarm) {
    store.callAlarm(alarm, "false");
}

</script>

<style scoped>
#listBox {
    min-width: 1000px;
}

#alarmlistBox {
    min-width: 420px;
}

#alarmBox {
    min-width: 320px;
    max-width: 700px;
}

#infoBox {
    max-width: 400px;
}

#modal-box{
    min-width: 400px;
    min-height: 600px;
}

</style>