<template>
<h1>Teacher Registration
</h1>
<router-link to="/EducatorMain">Main Page</router-link>|
<router-link to="/EducatorTeacherRegistration">Teacher Registration</router-link>|
<router-link to="/EducatorStudentRegistration">Student Registration</router-link>|
<router-link to="/EducatorScoreVerify">Score Verify</router-link>|
<router-link to="/EducatorCourseOfferingVerify">Offering Verify</router-link>|
<router-link to="/EducatorCourseSelectionVerify">Selection Verify</router-link>
</template>
<script lang="ts">
import { defineComponent } from 'vue';
export default defineComponent({
    name: 'EducatorTeacherRegistration'
})
</script>