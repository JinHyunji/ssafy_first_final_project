import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';
import router from '@/router';

const REST_ALARM_API = 'http://localhost:8080/api-alarm/alarm';
const REST_TEMP_API = 'http://localhost:8080/api-temp/temp';

export const useAlarmStore = defineStore('alarm', () => {

  const alarmList = ref([]);

  const getAlarmList = function(){
    axios.get(REST_ALARM_API)
    .then((res)=>{
      alarmList.value = res.data;
    })
  }

  const alarmObject = ref({});

  const getAlarm = function(alarmId){
    axios.get(`${REST_ALARM_API}/${alarmId}`)
    .then((res)=>{
      alarmObject.value = res.data;
    })
  }

  const modifyAlarm = function(){
    axios.put(REST_ALARM_API, alarmObject.value)
    .then((res)=>{
      router.push({ name: 'alarmList'})
    })
  }

  const deleteAlarm = function(alarmId){
    axios.delete(`${REST_ALARM_API}/${alarmId}`)
    .then((res)=>{
      router.go()
    })
  }

  const savedAlarm = ref({});
  const saveAlarm = function(alarm) {
    savedAlarm.value = alarm.value;
    savedAlarm.value.userId = sessionStorage.getItem('loginUser');
  };

  const templates = ref(null);
  const getTemplates = function() {
    axios.get(REST_TEMP_API)
    .then((res)=>{
      templates.value = res.data;
    })
  }

  const savedTempId = ref('');
  const selectedTemp = ref({});
  const clickTemp = function(tempId) {
    savedTempId.value = tempId;
    axios.get(`${REST_TEMP_API}/${tempId}`)
    .then((res) => {
      savedAlarm.value.exerType = res.data.exerType;
      savedAlarm.value.videoId = res.data.videoId;
      savedAlarm.value.img = res.data.img;
    })
  }

  const createAlarm = function() {
    axios.post(`${REST_ALARM_API}/${savedTempId.value}`, savedAlarm.value)
    .then((res) => {
      router.push({name: 'alarmList'});
    })
    .catch((err) => {
      console.log('err')
    })
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
    
    
  }
})
