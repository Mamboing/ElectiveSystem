<template>
  <div class="home">
    <!-- <HelloWorld msg="Welcome to Your Vue.js + TypeScript App"/> -->
    <LoginView/>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
// import HelloWorld from '@/components/HelloWorld.vue';
import LoginView from '@/components/LoginView.vue'; // @ is an alias to /src

export default defineComponent({
  name: 'HomeView',
  components: {
    // HelloWorld,
    LoginView
},
});
</script>
