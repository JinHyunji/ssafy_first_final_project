<template>
    <div class="container">
        <h1>
            {{ store.alarmObject.title }}
        </h1>
        <div v-if="store.alarmObject.img">
            <img :src="getImgSrc(store.alarmObject.img)">
        </div>
        <div v-else-if="store.alarmObject.videoId">
            <iframe width="700px" height="393.75px" :src="getVideoSrc(store.alarmObject.videoId)"
                title="YouTube video player" frameborder="0"
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                referrerpolicy="strict-origin-when-cross-origin" allowfullscreen>
            </iframe>
        </div>
        <div v-else>

        </div>
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


<style scoped>
    .container {
        margin-top: 80px;
        display: flex;
        flex-direction: column;
        align-items: center;
    }

</style>


