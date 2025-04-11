<script setup>

import DoctorPreview from '@/views/doctor/components/DoctorPreview.vue';
import {reactive, ref, watch} from 'vue';
import {departmentListGet, doctorPage, hosListGet, userRateListGet} from '@/api/businessApi.js';
import {useRouter, useRoute} from 'vue-router';

const page = {
  current: 1,
  total: 0,
  pageSize:30
}

const queryParams = reactive({
  ...page, departmentId: '', hosId: ''
})

const doctorList = ref([])
const departmentList = ref([])
const hosList = ref([])

const value1 = ref(0);
const value2 = ref(0);
const option1 = [
  {text: '全部医院', value: 0},
];
const option2 = [
  {text: '全部科室', value: 0},
];

async function loadData() {
  const page = await doctorPage(queryParams)
  doctorList.value = page.records
}


async function loadTabs() {
  departmentList.value = await departmentListGet({})
  hosList.value = await hosListGet({})

  option2.push(...departmentList.value.map(item => ({text: item.name, value: item.id})))
  option1.push(...hosList.value.map(item => ({text: item.name, value: item.id})))
}

loadTabs()

const router = useRouter()
const route = useRoute()

const userRateList = ref([])
async function loadUserRate() {
  userRateList.value = await userRateListGet()
}
loadUserRate()


watch(() => route.query.departmentId, (newVal) => {
  if (newVal) {
    queryParams.departmentId = newVal
  }
  loadData()
}, {immediate: true})


</script>

<template>

  <div>
    <van-nav-bar
        title="医生列表"
        left-text="返回"
        left-arrow
        @click-left="() => router.push('/home')"
    />

    <van-dropdown-menu>
      <van-dropdown-item @change="() => {
        queryParams.hosId = value1
        loadData()
      }" v-model="value1" :options="option1"/>
      <van-dropdown-item @change="() => {
        queryParams.departmentId = value2
        loadData()
      }" v-model="value2" :options="option2"/>
    </van-dropdown-menu>

    <div class="p-1.5">

      <doctor-preview
          v-if="doctorList.length > 0"
          :id="item.id"
          :name="item.name"
          :title="item.title"
          :rate="item.rate"
          :department="item.departmentName"
          :hos-name="item.hosName"
          :avatar="item.avatar"
          v-for="item in doctorList"></doctor-preview>
      <van-empty v-else image="error" description="暂无数据"/>
    </div>

  </div>

</template>

<style scoped>

</style>
