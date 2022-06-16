<template>
<h1>查分
</h1>
<router-link to="/StudentCourseSelection">选课</router-link>|
<router-link to="/StudentCurriculumView">课表</router-link>|
<router-link to="/StudentScoreQuery">查分</router-link>

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

export default defineComponent({
  setup() {
    const Search = reactive({
      courseName: null,
      courseId: null,
      teacherName: null,
      usualGrade: null,
      finalGrade: null,
      totalGrade: null
    })
    const clear = () => {
        Search.teacherName = null,
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
        { field: 'teacherName', title: '教师', sortable: true },
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

      axios({
        method: 'GET',
        //URL
        url: 'http://localhost:8081/student/score' ,
        params: {
         id: sessionStorage.id,
        }
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



    return {
      tablePage,
      gridOptions,
      searchEvent,
      handlePageChange,
      ShowList,
      Search,
      clear
    }
  }
})
</script>