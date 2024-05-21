import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';
import router from '@/router';

const REST_ALARM_API = 'http://localhost:8080/api-alarm/alarm';
const REST_TEMP_API = 'http://localhost:8080/api-temp/temp';

export const useAlarmStore = defineStore('alarm', () => {

  const alarmList = ref([]);

  const getAlarmList = async function () {
    await axios.get(REST_ALARM_API)
      .then((res) => {
        alarmList.value = res.data;
      })
  }

  const alarmObject = ref({});

  const getAlarm = async function (alarmId) {
    await axios.get(`${REST_ALARM_API}/${alarmId}`)
      .then((res) => {
        alarmObject.value = res.data;
        // console.log(alarmObject);
      })
  }

  const modifyAlarm = function () {
    axios.put(REST_ALARM_API, alarmObject.value)
      .then((res) => {
        router.push({ name: 'alarmList' })
      })
  }

  const deleteAlarm = function (alarmId) {
    axios.delete(`${REST_ALARM_API}/${alarmId}`)
      .then((res) => {
        router.go()
      })
  }

  const savedAlarm = ref({});
  const saveAlarm = function (alarm) {
    savedAlarm.value = alarm.value;
    savedAlarm.value.userId = sessionStorage.getItem('loginUser');
  };

  const templates = ref(null);
  const getTemplates = function () {
    axios.get(REST_TEMP_API)
      .then((res) => {
        templates.value = res.data;
      })
  }

  const savedTempId = ref('');
  const selectedTemp = ref({});
  const clickTemp = function (tempId) {
    savedTempId.value = tempId;
    axios.get(`${REST_TEMP_API}/${tempId}`)
      .then((res) => {
        savedAlarm.value.exerType = res.data.exerType;
        savedAlarm.value.videoId = res.data.videoId;
        savedAlarm.value.img = res.data.img;
      })
  }

  const updateAlarmVideoId = function(newVideoId) {
    console.log(newVideoId);
    savedAlarm.value.videoId = newVideoId;
    console.log(savedAlarm.value);
  }

  const createAlarm = function () {
    axios.post(`${REST_ALARM_API}/${savedTempId.value}`, savedAlarm.value)
      .then((res) => {
        router.push({ name: 'alarmList' });
      })
      .catch((err) => {
        console.log('err')
      })
  }

  const alarmOnOff = function (alarmId) {
    axios.get(`${REST_ALARM_API}/onoff/${alarmId}`)
      .then((res) => {
        console.log("알림 온오프 완료");
      })
  }

  const callAlarm = (alarm) => {
    if (!("Notification" in window)) {
      alert("이 브라우저는 알림 기능이 지원되지 않습니다.");
    } else if (Notification.permission === "granted") {
      const notification = new Notification(
        alarm.title,
        {
          image: "/public/images/" + alarm.img, // 경로를 바꾸라는 경고문이 뜨지만 바꾸면 이미지가 안뜸..
          body: "alarm.duration" + " | 척추수술 1700만원",
          requireInteraction: false // true -> 사용자가 동작하기 전까지 꺼지지 않음
        });
      notification.onclick = (event) => {
        event.preventDefault(); // prevent the browser from focusing the Notification's tab
        window.open("http://localhost:5173/popup/"+alarm.alarmId, "_blank");
      };
      console.log(alarm.title, "알림이 전송되었습니다.", new Date())
    } else if (Notification.permission !== "denied") {
      Notification.requestPermission().then((permission) => {
        if (permission === "granted") {
          new Notification("알람이 허용되었습니다!", { body: "미리보기를 다시 눌러주세요!" });
        }
      });
    }
  }



  return { 
    alarmList,
    getAlarmList,
    alarmObject,
    getAlarm,
    modifyAlarm,
    deleteAlarm,
    savedAlarm,
    saveAlarm,
    templates,
    getTemplates,
    clickTemp,
    createAlarm,
    selectedTemp,
    savedTempId,
    callAlarm,
    updateAlarmVideoId,
    alarmOnOff,
    
  }
})
