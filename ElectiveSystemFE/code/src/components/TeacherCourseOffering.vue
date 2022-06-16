<template>
    <h1>课程开设
    </h1>
    <router-link to="/TeacherMain">主页</router-link>|
    <router-link to="/TeacherCourseOffering">课程开设</router-link>|
    <router-link to="/TeacherScoreRegistration">成绩登记</router-link>
    <p>
        <vxe-input v-model="course.courseName" placeholder="课程名称"></vxe-input>
        <vxe-select v-model="course.weekday" placeholder="授课日" clearable>
            <vxe-option value="周一" label="周一"></vxe-option>
            <vxe-option value="周二" label="周二"></vxe-option>
            <vxe-option value="周三" label="周三"></vxe-option>
            <vxe-option value="周四" label="周四"></vxe-option>
            <vxe-option value="周五" label="周五"></vxe-option>
        </vxe-select>
        <vxe-select v-model="course.time" placeholder="课程时间" clearable>
            <vxe-option value="8:00 - 9:35" label="8:00 - 9:35"></vxe-option>
            <vxe-option value="9:50 - 11:25" label="9:50 - 11:25"></vxe-option>
            <vxe-option value="13:00 - 14:35" label="13:00 - 14:35"></vxe-option>
            <vxe-option value="14:50 - 16:25" label="14:50 - 16:25"></vxe-option>
            <vxe-option value="18:00 - 20:35" label="18:00 - 20:35"></vxe-option>
        </vxe-select>

    </p>
    <vxe-button content="SUBMIT" @click="Add"></vxe-button>

</template>
<script lang="ts">
import { defineComponent, reactive } from 'vue'
import axios from 'axios';
export default defineComponent({
    setup() {
        const course = reactive({
            courseName: '',
            weekday: null,
            time: null
        })
        const Add = () => {

            axios({
                method: 'PUT',
                url: 'http://localhost:8081/teacher/add/course',
                params: {
                    id: sessionStorage.id,
                    courseName: course.courseName,
                    weekday: course.weekday,
                    time: course.time,
                    //location: course.location
                }
            }).then(response => {
                console.log(response)
            }).catch(res => {
                console.log(res)
            }).finally(() => {
                console.log('完成了')
            })
        }
        return {
            course,
            Add
        }
    }
})
</script>