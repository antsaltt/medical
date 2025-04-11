<script setup lang="ts">
import {Tabbar, TabbarItem} from "vant";
import {ref, watch} from "vue";
import {useRoute, useRouter} from "vue-router";

const route = useRoute()
const router = useRouter()

const active = ref(route.name as string)

watch(active, (newVal) => {
  router.push( {name:newVal})
})

</script>

<template>

  <RouterView />
    <Tabbar v-model="active">
        <TabbarItem name="home" icon-="home-o">首页</TabbarItem>
        <TabbarItem name="myReg" icon-="home-o">我的挂号</TabbarItem>
        <TabbarItem name="doc" icon-="bars">家庭档案</TabbarItem>
        <TabbarItem name="me" icon-="contact">个人中心</TabbarItem>
    </Tabbar>

</template>
