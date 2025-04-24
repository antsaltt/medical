<script setup>
import doc1 from "@/assets/img/doctor-1.jpg";
import { ref } from "vue";

defineProps([
  "docName",
  "previewContent",
  "hosName",
  "avatar",
  "title",
  "likeFlag",
]);
defineEmits(["goto"]);

const loading = ref(false);
function follow(item) {
  loading.value = true;
  setTimeout(() => {
    loading.value = false;
  }, 2000);
}
</script>

<template>
  <div class="border p-3">
    <div class="p-2">
      <div class="flex items-center gap-4">
        <img class="w-12 rounded-full" :src="avatar" alt="" />
        <div class="flex flex-col">
          <span class="text-base">{{ docName }}</span>
          <span class="text-gray-300 text-sm">{{ hosName }}</span>
        </div>
        <div class="ml-16">
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
          <!--          <span class="border p-1.5 cursor-pointer rounded-2xl px-2 text-emerald-400 text-sm border-emerald-400">-->
          <!--            <van-icon name="plus" />-->
          <!--            关注-->
          <!--          </span>-->
        </div>
      </div>
    </div>
    <div>
      <span @click="$emit('goto')" class="text-xl">{{ title }}</span>
      <span class="line-clamp-2 text-gray-500 mt-2">{{ previewContent }}</span>
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
