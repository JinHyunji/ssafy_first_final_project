<template>
    <div>
        {{ store.alarmObject.title }}
    </div>
    <div v-if="store.alarmObject.img">
        이미지에 값이 있을 때 표시되는 항목
        <img :src="getImgSrc(store.alarmObject.img)">
    </div>
    <div v-else-if="store.alarmObject.videoId">
        비디오에 값이 있을때 표시되는 항목
        <iframe width="560" height="315" :src="getVideoSrc(store.alarmObject.videoId)"
            title="YouTube video player" frameborder="0"
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
            referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
    </div>
    <div v-else>
        아무것도 없을 때 표시되는 항목
    </div>
</template>

<script setup>
import { useAlarmStore } from '@/stores/alarm';
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const store = useAlarmStore();

onMounted(() => {
    store.getAlarm(route.params.alarmId);
})

const getImgSrc = function (imgLink) {
    return '/images/' + imgLink;
}

const getVideoSrc = function (videoLink) {
    console.log('www.youtube.com?v=' + videoLink)
    return "https://www.youtube.com/embed/"+videoLink+"?autoplay=1";
}

</script>

<style scoped></style>