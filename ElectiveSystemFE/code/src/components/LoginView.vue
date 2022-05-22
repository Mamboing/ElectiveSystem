<template>
<img alt="Vue logo" src="../assets/logo.png">
    <form @submit.prevent="LoginVue">
    <h1>Welcome to Elective System</h1>
        <div>
            <label for="">Type：</label>
            <!-- 帐号类型 -->
            <select v-model="selected">
                <option v-bind:key="option in options" v-for="option in options" :value="option.value">
                    {{ option.text }}
                </option>
            </select>
        </div>
        <p></p>
        <div>
            <label for="">Username：</label>
            <input type="text" v-model="username">
        </div>
        <p></p>
        <div>
            <label for="">Password：</label>
            <input type="password" v-model="password">
        </div>
        <p></p>
        <div>
            <button>Login</button>
        </div>
    </form>
</template>
<script lang="ts">
import { defineComponent } from 'vue';
export default defineComponent({
    name: 'LoginView',
    data() {
        return {
            username: "",
            password: "",
            selected: 'E',
            options: [
                { text: '教务', value: 'E' },
                { text: '学生', value: 'S' },
                { text: '老师', value: 'T' }
            ]
        }
    },
    methods: {
        LoginVue() {
            if (this.selected === "E") {
                if (this.username === "admin"
                    && this.password === "123456") {
                    this.$router.push("/EducatorMain")
                } else {
                    alert("用户类型或用户名或密码错误")
                }
            } else if (this.selected === "T") {
                if (this.username === "admin"
                    && this.password === "123456") {
                    this.$router.push("/TeacherMain")
                } else {
                    alert("用户类型或用户名或密码错误")
                }
            }else if (this.selected === "S") {
                if (this.username === "admin"
                    && this.password === "123456") {
                    this.$router.push("/StudentMain")
                } else {
                    alert("用户类型或用户名或密码错误")
                }
            }
        }
    }
})
</script>