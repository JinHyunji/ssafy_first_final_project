import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';
import router from '@/router';

const REST_YOUTUBE_API = 'http://localhost:8080/api-youtube';

export const useYoutubeStore = defineStore('youtube', () => {

    const videos = ref(null)

    const youtubeSearch = function (keyword) {
        // const URL = 'https://www.googleapis.com/youtube/v3/search';
        // const API_KEY = import.meta.env.VITE_YOUTUBE_API_KEY;

        axios({
            url: `${REST_YOUTUBE_API}/search`,
            method: 'GET',
            params: {
                query: keyword
            },
            withCredentials: true
        })
        .then((res) => {
            videos.value = res.data.items
            console.log(videos.value);
        })
    }

    const checkedVideoTitle = ref('');

    return {
        youtubeSearch,
        videos,
        checkedVideoTitle,
        
    }
})
