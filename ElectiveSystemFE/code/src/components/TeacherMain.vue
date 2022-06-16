<template>
  <h1>主页
  </h1>
  <router-link to="/TeacherMain">主页</router-link>|
  <router-link to="/TeacherCourseOffering">课程开设</router-link>|
  <router-link to="/TeacherScoreRegistration">成绩登记</router-link>

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
      pageSize: 10
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
        url: 'http://localhost:8081/teacher/schedule/',
        params: {
          id: sessionStorage.id,
          pageNo: tablePage.currentPage,
          pageSize: tablePage.pageSize
        }
      }).then(response => {
        console.log(tablePage.currentPage);
        const { list } = response.data.data;
        gridOptions.data = list;
        const { total } = response.data.data;
        tablePage.total = total;
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
      clear
    }
  }
})
</script>