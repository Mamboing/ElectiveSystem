<template>
  <h1>主页
  </h1>
  <router-link to="/EducatorMain">主页</router-link>|
  <router-link to="/EducatorTeacherRegistration">教师管理</router-link>|
  <router-link to="/EducatorStudentRegistration">学生管理</router-link>|
  <router-link to="/EducatorScoreVerify">成绩管理</router-link>|
  <router-link to="/EducatorCourseOfferingVerify">开课管理</router-link>|
  <router-link to="/EducatorCourseSelectionVerify">选课管理</router-link>
  <p>
 <vxe-input v-model="Search.adminPass" placeholder="【改】教务密码" clearable></vxe-input>
  </p>

  <p>
    <vxe-button status="primary" content="更改" @click="Update"></vxe-button>
  </p>
  <vxe-grid v-bind="gridOptions">
    <template #pager>
      <vxe-pager :layouts="['Sizes', 'PrevJump', 'PrevPage', 'Number', 'NextPage', 'NextJump', 'FullJump', 'Total']"
        v-model:current-page="tablePage.currentPage" v-model:page-size="tablePage.pageSize" :total="tablePage.total"
        @page-change="handlePageChange">
      </vxe-pager>
    </template>
  </vxe-grid>
</template>

<script lang="ts">
import { defineComponent, reactive } from 'vue'
import { VxeGridProps, VxePagerEvents, VXETable } from 'vxe-table'
import axios from 'axios';
export default defineComponent({
  setup() {
    const tablePage = reactive({
      total: 1,
      currentPage: 1,
      pageSize: 1
    })

    const Search = reactive({
      adminId: null,
      adminName: null,
      adminPass: null
    })

    const clear = () => {
      Search.adminId = null,
        Search.adminName = null,
        Search.adminPass = null
      ShowList();
    }

    let gridOptions = reactive<VxeGridProps>({
      border: true,
      height: 530,
      loading: false,
      columnConfig: {
        resizable: true
      },
      data: [],
      columns: [
        { type: 'seq', width: 60 },
        { type: 'checkbox', width: 50 },
        { field: 'adminId', title: 'ID' },
        { field: 'adminName', title: '姓名' },
        { field: 'adminPass', title: '密码' }

      ]
    })
    const Update = () => {

      axios({
        method: 'POST',
        url: 'http://localhost:8081/admin/update',
        data: {
          adminId: sessionStorage.id,
          adminName: Search.adminName,
          adminPass: Search.adminPass
        },
        withCredentials: true
      }).then(response => {
        console.log(tablePage.currentPage);
        const { list } = response.data.data;
        gridOptions.data = list;
        const { total } = response.data.data;
        tablePage.total = total;
        clear();
      }).catch(res => {
        console.log(res)
      }).finally(() => {
        console.log('完成了')
      })
    }
    const findList = () => {
      gridOptions.loading = true
      setTimeout(() => {
        gridOptions.loading = false
      }, 300)
      ShowList();
    }

    const searchEvent = () => {
      tablePage.currentPage = 1
      findList()
    }
    const ShowList = () => {

      axios({
        method: 'GET',
        url: 'http://localhost:8081/admin/list',
      }).then(response => {
        console.log(tablePage.currentPage);
        const { data } = response.data;
        gridOptions.data = data;
      }).catch(res => {
        console.log(res)
      }).finally(() => {
        console.log('完成了')
      })
    }


    const handlePageChange: VxePagerEvents.PageChange = ({ currentPage, pageSize }) => {
      tablePage.currentPage = currentPage
      tablePage.pageSize = pageSize
      findList()
    }

    findList()

    const openAlert = (options: any) => {
      VXETable.modal.alert(options)
    }

    return {
      tablePage,
      gridOptions,
      searchEvent,
      handlePageChange,
      ShowList,
      openAlert,
      Update,
      clear,
      Search
    }
  }
})
</script>