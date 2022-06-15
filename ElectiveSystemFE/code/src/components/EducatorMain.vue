<template>
  <h1>主页
  </h1>
  <router-link to="/EducatorMain">主页</router-link>|
  <router-link to="/EducatorTeacherRegistration">教师管理</router-link>|
  <router-link to="/EducatorStudentRegistration">学生管理</router-link>|
  <router-link to="/EducatorScoreVerify">成绩管理</router-link>|
  <router-link to="/EducatorCourseOfferingVerify">开课管理</router-link>|
  <router-link to="/EducatorCourseSelectionVerify">选课管理</router-link>
  <!-- <vxe-button status="primary" content="刷新" @click="ShowList"></vxe-button> -->
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
// import { table } from 'console';
export default defineComponent({
  setup() {
    const tablePage = reactive({
      total: 0,
      currentPage: 1,
      pageSize: 10
    })
    // interface StudentList {
    //   studentId: string
    //   studentName: string
    //   studentPass: string
    //   children: object
    // }
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
        { field: 'studentId', title: 'ID' },
        { field: 'studentName', title: '姓名' },
        { field: 'studentPass', title: '密码' }
        // ,
        // { field: 'address', title: 'Address', showOverflow: true }
      ]
    })

    const findList = () => {
      gridOptions.loading = true
      setTimeout(() => {
        gridOptions.loading = false
        tablePage.total = 10
        ShowList();
        gridOptions.data = JSON.parse(sessionStorage.Studentlist)
      }, 300)
    }

    const searchEvent = () => {
      tablePage.currentPage = 1
      findList()
    }
    // let tableData: StudentList[] = ref<any>({})
    const ShowList = () => {
      // axios.get('http://localhost:8081/admin/student/list', {
      //           pageNo: tablePage.currentPage,
      //     pageSize: tablePage.pageSize
      axios({//返回promise对象
        // 请求类型
        // headers: {
        //   "Authorization": sessionStorage.name
        // },
        method: 'GET',
        //URL
        url: 'http://localhost:8081/admin/student/list',
        data: {
          pageNo: tablePage.currentPage,
          pageSize: tablePage.pageSize
        }
      }).then(response => {
        console.log(response.data.list);
        let { data } = response.data;
        sessionStorage.Studentlist = JSON.stringify(data.list);
        console.log(sessionStorage.StudentList);
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
      ShowList
    }
  }
})
</script>