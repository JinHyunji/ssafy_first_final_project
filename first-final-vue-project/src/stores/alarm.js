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

  return { 
    
    alarmList,
    getAlarmList,

  }
})
