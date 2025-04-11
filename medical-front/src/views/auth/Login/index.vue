<script setup >
import { ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import {userLogin, userMe, userRegister} from '@/api/authApi.js';
import {showNotify} from 'vant';

const form = ref({
  username:'',
  password:''
})
const agree = ref(false)

const router = useRouter()

const onSubmit  = async () => {

  if (!agree.value) {
    showNotify({ type: 'warning', message: '请勾选用户协议' });
    return
  }

  const token = await userLogin(form.value)
  await afterLogin(token)
}

async function afterLogin(token) {
  // 保存token
  localStorage.setItem('TOKEN', token)
  // 获取登录人信息
  const loginUser = await userMe()
  // 保存登录人信息到 localstorage 和 pinia
  localStorage.setItem('USER_INFO', JSON.stringify(loginUser))
  // 获取用户菜单并保存到localstorage
  // 将用户推到首页
  await router.replace('/home')

}


// 密码的可见与不可见
const isShow = ref(false)

</script>

<template>
  <div class="login-page">

    <div class="flex justify-end mr-8">


    </div>
    <!-- 头部 -->
    <div class="login-head">
      <h3>密码登录</h3>
      <a href="javascript:">
        <span @click="() => router.push('/register')">注册</span>
        <van-icon name="arrow"></van-icon>
      </a>
    </div>
    <!-- 表单 -->
    <van-form  @submit="onSubmit" ref="formRef">
      <van-field
          v-model="form.username"
          name="username"
          label="用户名"
          placeholder="用户名"
          :rules="[{ required: true, message: '请填写用户名' }]"
      ></van-field>
      <van-field
          v-model="form.password"
          type="password"
          name="password"
          label="密码"
          placeholder="密码"
          :rules="[{ required: true, message: '请填写密码' }]"
      >

      </van-field>
      <div class="cp-cell">
        <van-checkbox v-model="agree">
          <span>我已同意</span>
          <a href="javascript:;">用户协议</a>
          <span>及</span>
          <a href="javascript:;">隐私条款</a>
        </van-checkbox>
      </div>
      <div class="cp-cell">
        <van-button native-type="submit" block round type="primary">
          登 录
        </van-button>
      </div>
      <div class="cp-cell">
        <a href="javascript:;">忘记密码？</a>
      </div>
    </van-form>
    <!-- 底部 -->
  </div>
</template>

<style lang="scss" scoped>
.login {
  &-page {
    padding-top: 46px;
  }
  &-head {
    display: flex;
    padding: 30px 30px 50px;
    justify-content: space-between;
    align-items: flex-end;
    line-height: 1;
    h3 {
      font-weight: normal;
      font-size: 24px;
    }
    a {
      font-size: 15px;
    }
  }
  &-other {
    margin-top: 60px;
    padding: 0 30px;
    .icon {
      display: flex;
      justify-content: center;
      img {
        width: 36px;
        height: 36px;
        padding: 4px;
      }
    }
  }
}
.van-form {
  padding: 0 14px;
  .cp-cell {
    height: 52px;
    line-height: 24px;
    padding: 14px 16px;
    box-sizing: border-box;
    display: flex;
    align-items: center;
    .van-checkbox {
      a {
        color: var(--cp-primary);
        padding: 0 5px;
      }
    }
  }
  .btn-send {
    color: var(--cp-primary);
    &.active {
      color: rgba(22, 194, 163, 0.5);
    }
  }
}
</style>
