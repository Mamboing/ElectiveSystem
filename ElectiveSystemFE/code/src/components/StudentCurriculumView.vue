<template>
<h1>课表
</h1>
<router-link to="/StudentCourseSelection">选课</router-link>|
<router-link to="/StudentCurriculumView">课表</router-link>|
<router-link to="/StudentScoreQuery">查分</router-link>
<p>
   <vxe-input v-model="CourseSearch.courseId" placeholder="【退】课程ID" clearable></vxe-input>
</p>
<p>
 <vxe-button status="primary" content="退课" @click="deSelect"></vxe-button>
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
import { ElMessage } from 'element-plus';

export default defineComponent({
  setup() {

    const CourseSearch = reactive({
      courseId: null,
    })

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
        { field: 'courseId', title: '课程ID', sortable: true },
        { field: 'courseName', title: '课程名称', sortable: true },
        { field: 'weekday', title: '授课日', sortable: true },
        { field: 'time', title: '授课时间', sortable: true },
        { field: 'teacherName', title: '授课老师', sortable: true },
        { field: 'courseRoom', title: '授课教室', sortable: true },
        { field: 'offerState', title: '课程状态', sortable: true }

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
        url: 'http://localhost:8081/student/schedule',
        params: {
          id: sessionStorage.id,
        }
      }).then(response => {
        let { code } = response.data;
        let { message } = response.data;
        if (code == 0) {
           const { data } = response.data;
        gridOptions.data = data;
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
 const deSelect = () => {


      axios({
        method: 'DELETE',

        url: 'http://localhost:8081/student/deselect/'+ CourseSearch.courseId,
        params:{
          id:sessionStorage.id,
          
        }
      }).then(response => {
        let { code } = response.data;
        let { message } = response.data;
        if (code == 0) {
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
      clear,
      deSelect,
      CourseSearch
    }
  }
})
</script>