<script setup>

import doctor1 from '@/assets/img/doctor-1.jpg';
import {ref, watchEffect} from 'vue';
import {useRoute, useRouter} from 'vue-router';
import {doctorOneGet, userRateAdd} from '@/api/businessApi.js';
import {Notify, showNotify} from 'vant';
const router = useRouter()
const route = useRoute()

const doctorOne = ref({
  id:'',
  name: '',
  title: '',
  department: '',
  hosName: '',
})

const form = ref({
  score: 0,
})

const params = ref({
  doctorId:'',
  rate:'',
})

watchEffect(async () => {
  try {
    await loadData()
  } catch (error) {
    showNotify({type: 'danger', message: '请求错误'});
  }
})

async function loadData() {
  doctorOne.value =  await doctorOneGet(route.query.id)
}

loadData()

function submitRate() {
  params.value.doctorId = doctorOne.value.id
  userRateAdd(params.value).then(() => {
    router.push({name:'myReg'})
    showNotify({type: 'danger', message: '评分成功'});
  })
}

</script>

<template>

  <van-nav-bar
      title="服务打分"
      left-text="返回"
      left-arrow
      @click-left="() => router.push({name:'myReg'})"
  />

  <van-notice-bar
      left-icon="volume-o"
      text="请为医生的服务评价，来帮我们变的更好"></van-notice-bar>

  <div class="p-3 bg-gray-100 h-screen">
    <div class="p-2 bg-white rounded-xl ">
      <div class="p-1.5 mb-2 font-bold text-lg border-b border-gray-100">
        请为医生的服务打分
      </div>
      <div class="p-2 flex flex-col gap-2">
        <div class="flex gap-4   ">
          <img style="width: 70px;object-fit: cover" class="rounded-full" :src="doctor1" alt="">
          <div class="w-2/4 flex flex-col gap-1">
            <div><span class="font-bold">{{ doctorOne.name }}</span> <span
                class="text-gray-400 text-base">{{ doctorOne.title }}</span></div>
            <div class="text-sm text-gray-400">{{ doctorOne.title }}</div>
            <div class="text-gray-400 text-base">{{ doctorOne.hosName }}</div>
          </div>
        </div>
        <div class="flex mt-2 justify-between">
          <van-rate
              v-model="params.rate"
              :size="35"
              color="#ffd21e"
              void-icon="star"
              void-color="#eee"
          />
          <van-button @click="submitRate" plain hairline  type="primary">提交</van-button>
        </div>
      </div>
    </div>

  </div>


</template>

<style scoped lang="scss">

</style>
