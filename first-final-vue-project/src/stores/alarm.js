import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';
import router from '@/router';

const REST_ALARM_API = 'http://localhost:8080/api-alarm';

export const useAlarmStore = defineStore('alarm', () => {

  const alarmList = ref([]);

  const getAlarmList = function(){
    axios.get(`${REST_ALARM_API}/alarm`)
    .then((res)=>{
      console.log(res)
      alarmList.value = res.data;
    })
  }

  const alarmObject = ref({});

  const getAlarm = function(alarmId){
    axios.get(`${REST_ALARM_API}/alarm/${alarmId}`)
    .then((res)=>{
      alarmObject.value = res.data;
    })
  }

  const modifyAlarm = function(){
    axios.put(`${REST_ALARM_API}/alarm`, alarmObject.value)
    .then((res)=>{
      router.push({ name: 'alarmList'})
    })
  }

  const deleteAlarm = function(alarmId){
    console.log(alarmId)
    axios.delete(`${REST_ALARM_API}/alarm/${alarmId}`)
    .then((res)=>{
      router.go()
    })
  }

  const savedAlarm = ref({});
  const alarm = ref({
    title: "",
    startTime: "",
    endTime: "",
    term: "",
    cycle: "",
    exerType: "",
    videoId: "",
    img: "",
    userId: ""
  });

  return { 
    
    alarmList,
    getAlarmList,
    alarmObject,
    getAlarm,
    modifyAlarm,
    deleteAlarm,
    savedAlarm,
    alarm,
  }
})
