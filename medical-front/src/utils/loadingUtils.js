import { ref  } from 'vue'


export let loading = ref(false)
let needLoadingRequestCount = 0



export function startLoading() {
    if (needLoadingRequestCount === 0) {
        loading.value = true
    }
    needLoadingRequestCount++
}


export function stopLoading() {
    if (needLoadingRequestCount <= 0) return
    needLoadingRequestCount--
    if (needLoadingRequestCount === 0) {
        setTimeout(() => {
            if (needLoadingRequestCount === 0) {
                loading.value = false
            }
        }, 300)/*300ms 间隔内的 loading 合并为一次*/
    }
}
