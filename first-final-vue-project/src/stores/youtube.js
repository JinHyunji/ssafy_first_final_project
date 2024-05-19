import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';
import router from '@/router';

export const useYoutubeStore = defineStore('youtube', () => {

    const videos = ref(null)

    const youtubeSearch = function (keyword) {
        const URL = 'https://www.googleapis.com/youtube/v3/search';
        const API_KEY = import.meta.env.VITE_YOUTUBE_API_KEY;

        axios({
            url: URL,
            method: 'GET',
            params: {
                key: API_KEY,
                part: 'snippet',
                maxResults: 10,
                q: keyword,
                type: 'video'
            }
        })
        .then((res) => {
            videos.value = res.data.items
        })
    }

    return {
        youtubeSearch,
        videos,

    }
})
