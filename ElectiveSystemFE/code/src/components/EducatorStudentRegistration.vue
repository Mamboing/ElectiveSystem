<template>
  <h1>学生管理
  </h1>
  <router-link to="/EducatorMain">主页</router-link>|
  <router-link to="/EducatorTeacherRegistration">教师管理</router-link>|
  <router-link to="/EducatorStudentRegistration">学生管理</router-link>|
  <router-link to="/EducatorScoreVerify">成绩管理</router-link>|
  <router-link to="/EducatorCourseOfferingVerify">开课管理</router-link>|
  <router-link to="/EducatorCourseSelectionVerify">选课管理</router-link>
  <p>
    <vxe-input v-model="StudentSearch.studentId" placeholder="【删改】学生ID" clearable></vxe-input>
    <vxe-input v-model="StudentSearch.studentName" placeholder="【增改查】学生用户名" clearable></vxe-input>
    <vxe-input v-model="StudentSearch.studentPass" placeholder="【增改】学生密码" clearable></vxe-input>
  </p>
  <p>
    <vxe-button status="primary" content="增加" @click="Add"></vxe-button>
    <vxe-button status="primary" content="删除" @click="Delete"></vxe-button>
    <vxe-button status="primary" content="更改" @click="Update"></vxe-button>
    <vxe-button status="primary" content="查询" @click="ShowList"></vxe-button>
    <vxe-button status="primary" content="清空查询" @click="clear"></vxe-button>
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
import { VxeGridProps, VxePagerEvents } from 'vxe-table'
import axios from 'axios';
import { ElMessage } from 'element-plus';

export default defineComponent({
  setup() {
    const StudentSearch = reactive({
      studentId: null,
      studentName: null,
      studentPass: null
    })
    const clear = () => {
      StudentSearch.studentId = null,
        StudentSearch.studentName = null,
        StudentSearch.studentPass = null
      ShowList();
    }
    const tablePage = reactive({
      total: 0,
      currentPage: 1,
      pageSize: 10
    })

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
        { field: 'studentId', title: 'ID', sortable: true },
        { field: 'studentName', title: '学生用户名', sortable: true },
        { field: 'studentPass', title: '密码', sortable: true }

      ]
    })

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

        url: 'http://localhost:8081/admin/student/list',
        params: {
          studentName: StudentSearch.studentName,
          pageNo: tablePage.currentPage,
          pageSize: tablePage.pageSize,

        },
      }).then(response => {
        let { code } = response.data;
        let { message } = response.data;
        if (code == 0) {
          const { list } = response.data.data;
          gridOptions.data = list;
          const { total } = response.data.data;
          tablePage.total = total;

          ElMessage({
            showClose: true,
            message: message,
            type: 'success',
          })

        } else {
          ElMessage({
            showClose: true,
            message: message,
            type: 'error',
          })
        }


      }).catch(res => {
        console.log(res)
      }).finally(() => {
        console.log('完成了')
      })
    }

    const Update = () => {

      axios({
        method: 'POST',
        url: 'http://localhost:8081/admin/student/update',
        data: {
          studentId: StudentSearch.studentId,
          studentName: StudentSearch.studentName,
          studentPass: StudentSearch.studentPass
        },
      }).then(response => {
        let { code } = response.data;
        let { message } = response.data;
        if (code == 0) {
          const { list } = response.data.data;
          gridOptions.data = list;
          const { total } = response.data.data;
          tablePage.total = total;
          ElMessage({
            showClose: true,
            message: message,
            type: 'success',
          })

        } else {
          ElMessage({
            showClose: true,
            message: message,
            type: 'error',
          })
        }
        clear();
      }).catch(res => {
        console.log(res)
      }).finally(() => {
        console.log('完成了')
      })
    }
    const Delete = () => {

      axios({
        method: 'DELETE',
        url: 'http://localhost:8081/admin/student/delete/' + StudentSearch.studentId,
        withCredentials: true
      }).then(response => {
        let { code } = response.data;
        let { message } = response.data;
        if (code == 0) {
          const { list } = response.data.data;
          gridOptions.data = list;
          const { total } = response.data.data;
          tablePage.total = total;
          ElMessage({
            showClose: true,
            message: message,
            type: 'success',
          })

        } else {
          ElMessage({
            showClose: true,
            message: message,
            type: 'error',
          })
        }
        clear();

      }).catch(res => {
        console.log(res)
      }).finally(() => {
        console.log('完成了')
      })
    }

    const Add = () => {

      axios({
        method: 'POST',
        url: 'http://localhost:8081/admin/student/add',
        data: {
          studentId: 0,
          studentName: StudentSearch.studentName,
          studentPass: StudentSearch.studentPass
        },
        withCredentials: true
      }).then(response => {
        let { code } = response.data;
        let { message } = response.data;
        if (code == 0) {
          const { list } = response.data.data;
          gridOptions.data = list;
          const { total } = response.data.data;
          tablePage.total = total;
          ElMessage({
            showClose: true,
            message: message,
            type: 'success',
          })

        } else {
          ElMessage({
            showClose: true,
            message: message,
            type: 'error',
          })
        }
        clear();

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

    return {
      tablePage,
      gridOptions,
      searchEvent,
      handlePageChange,
      ShowList,
      StudentSearch,
      Update,
      Delete,
      Add,
      clear
    }
  }
})
</script>