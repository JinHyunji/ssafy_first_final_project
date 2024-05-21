<template>
    <div class="position-absolute top-20 start-50 translate-middle-x mt-5">
        <div>
            <h2 class="my-3 ms-4">{{ user }}님의 알람목록</h2>
        </div>

        <div class="d-flex align-self-start" id="listBox">
            <div class="d-flex flex-column mb-3 mx-3">
                <button type="button" class="btn btn-link px-1 ms-auto p-0 text-decoration-none text-body"
                    @click="createAlarm">새로운 알람 생성</button>
                <div v-for="alarm in listForAlarm" :key="alarm.alarmId" class="d-flex w-100" id="alarmBox">
                    <input type="radio" class="btn-check p-2 flex-grow-1" name="options" :id="'option' + alarm.alarmId"
                        autocomplete="off" data-bs-toggle="collapse" href="#alarmInfo" role="button"
                        aria-expanded="false" aria-controls="collapseExample" @click="changeShowAlarm(alarm)">
                    <label class="btn px-2 flex-grow-1 mx-1" :for="'option' + alarm.alarmId">{{ alarm.title }}</label>
                    <button type="button" class="btn btn-link px-1 text-decoration-none text-secondary"
                        @click="modifyAlarm(alarm.alarmId)">수정</button>
                    <!-- <button type="button" class="btn btn-link px-1" @click="infoAlarm(alarm.alarmId)">상세</button> -->
                    <button type="button" class="btn btn-link px-1 text-decoration-none text-secondary"
                        @click="deleteAlarm(alarm.alarmId)">삭제</button>
                    <button type="button" class="btn btn-link px-1 text-decoration-none text-secondary"
                        @click="previewAlarm(alarm)">미리보기</button>
                    <div class="form-check form-switch pt-2">
                        <input class="form-check-input" type="checkbox" role="switch" id="flexSwitchCheckChecked"
                            v-model="alarm.activate" @change="changed(alarm)">
                    </div>
                </div>
            </div>

            <div class="card card-body d-flex flex-column w-50 p-4" id="infoBox">
                <div>{{ targetAlarm.title }}</div>
                <div>{{ targetAlarm.startTime }}부터</div>
                <div>{{ targetAlarm.endTime }}까지</div>
                <div>{{ targetAlarm.term }}분 마다</div>
                <div>{{ targetAlarm.exerType }} 운동을</div>
                <img :src="getImgSrc(targetAlarm.img)">
                <div>cycle: {{ targetAlarm.cycle }}</div>
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
});

const user = sessionStorage.getItem('loginUser');

const listForAlarm = ref([]);
const alarmMap = new Map();

onMounted(async () => {
    await store.getAlarmList();
    listForAlarm.value = store.alarmList;
})

onUnmounted(() => {
    alarmMap.forEach((newAlarm) => {
        clearInterval(newAlarm);
    })
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
        alarmMap.forEach((newAlarm) => {
            console.log(newAlarm, "이 초기화 됩니다.");
            clearInterval(newAlarm);
        })
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
                        // console.log(calculateGap(newValue[j].endTime));
                        if (calculateGap(newValue[j].endTime) < 0) {
                            const curAlarm = newValue[j];
                            // const srtTime = curAlarm.startTime.split(":").map(Number);
                            const calTime = calculateGap(newValue[j].startTime);

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

const calculateGap = function (timeString) {
    const timeArr = timeString.split(":").map(Number);

    const now = new Date();
    const calTime = new Date().setHours(timeArr[0], timeArr[1], 0, 0);

    const timeGap = now - calTime;

    return timeGap;
}

const changed = function (alarm) {
    store.alarmOnOff(alarm.alarmId);
}

const changeShowAlarm = function (alarm) {
    targetAlarm.value.title = alarm.title
    targetAlarm.value.startTime = alarm.startTime.split(":")[0]+"시 "+alarm.startTime.split(":")[1]+"분";
    targetAlarm.value.endTime = alarm.endTime.split(":")[0]+"시 "+alarm.endTime.split(":")[1]+"분";
    targetAlarm.value.term = alarm.term;
    targetAlarm.value.exerType = alarm.exerType;
    targetAlarm.value.cycle = alarm.exerType;


}

const getImgSrc = function (imgLink) {
    return '/images/' + imgLink;
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
    min-width: 900px;
}

#alarmBox {
    min-width: 320px;
    max-width: 600px;
}

#infoBox {
    max-width: 400px;
}
</style>