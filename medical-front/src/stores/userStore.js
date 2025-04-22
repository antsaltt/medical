import { defineStore } from 'pinia';

export const useUserStore = defineStore('user', {
  state: () => ({
    userInfo: {
      account: 'admin',
      avatar: 'https://img.yzcdn.cn/vant/cat.jpeg',
      collectionNumber: 10,
      likeNumber: 20,
      score: 100,
      couponNumber: 5,
      orderInfo: {
        paidNumber: 10,
        receivedNumber: 20,
        shippedNumber: 30,
        finishedNumber: 40,
      },
    },
    token: null, // 用户登录的 Token
  }),
  actions: {
    setUserInfo(user) {
      // 确保 userInfo 结构完整，合并默认值
      this.userInfo = {
        account: user.username || 'default',
        avatar: user.avatar || 'https://img.yzcdn.cn/vant/cat.jpeg',
        collectionNumber: user.collectionNumber || 0,
        likeNumber: user.likeNumber || 0,
        score: user.score || 0,
        couponNumber: user.couponNumber || 0,
        orderInfo: {
          paidNumber: user.orderInfo?.paidNumber || 0,
          receivedNumber: user.orderInfo?.receivedNumber || 0,
          shippedNumber: user.orderInfo?.shippedNumber || 0,
          finishedNumber: user.orderInfo?.finishedNumber || 0,
        },
      };
    },
    clearUserInfo() {
      // 重置为初始状态，而不是设为 null
      this.userInfo = {
        account: 'admin',
        avatar: 'https://img.yzcdn.cn/vant/cat.jpeg',
        collectionNumber: 0,
        likeNumber: 0,
        score: 0,
        couponNumber: 0,
        orderInfo: {
          paidNumber: 0,
          receivedNumber: 0,
          shippedNumber: 0,
          finishedNumber: 0,
        },
      };
    },
    setToken(token) {
      this.token = token;
    },
    clearToken() {
      this.token = null;
    },
  },
});