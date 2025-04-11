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
      <span class=" text-gray-400 mr-3">编号</span>
      <span>{{num}}</span>
    </div>
    <div>
      <span class="text-gray-400 mr-3">手机号码</span>
      <span>{{phone}}</span>
    </div>
    <div>
      <span class="text-gray-400 mr-3">预约时间</span>
      <span>{{applyDate}}</span>
    </div>
    <div>
      <span class="text-gray-400 mr-3">科室</span>
      <span>{{departmentName}}</span>
    </div>
    <div>
      <span class="text-gray-400 mr-3">医生</span>
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
