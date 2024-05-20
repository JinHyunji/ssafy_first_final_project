<template v-show="isLoaded">
    <div>
        <div>
            <h2>AlarmList</h2>
        </div>
        <button @click="createAlarm">새로운 알람 생성</button>

        <div v-for="alarm in listForAlarm" :key="alarm.alarmId" :id="'accordion' + alarm.alarmId" class="accordion">
            <div class="accordion-item">
                <h2 class="accordion-header" :id="'heading' + alarm.alarmId">
                    <button class="accordion-button" type="button" data-bs-toggle="collapse"
                        :data-bs-target="'#collapse' + alarm.alarmId" aria-expanded="true"
                        :aria-controls="'collapse' + alarm.alarmId">
                        {{ alarm.title }}
                        <div>cycle: {{ store.alarmObject.cycle }}</div>
                    </button>
                </h2>
                <div :id="'collapse' + alarm.alarmId" class="accordion-collapse collapse"
                    :aria-labelledby="'heading' + alarm.alarmId" data-bs-parent="#accordionExample">
                    <div class="accordion-body">
                        <button @click="modifyAlarm(alarm.alarmId)">수정</button>
                        <button @click="infoAlarm(alarm.alarmId)">상세</button>
                        <button @click="deleteAlarm(alarm.alarmId)">삭제</button>
                        <button @click="previewAlarm(alarm)">미리보기</button>
                        <div class="form-check form-switch">
                            <input class="form-check-input" type="checkbox" role="switch" id="flexSwitchCheckChecked"
                                v-model="alarm.activate" @change="changed($event,alarm)">
                            <label class="form-check-label" for="flexSwitchCheckDefault">임시로 써놓은 알람 activate값 :
                                {{ alarm.activate }}</label>
                        </div>
                        <div>title: {{ store.alarmObject.title }}</div>
                        <div>startTime: {{ store.alarmObject.startTime }}</div>
                        <div>endTime: {{ store.alarmObject.endTime }}</div>
                        <div>term: {{ store.alarmObject.term }}</div>
                        <div>exerType: {{ store.alarmObject.exerType }}</div>
                        <div>img: {{ store.alarmObject.img }}</div>
                        <div>userId: {{ store.alarmObject.userId }}</div>
                        <div>videoId: {{ store.alarmObject.videoId }}</div>
                        
                    </div>
                </div>
            </div>

        </div>


    </div>
</template>




<script setup>
import { ref, onMounted, onUpdated, watch, computed, onBeforeMount } from 'vue';
import { useAlarmStore } from '@/stores/alarm';
import { useRouter } from 'vue-router';

const store = useAlarmStore();
const router = useRouter();
const isLoaded = ref(false);

const listForAlarm = ref([]);

const changed = function (event, alarm) {
    event.stopPropagation();
    store.alarmOnOff(alarm.alarmId);
}

onMounted(async () => {
    await store.getAlarmList();
    listForAlarm.value = store.alarmList;
    // for (var i = 0; i < listForAlarm.value.length; i++) {
    //     if (listForAlarm.value[i].activate === true) {
    //         listForAlarm.value[i].expand = true;
    //         listForAlarm.value[i].collapse = "show";
    //     } else {
    //         listForAlarm.value[i].expand = false;
    //         listForAlarm.value[i].collapse = "";
    //     }
    // }
    // console.log(listForAlarm.value)
})

// const expandAlarmInfo = (alarm) => {
//     alarm.expand = alarm.expand === false ? true : false;
//     alarm.collapse = alarm.collapse === "" ? "show" : "";
// }

// onUpdated(() => {
//     console.log("알림 업데이트함")
// })

const modifyAlarm = function (id) {
    router.push({ name: 'alarmModify', params: { alarmId: id } });
}

const infoAlarm = function (id) {
    router.push({ name: 'alarmInfo', params: { alarmId: id } });
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

<style scoped></style>