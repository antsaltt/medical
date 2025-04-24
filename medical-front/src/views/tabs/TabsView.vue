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
        <TabbarItem name="home" icon-="home-o">Home</TabbarItem>
        <TabbarItem name="myReg" icon-="home-o">Registration</TabbarItem>
        <TabbarItem name="doc" icon-="bars">Family Record</TabbarItem>
        <TabbarItem name="me" icon-="contact">Personal Center</TabbarItem>
    </Tabbar>

</template>
