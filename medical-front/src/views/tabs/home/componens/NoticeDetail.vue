<script setup>


import {ref, watch} from 'vue';
import {postOneGet} from '@/api/businessApi.js';
import {useRoute, useRouter} from 'vue-router';

const route = useRoute()
const router = useRouter()

const postOne = ref({
  title:'',
  createTime:''
})
const loading = ref(false)
function follow(item){
  loading.value = true
  setTimeout(()=>{
    loading.value = false
  },2000)
}

async function loadData() {
  postOne.value = await postOneGet(route.query.id)
}

watch(() => route.query.id, () => {
  loadData()
}, {immediate: true})


</script>

<template>

    <van-nav-bar
        :title="postOne.title"
        left-text="Return"
        left-arrow
        @click-left="() => router.push('/home')"
    />


  <div class="p-3">
    <div class="flex justify-end">
      <van-button
          class="btn"
          size="small"
          round
          :loading="loading"
          @click="follow()"
      >
        <!--            {{ item.likeFlag === 1 ? '已关注' : '+ 关注' }}-->
        <van-icon name="plus" />
        follow
      </van-button>
    </div>
    <div class="py-2 font-bold text-xl">
      {{postOne.title}}
    </div>
    <div class="my-2 text-sm text-gray-500">
      Post time：{{postOne.createTime}}
    </div>

    <div class="mt-2">
      {{postOne.content}}
    </div>
  </div>

</template>

<style scoped lang="scss">
.btn {
  padding: 0 12px;
  border-color: var(--cp-primary);
  color: var(--cp-primary);
  height: 28px;
  width: 72px;
}
</style>
