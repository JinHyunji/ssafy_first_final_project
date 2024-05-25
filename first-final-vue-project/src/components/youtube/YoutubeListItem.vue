<template>
    <!-- <div class="carousel-item text-center" :class="active"> -->
    <div>
      <input @click="checked" type="radio" name="videoChecked">
      <h5>{{ videoTitle }}</h5>
      <iframe
        width="450"
        height="300"
        :src="videoURL"
        title="YouTube video player"
        frameborder="0"
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
        referrerpolicy="strict-origin-when-cross-origin"
        allowfullscreen
      ></iframe>
    </div>
  </template>
  
  <script setup>
  import { useAlarmStore } from '@/stores/alarm';
import { useYoutubeStore } from '@/stores/youtube';
  import { computed } from 'vue';
//   import _ from 'lodash';
  
  const store = useYoutubeStore();
  const alarmStore = useAlarmStore();
  
  const props = defineProps({
    video: {
      type: Object,
      required: true,
    },
    index: Number,
    current: Number,
  });
  
  const videoURL = computed(() => {
    const videoId = props.video.id.videoId;
    return `https://www.youtube.com/embed/${videoId}`;
  });
  
//   const active = computed(() => {
//     if (props.current == props.index) return { active: true };
//   });
  
  const videoTitle = computed(() => {
    return props.video.snippet.title;
  });

const checked = function() {
  alarmStore.updateAlarmVideoId(props.video.id.videoId);
  // console.log(props.video)
  store.checkedVideoTitle = props.video.snippet.title;
}
</script>
  
<style scoped></style>
  