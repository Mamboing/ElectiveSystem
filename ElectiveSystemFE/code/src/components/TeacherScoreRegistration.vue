<template>
  <h1>成绩登记
  </h1>
  <router-link to="/TeacherMain">主页</router-link>|
  <router-link to="/TeacherCourseOffering">课程开设</router-link>|
  <router-link to="/TeacherScoreRegistration">成绩登记</router-link>
  <p>
    <vxe-input v-model="Search.courseId" placeholder="【改查】课程ID" clearable></vxe-input>
    <vxe-input v-model="Search.studentId" placeholder="【改】学生ID
" clearable></vxe-input>
    <vxe-input v-model="Search.usualGrade" placeholder="【改】平时成绩
" clearable></vxe-input>
    <vxe-input v-model="Search.finalGrade" placeholder="【改】期末成绩
" clearable></vxe-input>
    <vxe-input v-model="Search.totalGrade" placeholder="【改】总评成绩
" clearable></vxe-input>
  </p>
  <p>
    <vxe-button status="primary" content="修改" @click="Update"></vxe-button>
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
    const Search = reactive({
      courseName: null,
      courseId: null,
      studentId: null,
      studentName: null,
      usualGrade: null,
      finalGrade: null,
      totalGrade: null
    })
    const clear = () => {
      Search.studentId = null,
        Search.studentName = null,
        Search.courseId = null,
        Search.courseName = null,
        Search.totalGrade = null,
        Search.usualGrade = null,
        Search.finalGrade = null,
        ShowList();
    }
    const tablePage = reactive({
      total: 0,
      currentPage: 1,
      pageSize: 20
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
        { field: 'courseId', title: '课程ID', sortable: true },
        { field: 'courseName', title: '课程名称', sortable: true },
        { field: 'studentId', title: '学生ID', sortable: true },
        { field: 'studentName', title: '学生', sortable: true },
        { field: 'usualGrade', title: '平时成绩', sortable: true },
        { field: 'finalGrade', title: '期末成绩', sortable: true },
        { field: 'totalGrade', title: '总评成绩', sortable: true }

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
      sessionStorage.courseId=Search.courseId
      axios({
        method: 'GET',
        //URL
        url: 'http://localhost:8081/teacher/find/' +sessionStorage.courseId,
        params: {
          pageNo: tablePage.currentPage,
          pageSize: tablePage.pageSize,
          teacherId: sessionStorage.id
        }
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
        url: 'http://localhost:8081/admin/score/update',
        data: {
          courseId: Search.courseId,
          finalGrade: Search.finalGrade,
          studentId: Search.studentId,
          totalGrade: Search.totalGrade,
          usualGrade: Search.usualGrade
        }
      }).then(response => {
        console.log(response.config)
         console.log(response.data)
          console.log(response.status)
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
        ShowList();
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
      Search,
      Update,
      clear
    }
  }
})
</script>