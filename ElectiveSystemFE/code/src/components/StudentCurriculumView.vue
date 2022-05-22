<template>
<h1>Curriculum View
</h1>
<router-link to="/StudentMain">Main Page</router-link>|
<router-link to="/StudentCourseSelection">Course Selection</router-link>|
<router-link to="/StudentCurriculumView">Curriculum View</router-link>|
<router-link to="/StudentScoreQuery">Score Query</router-link>
</template>
<script lang="ts">
import { defineComponent } from 'vue';
export default defineComponent({
    name: 'StudentCurriculumView'
})
</script>