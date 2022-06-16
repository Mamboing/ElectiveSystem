<template>
    <img alt="Vue logo" src="../assets/logo.png">
    <p>
        <vxe-select v-model="Login.userType" placeholder="用户类型" clearable>
            <vxe-option value="1" label="学生"></vxe-option>
            <vxe-option value="2" label="老师"></vxe-option>
            <vxe-option value="3" label="教务"></vxe-option>
        </vxe-select>
    </p>
    <p>
        <vxe-input v-model="Login.username" placeholder="用户名
" clearable></vxe-input>
    </p>
    <p>
        <vxe-input v-model="Login.password" placeholder="密码
" clearable></vxe-input>
    </p>

    <p>
        <vxe-button status="primary" content="登录" @click="LoginVue"></vxe-button>
    </p>

</template>
<script lang="ts" >
import { defineComponent, reactive } from 'vue';
import axios from 'axios';
import { ElMessage } from 'element-plus';
import { useRoute, useRouter } from 'vue-router';
const Route = useRoute();
const Router = useRouter();
console.log(Route, Router)
// 接口基础路径
export default defineComponent({
    name: 'LoginView',
    setup() {
        const Login = reactive({
            username: null,
            password: null,
            userType: null,
        })

        return {
            Login
        }
    },
    methods: {
        LoginVue() {
            axios.post('http://localhost:8081/user/login', {
                password: this.Login.password,
                username: this.Login.username,
                userType: this.Login.userType
            }
            ).then(res => {
                console.log(res)
                let { code } = res.data;
                let { data } = res.data;
                let { message } = res.data;
                if (code == 0) {
                    sessionStorage.id = data.id;
                    ElMessage({
                        showClose: true,
                        message: message,
                        type: 'success',
                    })
                    if (sessionStorage.userType == 1) {
                        this.$router.push("/StudentCourseSelection")
                    } else if (sessionStorage.userType == 2) {
                        this.$router.push("/TeacherCourseOffering")
                    } else if (sessionStorage.userType == 3) {
                        this.$router.push("/EducatorMain")
                    }
                } else {
                    ElMessage({
                        showClose: true,
                        message: message,
                        type: 'error',
                    })
                }

            }).catch(res => {
                console.log(res)
                console.log('失败了')
            }).finally(() => {
                console.log('完成了')

            })
        }
    }
})
</script>