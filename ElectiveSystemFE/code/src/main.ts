import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import { useTable } from './plugins/vxe-table'
// import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
createApp(App).use(store).use(router).use(useTable).mount('#app')