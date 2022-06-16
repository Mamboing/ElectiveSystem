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
                userType: this.LoginSelected,
                xhrFields: {
                    withCredentials: true
                }
            }
            ).then(res => {
                console.log(res)
                let { data, message } = res.data;
                sessionStorage.id = data.id;
                sessionStorage.loggedIn = data.loggedIn;
                sessionStorage.name = data.name;
                sessionStorage.privilege = data.privilege;
                sessionStorage.userType = data.userType;
                sessionStorage.message1 = message;
                ElMessage({
                    showClose: true,
                    message: sessionStorage.message1,
                    type: 'success',
                })
                if (sessionStorage.userType == 1) {
                    this.$router.push("/StudentMain")
                } else if (sessionStorage.userType == 2) {
                    this.$router.push("/TeacherCourseOffering")
                } else if (sessionStorage.userType == 3) {
                    this.$router.push("/EducatorMain")
                } else {
                    ElMessage.error(sessionStorage.message)
                }


            }).catch(res => {
                console.log(res)
                ElMessage.error(sessionStorage.message)
            }).finally(() => {
                console.log('完成了')

            })
        }
    }
})
</script>