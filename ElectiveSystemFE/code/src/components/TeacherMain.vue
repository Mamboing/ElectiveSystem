<template>
<h1>Main  Page
</h1>
<router-link to="/TeacherMain">Main Page</router-link>|
<router-link to="/TeacherCourseOffering">Course Offering</router-link>|
<router-link to="/TeacherScoreRegistration">Score Registration</router-link>
</template>
<script lang="ts">
import { defineComponent } from 'vue';
export default defineComponent({
    name: 'TeacherMainPage'
})
</script>