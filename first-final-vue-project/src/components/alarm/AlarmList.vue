<template>
    <div>
        <div>
            <h2>AlarmList</h2>
        </div>
        <div v-for="alarm in store.alarmList" :key="alarm.alarmId">
            <div>{{ alarm.title }}</div>
            <div>{{ alarm.exerType }}</div>
            <button @click="modifyAlarm(alarm.alarmId)">수정</button>
            <button @click="infoAlarm(alarm.alarmId)">상세</button>
            <button @click="deleteAlarm(alarm.alarmId)">삭제</button>
            <button @click="previewAlarm(alarm)">미리보기</button>
            <div class="form-check form-switch">
                <input class="form-check-input" type="checkbox" role="switch" id="flexSwitchCheckChecked"
                    v-model="alarm.activate" @change="changed(alarm)">
                <label class="form-check-label" for="flexSwitchCheckDefault">임시로 써놓은 알람 activate값 :
                    {{ alarm.activate }}</label>
            </div>
        </div>
        <button @click="createAlarm">새로운 알람 생성</button>
    </div>
</template>

<script setup>
import { ref, onMounted, onUpdated, watch, computed, onBeforeMount } from 'vue';
import { useAlarmStore } from '@/stores/alarm';
import { useRouter } from 'vue-router';

const store = useAlarmStore();
const router = useRouter();

const listForAlarm = ref([]);

const changed = function (alarm) {
    // 여기에 활성화여부 업데이트하는 요청 날리기
    // 그냥 from에서 to로 요청 날릴 때
    // change될 때마다 요청 날리는게 부담스럽다면 요청을 바꾸고 30초 정도 뒤에 요청을 날릴 수도 있음 -> 근데 굳이 그럴 필요 있나?
    store.alarmOnOff(alarm.alarmId);
}

onMounted(() => {
    store.getAlarmList();
    listForAlarm.value = computed(()=> store.alarmList);
})

// onUpdated(() => {
//     console.log("알림 업데이트함")
// })

console.log(listForAlarm);

watch(
    listForAlarm,
    (newValue, oldValue) => {
        console.log(newValue);
        console.log(oldValue)
        for (var i = 0; i < newValue.length; i++) {
            //여기에 알림 시간 계산하는거 들어가야 함
            // console.log(ringList[i].exerType);
            if (newValue[i].activate === true) {
                console.log("활성화됨")
            }
        }
    },
    { deep: true },
    {immediate: true}
)

const ringTheAlarm = function (ringList) {
    
}


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