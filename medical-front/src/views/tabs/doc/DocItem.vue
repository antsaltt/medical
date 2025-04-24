<script setup>

import {genderType} from '@/utils/commonDict.js';
import {calculateAge} from '@/utils/commonUtils.js';

defineProps(['realName','idCardNo','gender'])
</script>

<template>
  <div class="patient-list ">
    <div
        class="patient-item  "
    >
      <div class="info ">
        <span class="name">{{realName }}</span>
        <span class="id">
            {{idCardNo}}
          </span>
        <span>{{ genderType[gender] }}</span>
        <!-- <span>{{calculateAge(idCardNo)}} Age</span> -->
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>

.patient-list {
  padding: 15px;
}
.patient-item:active {
  background-color: var(--cp-disable);
}
.patient-item {
  background-color: var(--cp-disable);
}
.patient-item {
  display: flex;
  align-items: center;
  padding: 15px;
  background-color: var(--cp-bg);
  border-radius: 8px;
  margin-bottom: 15px;
  position: relative;
  border: 1px solid var(--cp-bg);
  transition: all 0.3s;
  overflow: hidden;
  .info {
    display: flex;
    flex-wrap: wrap;
    flex: 1;
    span {
      color: var(--cp-tip);
      margin-right: 20px;
      line-height: 30px;
      &.name {
        font-size: 16px;
        color: var(--cp-text1);
        width: 80px;
        margin-right: 0;
      }
      &.id {
        color: var(--cp-text2);
        width: 180px;
      }
    }
  }
  .icon {
    color: var(--cp-tag);
    width: 20px;
    text-align: center;
  }
  .tag {
    position: absolute;
    right: 60px;
    top: 21px;
    width: 30px;
    height: 16px;
    font-size: 10px;
    color: #fff;
    background-color: var(--cp-primary);
    border-radius: 2px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  &.selected {
    border-color: var(--cp-primary);
    background-color: var(--cp-plain);
    .icon {
      color: var(--cp-primary);
    }
  }
}
.patient-add {
  background-color: var(--cp-bg);
  color: var(--cp-primary);
  text-align: center;
  padding: 15px 0;
  border-radius: 8px;
  .cp-icon {
    font-size: 24px;
  }
}
.patient-tip {
  color: var(--cp-tag);
  padding: 12px 0;
}
.pb4 {
  padding-bottom: 4px;
}
</style>
