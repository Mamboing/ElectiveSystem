<template>
  <h1>课程开设
  </h1>
  <router-link to="/TeacherMain">主页</router-link>|
  <router-link to="/TeacherCourseOffering">课程开设</router-link>|
  <router-link to="/TeacherScoreRegistration">成绩登记</router-link>
    <form @submit.prevent="Submit">
     <p>Course Name</p>
    <p>
        <vxe-input v-model="course.value1" placeholder="Course Name"></vxe-input>
    </p>
    <p>Course Day</p>
    <p>
        <vxe-select v-model="course.value2" placeholder="Course Day" clearable>
            <vxe-option value="1" label="周一"></vxe-option>
            <vxe-option value="2" label="周二"></vxe-option>
            <vxe-option value="3" label="周三"></vxe-option>
            <vxe-option value="4" label="周四"></vxe-option>
            <vxe-option value="5" label="周五"></vxe-option>
        </vxe-select>
    </p>
    <p>Course Time</p>
    <p>
        <vxe-select v-model="course.value3" placeholder="Course Time" clearable>
            <vxe-option value="1" label="08:00-09:35"></vxe-option>
            <vxe-option value="2" label="09:50-11:25"></vxe-option>
            <vxe-option value="3" label="13:00-14:35"></vxe-option>
            <vxe-option value="4" label="14:50-16:25"></vxe-option>
            <vxe-option value="5" label="18:00-20:35"></vxe-option>
        </vxe-select>
    </p>
    <p>Course Location</p>
    <p>
        <vxe-select v-model="course.value4" placeholder="Course Location" clearable>
            <vxe-option value="1" label="Classroom 101"></vxe-option>
            <vxe-option value="2" label="Classroom 102"></vxe-option>
            <vxe-option value="3" label="Classroom 103"></vxe-option>
            <vxe-option value="4" label="Classroom 104"></vxe-option>
            <vxe-option value="5" label="Classroom 105"></vxe-option>
            <vxe-option value="6" label="Classroom 106"></vxe-option>
            <vxe-option value="7" label="Classroom 107"></vxe-option>
            <vxe-option value="8" label="Classroom 108"></vxe-option>
            <vxe-option value="9" label="Classroom 109"></vxe-option>
            <vxe-option value="10" label="Classroom 110"></vxe-option>
            <vxe-option value="11" label="Classroom 111"></vxe-option>
            <vxe-option value="12" label="Classroom 112"></vxe-option>
            <vxe-option value="13" label="Classroom 113"></vxe-option>
            <vxe-option value="14" label="Classroom 114"></vxe-option>
            <vxe-option value="15" label="Classroom 115"></vxe-option>
            <vxe-option value="16" label="Classroom 116"></vxe-option>
            <vxe-option value="17" label="Classroom 117"></vxe-option>
            <vxe-option value="18" label="Classroom 118"></vxe-option>
            <vxe-option value="19" label="Classroom 119"></vxe-option>
            <vxe-option value="20" label="Classroom 120"></vxe-option>
        </vxe-select>
    </p>
    <vxe-button content="SUBMIT"></vxe-button>
    </form>
   
</template>
<script lang="ts">
import { defineComponent, reactive } from 'vue'

export default defineComponent({
    setup() {
        const course = reactive({
            value1: '',
            value2: null,
            value3: null,
            value4: null,
        })
        return {
            course
        }
    },
    methods: {
        Submit() {
            if (this.course.value1 === ' ') {
                    alert("没有填写课程名称")
            }
            if (this.course.value2 === null) {
                    alert("没有选择课程日期")
            }
            if (this.course.value3 === null) {
                    alert("没有选择课程时间")
            }
            if (this.course.value4 === null) {
                    alert("没有选择课程地点")
            }
            this.$router.push("/TeacherMain")
        }
    }
})
</script>