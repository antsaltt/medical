<script setup>

import {useRouter} from 'vue-router';
import {ref} from 'vue';

const router = useRouter()

const isChange = ref(false)
import { idCardRules } from '@/utils/rules'
import {genderType} from '@/utils/commonDict.js';
import {patientResumeAdd, patientResumeDelete, patientResumeEdit, patientResumeList} from '@/api/businessApi.js';
import {showNotify} from 'vant';

import {calculateAge} from '@/utils/commonUtils.js';

const list =  ref([])
const show = ref(false)
const isEdit = ref(false)

const form = ref({
  id:'',
  realName:'',
  idCardNo:'',
  gender:'1',
  defaultResume:'0'
})

function goEdit(item) {
  isEdit.value = true
  form.value.realName = item.realName
  form.value.id = item.id
  form.value.gender =  item.gender
  form.value.idCardNo = item.idCardNo
  show.value = true
}

function submit() {
  if (isEdit) {
    editResume()
  } else {
    addPatientResume()
  }
}

function addPatientResume() {
  patientResumeAdd(form.value).then(() => {
    showNotify({ type: 'success', message: 'Add successful' });
    show.value = false
    loadData()
  })
}

function removeResume() {
  patientResumeDelete(form.value.id).then(() => {
    showNotify({ type: 'success', message: 'Delete successful' });
    show.value = false
    loadData()
  })
}

function editResume() {
  patientResumeEdit(form.value).then(() => {
    showNotify({ type: 'success', message: 'Edit successful' });
    show.value = false
    loadData()
  })
}

async function loadData() {
  list.value = await patientResumeList()
}
loadData()

</script>

<template>

  <van-nav-bar
      :title="isChange ? 'Choose Patient' : 'Family Record'"
      left-text="back"
      left-arrow

      @click-left="() => router.push({name:'myReg'})"
  />
  <div class="patient-page">
    <div class="patient-list">
      <div
          class="patient-item"
          v-for="item in list"
          :key="item.id"
      >
        <div class="info">
          <span class="name">{{ item.realName }}</span>
          <span class="id">
<!--            {{ item.idCard.replace(/^(.{6}).+(.{4})$/, '$1********$2') }}-->
            {{item.idCardNo}}
          </span>
          <span>{{ genderType[item.gender] }}</span>
          <span>{{calculateAge(item.idCardNo)}} Age</span>
        </div>
        <div class="icon" @click.stop="goEdit(item)">
          <van-icon name="setting-o" />
        </div>
        <div class="tag" v-if="item.defaultFlag === 1">default</div>
      </div>
      <div class="patient-add" v-if="list.length < 6" @click="() => {
        show = true
        isEdit = false
        form  = {}
      }">
        <van-icon name="plus" />
        <p>Add Patient</p>
      </div>
      <div class="patient-tip">Up to 6 people can be added</div>
    </div>
    <!-- 底部按钮 -->
    <div class="patient-next" @click="next" v-if="isChange">
      <van-button type="primary" round block>next step</van-button>
    </div>
    <van-popup position="right" v-model:show="show">
      <van-nav-bar
          v-if="isEdit"
          title="edit"
          left-text="back"
          right-text="delete"
          left-arrow
          @click-left="() => {
            show = false
          }"
          @click-right="removeResume"
      />
      <van-nav-bar
          v-else
          title="add"
          left-text="back"
          left-arrow
          @click-left="() => {
            show = false
          }"
      />
      <van-form autocomplete="off" >

        <van-field
            v-model="form.realName"
            name="name"
            label="real name"
            placeholder="Please enter your real name"
            :rules="[{ required: true, message: 'Please enter your real name' }]"
        />
        <van-field
            v-model="form.idCardNo"
            label="ID number"
            placeholder="Please enter your ID number"
            :rules="idCardRules"
        />
        <van-field name="radio" label="gender">
          <template #input>
            <van-radio-group v-model="form.gender" direction="horizontal">
              <van-radio name="1">male</van-radio>
              <van-radio name="2">female</van-radio>
            </van-radio-group>
          </template>
        </van-field>
<!--        <van-field label="Default Patient">-->
<!--          <template #input>-->
<!--            <van-checkbox v-model="form.defaultResume" :icon-size="18" round />-->
<!--          </template>-->
<!--        </van-field>-->
      </van-form>
     <div class="p-3">
       <van-button v-e @click="submit" round block type="primary" native-type="submit">
         submit
       </van-button>
     </div>

    </van-popup>
  </div>

</template>

<style lang="scss" scoped>
.patient-page {
  padding: 10px 0 80px;
  :deep() {
    .van-popup {
      width: 100%;
      height: 100%;
      padding-top: 46px;
      box-sizing: border-box;
    }
  }
}
.patient-change {
  padding: 15px;
  > h3 {
    font-weight: normal;
    margin-bottom: 5px;
  }
  > p {
    color: var(--cp-text3);
  }
}
.patient-next {
  padding: 15px;
  background-color: #fff;
  position: fixed;
  left: 0;
  bottom: 0;
  width: 100%;
  height: 80px;
  box-sizing: border-box;
}

// 底部操作栏
.van-action-bar {
  padding: 0 10px;
  margin-bottom: 10px;
  .van-button {
    color: var(--cp-price);
    background-color: var(--cp-bg);
  }
}

.patient-list {
  padding: 15px;
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
