<template>
  <RouterView />
  <!-- <div v-for="item in text" :key="item.t">
    <input type="checkbox" v-model="ch" :value="item.v">{{ item.t }}
  </div> -->
</template>

<script setup>
  import { ref } from 'vue';
  const text=[{t:"봄", v:"spring"}, {t:"여름", v:"summer"}]
  const ch=ref([text[0].v]);

//참고용 코드
// new Vue({
//     el: '#app',
//     // =======================
//     data: {
//         title: 'Timer',
//         timer: null,
//         totalTime: (1 * 60),
//         resetButton: false,
//     },
//     // =======================
//     methods: {
//         startTimer: function () {
//             this.timer = setInterval(() => this.countdown(), 1000);
//             this.resetButton = true;
//         },
//         stopTimer: function () {
//             clearInterval(this.timer);
//             this.timer = nullthis.resetButton = true;
//         },
//         resetTimer: function () {
//             this.totalTime = (1 * 60);
//             clearInterval(this.timer);
//             this.timer = null;
//             this.resetButton = false;
//         },
//         padTime: function (time) {
//             return (time < 10 ? '0' : '') + time;
//         },
//         countdown: function () {
//             if (this.totalTime >= 1) {
//                 this.totalTime--;
//             } else {
//                 this.totalTime = 0;
//                 this.resetTimer;
//             }
//         }
//     },
//     // =======================
//     computed: {
//         minutes: function () {
//             const minutes = Math.floor(this.totalTime / 60);
//             return this.padTime(minutes);
//         },
//         seconds: function () {
//             const seconds = this.totalTime - (this.minutes * 60);
//             return this.padTime(seconds);
//         }
//     }
// });

</script>

<style scoped>

</style>
