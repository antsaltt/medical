<script setup>

import {useRouter} from 'vue-router';


const router = useRouter()

defineProps(['name','title','department','hosName','avatar','id','rate'])



</script>

<template>


  <div class=" p-1.5">
    <div @click="() => router.push({path:'/registration',query:{id:id}})" class="w-full border-b-2 pb-1.5 flex gap-2 items-center">
      <div class="w-1/4">
        <img style="width: 100%" class="rounded-full"  :src="avatar" alt="">
      </div>
      <div class="w-2/4 flex flex-col gap-1">
        <div><span class="font-bold">{{ name }}</span> <span class="text-gray-400 text-base">{{title}}</span></div>
        <div class="text-sm text-gray-400">{{department}}</div>
        <div class=" text-base">{{hosName}}</div>
        <div></div>
      </div>
      <div class="w-1/4 p-2.5 flex items-center justify-center">
        <div class="border text-gray-400 h-14 w-20 flex flex-col  items-center justify-center  border-orange-400">
          <span class="text-orange-400">{{rate === -1 ? 'No rating yet' : rate}}</span>
          <span class="text-zinc-700 mt-1">Positive Rating Index</span>
        </div>
      </div>
    </div>

  </div>

</template>

<style scoped>

</style>
