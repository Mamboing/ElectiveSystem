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
                ElMessage.success(message)
                Router.push({
                    path: '/StudentMain'
                })

            }).catch(res => {
                console.log('失败了')
                ElMessage.error(res)
            }).finally(() => {
                console.log('完成了')
            })
            // if (this.selected === "E") {
            //     if (this.username === "admin"
            //         && this.password === "123456") {
            //         this.$router.push("/EducatorMain")
            //     } else {
            //         alert("用户类型或用户名或密码错误")
            //     }
            // } else if (this.selected === "T") {
            //     if (this.username === "admin"
            //         && this.password === "123456") {
            //         this.$router.push("/TeacherMain")
            //     } else {
            //         alert("用户类型或用户名或密码错误")
            //     }
            // }else if (this.selected === "S") {
            //     if (this.username === "admin"
            //         && this.password === "123456") {
            //         this.$router.push("/StudentMain")
            //     } else {
            //         alert("用户类型或用户名或密码错误")
            //     }
            // }
        }
    }
})
</script>