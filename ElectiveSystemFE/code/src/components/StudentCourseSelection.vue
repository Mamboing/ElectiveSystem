<template>
  <h1>选课
  </h1>
  <router-link to="/StudentCourseSelection">选课</router-link>|
  <router-link to="/StudentCurriculumView">课表</router-link>|
  <router-link to="/StudentScoreQuery">查分</router-link>
  <p>
    <vxe-input v-model="CourseSearch.courseId" placeholder="【选】课程ID" clearable></vxe-input>
    <vxe-input v-model="CourseSearch.courseName" placeholder="【查】课程名称" clearable></vxe-input>
    <vxe-input v-model="CourseSearch.teacherName" placeholder="【查】授课教师
" clearable></vxe-input>
    <vxe-select v-model="CourseSearch.time" placeholder="【查】授课时间" clearable>
      <vxe-option value="8:00 - 9:35" label="8:00 - 9:35"></vxe-option>
      <vxe-option value="9:50 - 11:25" label="9:50 - 11:25"></vxe-option>
      <vxe-option value="13:00 - 14:35" label="13:00 - 14:35"></vxe-option>
      <vxe-option value="14:50 - 16:25" label="14:50 - 16:25"></vxe-option>
      <vxe-option value="18:00 - 20:35" label="18:00 - 20:35"></vxe-option>
    </vxe-select>
  </p>
  <p>
    <vxe-button status="primary" content="选课" @click="Select"></vxe-button>
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
    const CourseSearch = reactive({
      courseId: null,
      courseName: null,
      teacherName: null,
      time: null
    })
    const clear = () => {
      CourseSearch.courseId = null,
        CourseSearch.courseName = null,
        CourseSearch.teacherName = null,
        CourseSearch.time = null,
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
        { field: 'teacherName', title: '授课老师', sortable: true },
        { field: 'time', title: '授课时间', sortable: true },
        { field: 'weekday', title: '授课日', sortable: true },
        { field: 'courseRoom', title: '授课教室', sortable: true },
        { field: 'offerState', title: '课程状态', sortable: true },

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
        method: 'POST',

        url: 'http://localhost:8081/student/course/list',
        params: {
          pageNo: tablePage.currentPage,
          pageSize: tablePage.pageSize,
          studentId: sessionStorage.id
        },
        data: {
          courseName: CourseSearch.courseName,
          teacherName: CourseSearch.teacherName,
          courseTime: CourseSearch.time,
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
    const Select = () => {


      axios({
        method: 'PUT',

        url: 'http://localhost:8081/student/select/' + CourseSearch.courseId,
        params: {
          id: sessionStorage.id,
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



    return {
      tablePage,
      gridOptions,
      searchEvent,
      handlePageChange,
      ShowList,
      CourseSearch,
      clear,
      Select
    }
  }
})
</script>