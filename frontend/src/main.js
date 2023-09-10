import { createApp } from 'vue'
import App from './App.vue'
import '../node_modules/bulma/css/bulma.min.css'
import {createPinia} from 'pinia'

const pinia = createPinia();

createApp(App).use(pinia).mount('#app')
