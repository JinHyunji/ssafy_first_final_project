<template>
    <div class="position-absolute top-20 start-50 translate-middle-x mt-5">
        <div>
            <h2 class="my-3 ms-4 mb-4">{{ user }}님의 알람목록</h2>
        </div>

        <div class="d-flex align-self-start" id="listBox">
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
                <div class="my-2">{{ targetAlarm.exerType }} 운동을</div>
                <img class="my-2" :src="targetAlarmImgSrc" style="width:100px">
            </div>

        </div>
    </div>

</template>

<script setup>
import { ref, onMounted, onUpdated, watch, computed, onBeforeMount, onUnmounted } from 'vue';
import { useAlarmStore } from '@/stores/alarm';
import { useRouter } from 'vue-router';

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

onMounted(async () => {
    await store.getAlarmList();
    listForAlarm.value = store.alarmList;
    alarmMap.forEach((intervalId, alarmId) => {
        clearInterval(intervalId);
    });
    clearAlarm();
})

onUnmounted(() => {
    clearAlarm();
})

const alarmOn = function (alarm) {
    if (alarmMap.has(alarm.alarmId)) {
        clearInterval(alarmMap.get(alarm.alarmId));
        alarmMap.delete(alarm.alarmId);
    }

    store.callAlarm(alarm);
    const newAlarm = setInterval(() => store.callAlarm(alarm), alarm.term * 1000 * 60);
    alarmMap.set(alarm.alarmId, newAlarm);
}

// 처음 오는 알림은 시간을 계산해서 보내야함
// 시작시간과 종료시간을 조건에 추가해야함
// 알림 오는 날도 시작시간을 조건에 추가해야 함

watch(
    listForAlarm,
    (newValue, oldValue) => {
        console.log("알림 재설정 로드")
        
        console.log(alarmMap)
        clearAlarm();
        console.log(alarmMap)

        if (oldValue.length === 0) {
        //요일 가져오기
        var today = new Date().getDay();

        //처음 로드되었을 경우, 시간을 계산해서 일회성 알림을 보내야 함
        for (var j = 0; j < newValue.length; j++) {
            if (newValue[j].activate === "true" || newValue[j].activate === true) {

                if (newValue[j].term === 0) {
                    alert("알림 간격이 0인 알림을 비활성화 함");
                    changed(newValue[j]);
                    return
                }

                const alarmDay = newValue[j].cycle.split("").map(Number);
                if (alarmDay.includes(today)) {
                    if (store.calculateGap(newValue[j].endTime) < 0) {
                        const curAlarm = newValue[j];
                        // const srtTime = curAlarm.startTime.split(":").map(Number);
                        const calTime = store.calculateGap(newValue[j].startTime);

                        let plusGap = 0;
                        if (calTime >= 0) {
                            const modTerm = curAlarm.term * 60 * 1000;
                            plusGap = calTime % modTerm;
                        } else {
                            plusGap = Math.abs(calTime);
                        }
                        setTimeout(() => { alarmOn(curAlarm) }, plusGap)
                        console.log(curAlarm.title, "알림을 활성화했습니다.", new Date());
                    }
                }
            }
        }
        }
    },
    { deep: true },
)

const changed = function (alarm) {
    store.alarmOnOff(alarm.alarmId);
}

const clearAlarm = function () {
    alarmMap.forEach((newAlarm, alarmId) => {
        clearInterval(newAlarm);  // intervalId는 newAlarm을 의미
    });
    alarmMap.clear();
}

const transWeek = ["0", "월", "화", "수", "목", "금", "토", "일"];

const changeShowAlarm = function (alarm) {
    targetAlarm.value.title = alarm.title
    targetAlarm.value.startTime = alarm.startTime.split(":")[0] + "시 " + alarm.startTime.split(":")[1] + "분";
    targetAlarm.value.endTime = alarm.endTime.split(":")[0] + "시 " + alarm.endTime.split(":")[1] + "분";
    targetAlarm.value.term = alarm.term;
    targetAlarm.value.exerType = alarm.exerType;
    const weekArr = alarm.cycle.split("").map(Number);
    for (var i = 0; i < weekArr.length; i++) {
        targetAlarm.value.cycle += transWeek[weekArr[i]] + ", ";
    }
    targetAlarm.value.cycle = targetAlarm.value.cycle.slice(0, -2);
    console.log(targetAlarm.value.cycle)
    if (alarm.videoId !== undefined && alarm.videoId != null && alarm.videoId != "") {
        targetAlarmImgSrc.value = 'https://img.youtube.com/vi/' + alarm.videoId + "/mqdefault.jpg";
    } else {
        targetAlarmImgSrc.value = '/images/' + alarm.img;
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
    store.callAlarm(alarm);
}

</script>

<style scoped>
#listBox {
    min-width: 1000px;
}

#alarmBox {
    min-width: 320px;
    max-width: 700px;
}

#infoBox {
    max-width: 400px;
}
</style>