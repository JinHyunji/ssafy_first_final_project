<template>
    <TheHeaderNav/>
    <div class="container">
        <h3 class="shadow p-3 mb-5 w-50 text-center bg-body-tertiary rounded rounded-pill">
            {{ store.alarmObject.title }}
        </h3>  
        <div class="border border-warning-subtle border border-2" v-if="store.alarmObject.img">
            <img :src="getImgSrc(store.alarmObject.img)">
        </div>
        <div class="" v-else-if="store.alarmObject.videoId">
            <iframe class="rounded-4" width= "700px" height="393.75px" :src="getVideoSrc(store.alarmObject.videoId)"
                title="YouTube video player" frameborder="0"
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                referrerpolicy="strict-origin-when-cross-origin" allowfullscreen>
            </iframe>
        </div>
        <div class="border border-warning-subtle border border-2" v-else>

        </div>
    </div>
</template>

<script setup>
import { useAlarmStore } from '@/stores/alarm';
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import TheHeaderNav from '@/components/common/TheHeaderNav.vue';

const route = useRoute();
const store = useAlarmStore();

onMounted(() => {
    store.getAlarm(route.params.alarmId);
})

const getImgSrc = function (imgLink) {
    return '/images/' + imgLink;
}

const getVideoSrc = function (videoLink) {
    console.log('www.youtube.com?v=' + videoLink);
    return "https://www.youtube.com/embed/"+videoLink+"?autoplay=1";
}

</script>


<style scoped>
    .container {
        margin-top: 40px;
        display: flex;
        flex-direction: column;
        align-items: center;
    }
    .my-underline {
        background: linear-gradient(to top, #ffebaf 30%, transparent 30%);
    }

</style>


