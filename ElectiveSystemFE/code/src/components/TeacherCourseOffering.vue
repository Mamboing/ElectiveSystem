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
            <vxe-select v-model="course.time" placeholder="Course Time" clearable>
                <vxe-option value="8:00 - 9:35" label="8:00 - 9:35"></vxe-option>
                <vxe-option value="9:50 - 11:25" label="9:50 - 11:25"></vxe-option>
                <vxe-option value="13:00 - 14:35" label="13:00 - 14:35"></vxe-option>
                <vxe-option value="14:50 - 16:25" label="14:50 - 16:25"></vxe-option>
                <vxe-option value="18:00 - 20:35" label="18:00 - 20:35"></vxe-option>
            </vxe-select>
      
            <vxe-select v-model="course.location" placeholder="Course Location" clearable>
                <vxe-option value="101" label="Classroom 101"></vxe-option>
                <vxe-option value="102" label="Classroom 102"></vxe-option>
                <vxe-option value="103" label="Classroom 103"></vxe-option>
                <vxe-option value="104" label="Classroom 104"></vxe-option>
                <vxe-option value="105" label="Classroom 105"></vxe-option>
                <vxe-option value="106" label="Classroom 106"></vxe-option>
                <vxe-option value="107" label="Classroom 107"></vxe-option>
                <vxe-option value="108" label="Classroom 108"></vxe-option>
                <vxe-option value="109" label="Classroom 109"></vxe-option>
                <vxe-option value="110" label="Classroom 110"></vxe-option>
                <vxe-option value="111" label="Classroom 111"></vxe-option>
                <vxe-option value="112" label="Classroom 112"></vxe-option>
                <vxe-option value="113" label="Classroom 113"></vxe-option>
                <vxe-option value="114" label="Classroom 114"></vxe-option>
                <vxe-option value="115" label="Classroom 115"></vxe-option>
                <vxe-option value="116" label="Classroom 116"></vxe-option>
                <vxe-option value="117" label="Classroom 117"></vxe-option>
                <vxe-option value="118" label="Classroom 118"></vxe-option>
                <vxe-option value="119" label="Classroom 119"></vxe-option>
                <vxe-option value="120" label="Classroom 120"></vxe-option>
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
            time: null,
            location: null,
        })
        const Add = () => {

            axios({
                method: 'PUT',
                url: 'http://localhost:8081/teacher/add/course',
                params: {
                    courseName: course.courseName,
                    weekday: course.weekday,
                    time: course.courseName,
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