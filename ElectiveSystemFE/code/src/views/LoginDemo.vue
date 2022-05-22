<template>
  <div class="about">
    <h1>Welcome to Elective System</h1>
  <div id="v-model-select-dynamic" class="demo">
    <!-- 帐号类型 -->
  <select v-model="opt.selected">
    <option v-bind:key ="option in opt.options" v-for="option in opt.options" :value="option.value">
      {{ option.text }}
    </option>
  </select>
  <span>Selected: {{ opt.selected }}</span>
</div>
  <!-- 用户名&密码 -->
  <div id="v-model-basic" class="demo">
    <p>Username：
  <input v-model="user.username" placeholder="edit me" />
  </p>
  <p>Password：
  <input v-model="user.password" placeholder="edit me" />
  </p>
  <p>Username is: {{ user.username }}</p>
  <p>Password is: {{ user.password }}</p>
  <button type="submit" @click="login">登录</button>
</div>
  </div>
</template>
<script lang="ts">
import { defineComponent, reactive } from 'vue';
export default defineComponent({
  name: 'LoginVue',
  setup(){
    const obj = {
      username:'',
      password:'',
      }
      const user = reactive<any>(obj)
      console.log(user)
      const cho ={
        selected: 'E',
       options: [
        { text: '教务', value: 'E' },
        { text: '学生', value: 'S' },
        { text: '老师', value: 'T' }
        ]
      }
const opt = reactive<any>(cho)

  //方法
  const login = () =>{
  alert("登陆了！")
  }
  return{
    login,
    user,
    opt
  }
  },

})
</script>
<style>
.demo {
  font-family: sans-serif;
  border: 1px solid #eee;
  border-radius: 2px;
  padding: 20px 30px;
  margin-top: 1em;
  margin-bottom: 40px;
  user-select: none;
  overflow-x: auto;
}
</style>