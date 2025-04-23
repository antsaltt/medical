<script setup>
import { ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import {userMe, userRegister} from '@/api/authApi.js';
import {showNotify} from 'vant';

const agree = ref(false)

const form = ref({
  username:'',
  password:'',
  repeatPassword:''
})

const router = useRouter()
// 短信登录界面切换


// 密码的可见与不可见
const isShow = ref(false)

const onSubmit  = async () => {

  if (form.value.password !== form.value.repeatPassword) {
    showNotify({ type: 'danger', message: 'Passwords do not match' });
    return
  }
  await userRegister(form.value)
  showNotify({ type: 'success', message: 'Registration successful' });
  await router.replace('/login')
}


</script>

<template>
  <div class="login-page">

    <!-- 头部 -->
    <div class="login-head">
      <h3>User registration</h3>
      <a href="javascript:;">
       <span @click="() => router.push('/login')">Login</span>
        <van-icon name="arrow"></van-icon>
      </a>
    </div>

    <van-form @submit="onSubmit">
      <van-cell-group inset>
        <van-field
            v-model="form.username"
            name="username"
            label="username"
            placeholder="username"
            :rules="[{ required: true, message: 'Please enter the username' }]"
        />
        <van-field
            v-model="form.password"
            type="password"
            name="password"
            label="password"
            placeholder="password"
            :rules="[{ required: true, message: 'Please enter the password' }]"
        />
        <van-field
            v-model="form.repeatPassword"
            type="password"
            name="repeatPassword"
            label="password"
            placeholder="password"
            :rules="[{ required: true, message: 'Please repeat the password' }]"
        />
      </van-cell-group>

      <div style="margin: 16px;">
        <van-button round block type="primary" native-type="submit">
          Submit
        </van-button>
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
