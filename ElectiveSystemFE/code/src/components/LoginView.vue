<template>
    <img alt="Vue logo" src="../assets/logo.png">
    <form @submit.prevent="LoginVue">
        <h1>Welcome to Elective System</h1>
        <div>
            <label for="">Type：</label>
            <!-- 帐号类型 -->
            <select v-model="LoginSelected">
                <option v-bind:key="option in options" v-for="option in options" :value="option.value">
                    {{ option.text }}
                </option>
            </select>
        </div>
        <p></p>
        <div>
            <label for="">Username：</label>
            <input type="text" v-model="LoginUsername">
        </div>
        <p></p>
        <div>
            <label for="">Password：</label>
            <input type="password" v-model="LoginPassword">
        </div>
        <p></p>
        <div>
            <button>登录</button>
            <vxe-button status="success" content="登录"></vxe-button>
        </div>
    </form>

</template>
<script lang="ts" >
import { defineComponent } from 'vue';
import axios from 'axios';
import { ElMessage } from 'element-plus';
import { useRoute, useRouter } from 'vue-router';
const Route = useRoute();
const Router = useRouter();
console.log(Route, Router)
// 接口基础路径
export default defineComponent({
    name: 'LoginView',
    data() {
        return {
            LoginUsername: "",
            LoginPassword: "",
            LoginSelected: '1',
            options: [
                { text: '教务', value: 3 },
                { text: '学生', value: 1 },
                { text: '老师', value: 2 }
            ]
        }
    },
    methods: {
        LoginVue() {
            axios.post('http://localhost:8081/user/login', {
                password: this.LoginPassword,
                username: this.LoginUsername,
                userType: this.LoginSelected
            }
            ).then(res => {
                console.log(res)
                let { code, data, message } = res.data;
                sessionStorage.code = code;
                sessionStorage.userType = data.userType;
                sessionStorage.message = message;
                ElMessage({
    showClose: true,
    message: 'Congrats, this is a success message.',
    type: 'success',
  })
                if (sessionStorage.userType == 1 && sessionStorage.code == 0) {
                    this.$router.push("/StudentMain")
                } else if (sessionStorage.userType == 2 && sessionStorage.code == 0) {
                    this.$router.push("/TeacherMain")
                } else if (sessionStorage.userType == 3 && sessionStorage.code == 0) {
                    this.$router.push("/EducatorMain")
                } else {
                    ElMessage.error(sessionStorage.message)
                }


            }).catch(res => {
                console.log(res)
            }).finally(() => {
                console.log('完成了')
            })
        }
    }
})
</script>