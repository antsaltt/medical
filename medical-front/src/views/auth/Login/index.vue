<script setup >
import { ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import {userLogin, userMe, userRegister} from '@/api/authApi.js';
import {showNotify} from 'vant';
import { useUserStore } from '@/stores/userStore.js';

const form = ref({
  username:'',
  password:''
})
const agree = ref(false)

const router = useRouter()

const onSubmit  = async () => {

  if (!agree.value) {
    showNotify({ type: 'warning', message: 'Please agree to the user agreement' });
    return
  }

  const token = await userLogin(form.value)
  await afterLogin(token)
}

async function afterLogin(token) {
  const userStore = useUserStore();

  // 保存 token 到 localStorage
  localStorage.setItem('TOKEN', token);
  console.log(token)
  // 获取用户信息
  const loginUser = await userMe();
  console.log("loginuser",loginUser)

  // 保存用户信息到 localStorage 和 Pinia
  localStorage.setItem('USER_INFO', JSON.stringify(loginUser));
  userStore.setUserInfo(loginUser);
  userStore.setToken(token);

  // 提示用户登录成功
  showNotify({ type: 'success', message: 'Login Success' });

  // 跳转到首页
  await router.replace('/home');
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
      <h3>Password Login</h3>
      <a href="javascript:">
        <span @click="() => router.push('/register')">Register</span>
        <van-icon name="arrow"></van-icon>
      </a>
    </div>
    <!-- 表单 -->
    <van-form  @submit="onSubmit" ref="formRef">
      <van-field
          v-model="form.username"
          name="username"
          label="username"
          placeholder="username"
          :rules="[{ required: true, message: 'Please enter username' }]"
      ></van-field>
      <van-field
          v-model="form.password"
          type="password"
          name="password"
          label="密码"
          placeholder="密码"
          :rules="[{ required: true, message: 'Please enter password' }]"
      >

      </van-field>
      <div class="cp-cell">
        <van-checkbox v-model="agree">
          <span>I agreed</span>
          <a href="javascript:;">User Agreement</a>
          <span>及</span>
          <a href="javascript:;">Privacy Policy</a>
        </van-checkbox>
      </div>
      <div class="cp-cell">
        <van-button native-type="submit" block round type="primary">
          Login
        </van-button>
      </div>
      <div class="cp-cell">
        <a href="javascript:;">Forgot password? </a>
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
