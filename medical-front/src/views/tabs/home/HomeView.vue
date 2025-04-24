<script setup>


import navi1 from '@/assets/img/navi/navigator-icon-1.png'
import navi2 from '@/assets/img/navi/navigator-icon-2.png'
import navi3 from '@/assets/img/navi/navigator-icon-3.png'
import {useRouter} from 'vue-router';
import banner2 from '@/assets/img/homeBanner/banner3.png'
import banner5 from '@/assets/img/homeBanner/banner1.png'
import banner6 from '@/assets/img/homeBanner/banner2.png'
import {ref} from 'vue';
import NoticeCard from '@/views/tabs/home/componens/NoticeCard.vue';
import {departmentListGet, postPageGet} from '@/api/businessApi.js';
import middleShow from '@/assets/img/banner-2.jpg'

const transformers = [
  {imgUrl: navi1, label: 'Guide', desc: 'Answer patients questions'},
  {imgUrl: navi2, label: 'Find a doctor', desc: 'Find hospital departments'},
  {imgUrl: navi3, label: 'Registration', desc: 'Online appointment registration'},
]

const router = useRouter()

const active = ref()


const departmentList = ref([])
const postList = ref([])

async function loadData() {
  departmentList.value = await departmentListGet({limit: 8})
  const postPage = await postPageGet({pageSize: 8})
  postList.value = postPage.records
}

loadData()

</script>

<template>

  <div class=" bg-gray-50">
    <van-swipe height="230" class="my-swipe" :autoplay="3000" indicator-color="white">

      <van-swipe-item>
        <img style="width: 100%;height: 100%" :src="banner2" alt="">
      </van-swipe-item>
      <van-swipe-item>
        <img style="width: 100%;height: 100%" :src="banner6" alt="">
      </van-swipe-item>
      <van-swipe-item>
        <img style="width: 100%;height: 100%" :src="banner5" alt="">
      </van-swipe-item>
    </van-swipe>


    <div class="grid grid-cols-3 gap-2 mt-2">
      <div class="flex flex-col items-center justify-center" @click="() => router.push('/doctorList')"
           v-for="v in transformers" :key="v.label">
        <img class="w-16" :src="v.imgUrl" alt="">
        <div class="flex flex-col items-center justify-center    mt-2">
          <div class="font-bold">{{ v.label }}</div>
          <div class="text-desc">{{ v.desc }}</div>
          <!-- <div class="text-sm  mt-1 text-gray-500">{{ v.desc }}</div> -->
        </div>
      </div>
    </div>

    <div class="px-4 ">
      <div class="p-2 bg-white  rounded-xl mt-8">
        <div class="grid grid-cols-4 gap-3">
          <div @click="() => router.push({path:'/doctorList',query:{departmentId:item.id}})"
               v-for="item in departmentList" class="flex flex-col items-center justify-center">
            <img class="w-12" :src="item.icon" alt="">
            <span class="depart-name">{{ item.name }}</span>
          </div>
        </div>
        <!-- <img class="mt-2" :src="middleShow" alt=""> -->
      </div>
    </div>

    <div class="bg-white mb-20">
      <van-tabs v-model:active="active">
        <van-tab title="Highlights">
          <notice-card
              @goto="() => router.push({path:'/post/detail',query:{id:item.id}})"
              :hos-name="item.hosName"
              :avatar="item.avatar"
              :title="item.title"
              :doc-name="item.docName" :preview-content="item.previewContent" v-for="item in postList"></notice-card>
        </van-tab>
        <!-- <van-tab title="Recommend">
          <notice-card
              @click="() => router.push({path:'/post/detail',query:{id:item.id}})"
              :hos-name="item.hosName"
              :avatar="item.avatar"
              :title="item.title"
              :doc-name="item.docName" :preview-content="item.previewContent" v-for="item in postList"></notice-card>
        </van-tab> -->
      </van-tabs>

    </div>
  </div>
</template>

<style lang="scss" scoped>
.home-page {
  font-family: Arial, Helvetica, sans-serif;
  background: #f7f7f7;
  padding-bottom: 10px;


  .home-transformer {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    padding: 10px;

    &__item {
      font-size: 13px;
      line-height: 15px;
      text-align: center;

      img {
        // width: 60px
        max-width: 100%; /* 确保图标不会超出容器宽度 */
        max-height: 64px; /* 限制图标的最大高度 */
        object-fit: contain; /* 保持图标比例 */
      }
    }

    &__desc {
      display: flex;
      flex-direction: column;

      .desc_text {
        font-size: 12px;
        color: #c4bdbd;
        margin-top: 5px;
      }
    }
  }

}

.my-swipe .van-swipe-item {
  color: #fff;
  font-size: 20px;
  line-height: 150px;
  text-align: center;
  background-color: #39a9ed;
}

.top-panel {
  background-color: #ffffff;
  padding: 20px;
  display: flex;
}

.text-desc {
  font-size: 14px; /* 设置字体大小 */
  text-align: center; /* 居中对齐 */
  color: #555; /* 可选：设置字体颜色 */
}

.depart-name {
  font-size: 14px; /* 设置字体大小 */
  text-align: center; /* 居中对齐 */
  color: black; /* 可选：设置字体颜色 */
  margin-top: 5px;
}

</style>



