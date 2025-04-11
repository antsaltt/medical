import './assets/main.css'

import {createApp} from 'vue'
import {createPinia} from 'pinia'

import App from './App.vue'
import router from './router'
import Vant from 'vant';
import 'vant/lib/index.css'

import 'virtual:svg-icons-register'


const app = createApp(App)

app.use(createPinia())
app.use(router)
app.use(Vant)


app.mount('#app')


const rootValue = 16
const rootWidth = 390
const deviceWidth = document.body.clientWidth
document.documentElement.style.fontSize = (deviceWidth * rootValue) / rootWidth + 'px'

