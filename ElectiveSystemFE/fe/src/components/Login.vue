<template>
<h3></h3>
<el-input v-model="input" placeholder="请输入内容"></el-input>
</template>
<script lang="ts">
import { defineComponent,ref } from 'vue';
export default defineComponent({
    name: 'LoginVue',
    setup() {
      return {
        input: ref(''),
      }
    },
})
</script>