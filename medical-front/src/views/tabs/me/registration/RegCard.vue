<script setup>

import {useRouter} from 'vue-router';

const router = useRouter()

defineProps(['realName','idCardNo','num','phone','applyDate','departmentName','hosName','doctorId'])

</script>

<template>
  <div class="bg-white rounded-xl p-3 flex flex-col gap-3">
    <div>
      <span class="text-lg font-bold ">{{realName}} <span class="text-gray-500">( {{idCardNo}} )</span></span>
    </div>
    <div>
      <span class=" text-gray-400 mr-3">Number</span>
      <span>{{num}}</span>
    </div>
    <div>
      <span class="text-gray-400 mr-3">Phone number</span>
      <span>{{phone}}</span>
    </div>
    <div>
      <span class="text-gray-400 mr-3">Appointment time</span>
      <span>{{applyDate}}</span>
    </div>
    <div>
      <span class="text-gray-400 mr-3">Department</span>
      <span>{{departmentName}}</span>
    </div>
    <div>
      <span class="text-gray-400 mr-3">Doctor</span>
      <span>{{hosName}}</span>
    </div>
    <van-divider :style="{margin:'10px'}" />
    <div class="flex justify-end ">
      <van-button @click="() => router.push({path:'/doctorRate',query:{id:doctorId}})"   type="success" size="small">评价</van-button>
    </div>
  </div>
</template>

<style scoped lang="scss">

</style>
