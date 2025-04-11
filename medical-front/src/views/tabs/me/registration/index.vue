<script setup>

import RegCard from '@/views/tabs/me/registration/RegCard.vue';
import {useRouter} from 'vue-router';
import {myRegisterGet} from '@/api/businessApi.js';
import {ref} from 'vue';

const router = useRouter()

const myRegList = ref([])

const loadData = async () => {
  myRegList.value = await myRegisterGet()
}

loadData()

</script>

<template>

  <van-nav-bar
      title="我的挂号"
      left-text="返回"
      left-arrow
      @click-left="() => router.push('/home')"
  />

  <div class="flex  bg-gray-100  flex-col gap-3 px-3">
    <reg-card
        :doctor-id="item.doctorId"
        :num="item.id"
        :real-name="item.realName"
        :id-card-no="item.idCardNo"
        :hos-name="item.doctorName"
        :department-name="item.departmentName"
        :apply-date="item.applyDate"
        v-for="item in myRegList"></reg-card>
  </div>


</template>

<style scoped lang="scss">

</style>
