<script setup>

import {useRoute, useRouter} from 'vue-router';
import {ref, watch, watchEffect} from 'vue';
import {doctorOneGet, patientResumeList, registerSave} from '@/api/businessApi.js';
import {regTime} from '@/utils/commonDict.js';
import {showDialog, showNotify} from 'vant';
import DocItem from '@/views/tabs/doc/DocItem.vue';

const router = useRouter()
const route = useRoute()
const id = route.query.id
const panelShow = ref(false)
const height = ref(500)

const doctorInfo = ref({
  appointmentTime: [],
})
const current = ref(-1)

watchEffect(async () => {
  try {
    await loadData()
  } catch (error) {
    showNotify({type: 'danger', message: '请求错误'});
  }
})

async function loadData() {
  doctorInfo.value = await doctorOneGet(id)
}

const params = ref({
  doctorId: '',
  timeQuantum: undefined,
  resumeId:''
})

async function handleRegister() {

  await loadResume()

  if (!params.value.resumeId) {
    panelShow.value = true
    return;
  }

  if (!params.value.timeQuantum) {
    showNotify({type: 'danger', message: 'Please select a time slot'});
    return
  }


  if (!params.value.timeQuantum) {
    showNotify({type: 'danger', message: 'Please select a time slot'});
    return
  }

  params.value.doctorId = doctorInfo.value.id
  registerSave(params.value).then(() => {

    showDialog({
      title: 'Notification',
      message: 'Appointment booked successfully',
      theme: 'round-button',
    })

    loadData()
  })
}



const isItemDisabled = (item) => {
  const index = doctorInfo.value.appointmentTime.indexOf(item)
  return index !== -1
}
const resumeList = ref([])
async function loadResume() {
  resumeList.value = await patientResumeList()
}

</script>

<template>

  <van-overlay :show="panelShow" @click="panelShow = false">
    <van-floating-panel :v-model:height="height" duration="0.6"  >
      <doc-item
          @click="() => {
          panelShow = false
          params.resumeId = item.id
        showNotify({type: 'success', message: 'Successful Choice'});
        }"
          :gender="item.gender"
          :id-card-no="item.idCardNo"
          :real-name="item.realName"
          v-for="item in resumeList"></doc-item>
    </van-floating-panel>
  </van-overlay>





  <van-nav-bar
      title="Appointment Registration"
      left-text="back"
      left-arrow
      @click-left="() => router.back()"
  />
  <div class="p-4 bg-gray-100">
    <div class="p-2 rounded bg-white flex gap-4">
      <div class="w-2/5">
        <img class="w-full  rounded-full" :src="doctorInfo.avatar" alt="">
      </div>
      <div class="flex flex-col">
        <div><span class="text-lg">{{ doctorInfo.name }}</span> 
          <span class="text-gray-500 text-base">({{ doctorInfo.title }})</span>
        </div>
        <div class="text-base">
          {{ doctorInfo.hosName }}
        </div>
        <div class="line-clamp-3 text-sm text-gray-400">
          {{ doctorInfo.desc }}
        </div>
      </div>
    </div>
    <div class="p-2 rounded bg-white mt-4 flex flex-col">
      <div class="font-bold text-lg mt-4">Select Appointment Time</div>
      <div class="grid grid-cols-4 mt-2 grid-rows-4 gap-4">
        <div
            :class="[{'bg-zinc-200': current === index}, {'cursor-not-allowed': isItemDisabled(item)}]" @click="() =>{
              if (!isItemDisabled(item)) {
                current = index
              }
                params.timeQuantum  = item
        }" v-for="(item,index) in Object.keys(regTime)"
            class="rounded-xl text-center  p-1.5 bg-gray-50 text-base ">
          {{ regTime[item] }}
        </div>
      </div>
      <div class="mt-8">
        <div class="mb-2">Notice</div>
        <ol class='flex flex-col gap-3 text-gray-500'>
          <li>1. The registration platform provides appointment services for seven days starting tomorrow. Users can book appointments for most departments of the hospital from tomorrow to seven days in advance.</li>
          <li>2. Due to doctors' busy schedules, appointments may be rescheduled or canceled. You will be notified immediately via SMS if changes occur.</li>
          <li>3. If you cannot attend your appointment on the scheduled day, please cancel it in advance through the platform to avoid wasting appointment slots. We appreciate your understanding.</li>
        </ol>
      </div>
    </div>
    <div class="mt-10">
      <van-button @click="handleRegister" type="primary" block>Reserve</van-button>
    </div>

  </div>

</template>

<style lang="scss" scoped>

.cursor-not-allowed {
  cursor: not-allowed;
  opacity: 0.5;
}

.notice-list {
  margin-top: 30rpx;
  margin-bottom: 40rpx;
  margin-left: 0;
  padding-left: 40rpx;

  .notice-item {
    color: #a0a0a0;
    font-size: 26rpx;
    line-height: 1.7;
    margin-bottom: 20rpx;
  }
}
</style>
