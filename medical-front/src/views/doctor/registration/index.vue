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
    showNotify({type: 'danger', message: '请选择预约时间段'});
    return
  }


  if (!params.value.timeQuantum) {
    showNotify({type: 'danger', message: '请选择预约时间段'});
    return
  }

  params.value.doctorId = doctorInfo.value.id
  registerSave(params.value).then(() => {

    showDialog({
      title: '提示',
      message: '预约成功',
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
        showNotify({type: 'success', message: '选择成功'});
        }"
          :gender="item.gender"
          :id-card-no="item.idCardNo"
          :real-name="item.realName"
          v-for="item in resumeList"></doc-item>
    </van-floating-panel>
  </van-overlay>





  <van-nav-bar
      title="挂号预约"
      left-text="返回"
      left-arrow
      @click-left="() => router.back()"
  />
  <div class="p-4 bg-gray-100">
    <div class="p-2 rounded bg-white flex gap-4">
      <div class="w-2/5">
        <img class="w-full  rounded-full" :src="doctorInfo.avatar" alt="">
      </div>
      <div class="flex flex-col">
        <div><span class="text-lg">{{ doctorInfo.name }}</span> <span
            class="text-gray-500 text-base">({{ doctorInfo.title }})</span></div>
        <div class="text-base">
          {{ doctorInfo.hosName }}
        </div>
        <div class="line-clamp-3 text-sm text-gray-400">
          {{ doctorInfo.desc }}
        </div>
      </div>
    </div>
    <div class="p-2 rounded bg-white mt-4 flex flex-col">
      <div class="font-bold text-lg mt-4">选择挂号时间</div>
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
        <div class="mb-2">注意事项</div>
        <ol class='flex  flex-col gap-3 text-gray-500'>
          <li>1. 挂号平台提供次日起七天的预约服务，用户可预约中医院的大部分科室次日起至七天的就诊号源。</li>
          <li>2. 因为医生工作繁忙，可能更改或者取消预约时间，届时会第一时间通知您，请留意短信通知。</li>
          <li>3. 如果您在就诊当天不能前往医院取号就诊，请提前通过挂号平台取消预约，否则会因造成号源的浪费，请您谅解。</li>
        </ol>
      </div>
    </div>
    <div class="mt-10">
      <van-button @click="handleRegister" type="primary" block>挂号</van-button>
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
