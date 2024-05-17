import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import axios from 'axios';

import VueCookies from 'vue-cookies';

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.use(VueCookies);

app.mount('#app')

axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.withCredentials = true;
