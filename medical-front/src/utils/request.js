import axios from 'axios'
import * as loadingUtils from '@/utils/loadingUtils.js';
import {showNotify} from 'vant';

const service = axios.create({
    baseURL: import.meta.env.VITE_API_URL,
    timeout: 10000,
})

axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'




service.interceptors.request.use(config => {
    loadingUtils.startLoading()
    config.headers['token'] = localStorage.getItem('TOKEN')
    return config
})

service.interceptors.response.use(
    res => {
        loadingUtils.stopLoading()
        const data = res.data

        if (data.code !== 200) {
            showNotify({ type: 'danger', message: data.msg });
            return Promise.reject(data)
        }
        if (data.code === 401) {
            showNotify({ type: 'danger', message: '请先登录' });
            return Promise.reject(data)
        }

        return res.data.data
    },
    err => {
        return Promise.reject(err)
    }
)

export default service
