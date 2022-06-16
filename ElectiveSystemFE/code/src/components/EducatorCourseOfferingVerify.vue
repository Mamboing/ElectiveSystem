
<template>
  <h1>开课管理
  </h1>
  <router-link to="/EducatorMain">主页</router-link>|
  <router-link to="/EducatorTeacherRegistration">教师管理</router-link>|
  <router-link to="/EducatorStudentRegistration">学生管理</router-link>|
  <router-link to="/EducatorScoreVerify">成绩管理</router-link>|
  <router-link to="/EducatorCourseOfferingVerify">开课管理</router-link>|
  <router-link to="/EducatorCourseSelectionVerify">选课管理</router-link>
  <p>
    <vxe-input v-model="CourseSearch.courseId" placeholder="【改删审】课程ID" clearable></vxe-input>
    <vxe-input v-model="CourseSearch.courseName" placeholder="【查改】课程名称" clearable></vxe-input>
     <vxe-input v-model="CourseSearch.weekday" placeholder="【查改】授课日
" clearable></vxe-input>
    <vxe-input v-model="CourseSearch.time" placeholder="【查改】授课时间
" clearable></vxe-input>
    <vxe-input v-model="CourseSearch.teacherName" placeholder="【查】授课教师
" clearable></vxe-input>
    <vxe-input v-model="CourseSearch.courseRoom" placeholder="【查改】授课教室" clearable></vxe-input>
    <vxe-input v-model="CourseSearch.offerState" placeholder="【查改】课程状态" clearable></vxe-input>


   
  </p>
  <p>
    <vxe-button status="primary" content="删除" @click="Delete"></vxe-button>
    <vxe-button status="primary" content="更改" @click="Update"></vxe-button>
    <vxe-button status="primary" content="查询" @click="ShowList"></vxe-button>
    <vxe-button status="primary" content="审核" @click="Verify"></vxe-button>
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
import XEUtils from 'xe-utils'
// import { table } from 'console';
export default defineComponent({
  setup() {
    const CourseSearch = reactive({
      courseId: '',
      courseName: '',
      courseRoom: '',
      offerState: '',
      teacherName: '',
      time: '',
      weekday: ''
    })

    const tablePage = reactive({
      total: 0,
      currentPage: 1,
      pageSize: 20
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
        { field: 'courseId', title: '课程ID', sortable: true },
        { field: 'courseName', title: '课程名称', sortable: true },
        { field: 'weekday', title: '授课日', sortable: true },
        { field: 'time', title: '授课时间', sortable: true },
        { field: 'teacherName', title: '授课老师', sortable: true },
        { field: 'courseRoom', title: '授课教室', sortable: true },
        { field: 'offerState', title: '课程状态', sortable: true }
        // ,
        // { field: 'address', title: 'Address', showOverflow: true }
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

        url: 'http://localhost:8081/admin/course/list',
        params: {
          courseName: CourseSearch.courseName,
          courseRoom: CourseSearch.courseRoom,
          offerState: CourseSearch.offerState,
          teacherName: CourseSearch.teacherName,
          time: CourseSearch.time,
          weekday: CourseSearch.weekday,
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

    const Update = () => {

      axios({
        method: 'POST',
        url: 'http://localhost:8081/admin/course/update',
        data: {
          courseId:  CourseSearch.courseId,
          courseName: CourseSearch.courseName,
          courseRoom: CourseSearch.courseRoom,
          offerState: CourseSearch.offerState,
          teacherId: 0,
          time: CourseSearch.time,
          weekday: CourseSearch.weekday
        }
      }).then(response => {
        console.log(tablePage.currentPage);
        const { list } = response.data.data;
        gridOptions.data = list;
        const { total } = response.data.data;
        tablePage.total = total;
         CourseSearch.courseId = '',
        CourseSearch.courseName = '',
          CourseSearch.courseRoom = '',
          CourseSearch.offerState = '',
          CourseSearch.teacherName = '',
          CourseSearch.time = '',
          CourseSearch.weekday = '',
          ShowList();
      }).catch(res => {
        console.log(res)
      }).finally(() => {
        console.log('完成了')
      })
    }
    const Delete = () => {

      axios({
        method: 'DELETE',
        url: 'http://localhost:8081/admin/course/delete/' + CourseSearch.courseId
      }).then(response => {
        console.log(tablePage.currentPage);
        const { list } = response.data.data;
        gridOptions.data = list;
        const { total } = response.data.data;
        tablePage.total = total;
       CourseSearch.courseName = '',
          CourseSearch.courseRoom = '',
          CourseSearch.offerState = '',
          CourseSearch.teacherName = '',
          CourseSearch.time = '',
          CourseSearch.weekday = '',
        ShowList();

      }).catch(res => {
        console.log(res)
      }).finally(() => {
        console.log('完成了')
      })
    }

    const Verify = () => {

      axios({
        method: 'PUT',
        url: 'http://localhost:8081/admin/course/verify/' + CourseSearch.courseId
      }).then(response => {
        console.log(tablePage.currentPage);
        const { list } = response.data.data;
        gridOptions.data = list;
        const { total } = response.data.data;
        tablePage.total = total;
       CourseSearch.courseName = '',
          CourseSearch.courseRoom = '',
          CourseSearch.offerState = '',
          CourseSearch.teacherName = '',
          CourseSearch.time = '',
          CourseSearch.weekday = '',
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
      // console.log(tablePage.currentPage)
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
      Update,
      Delete,
      Verify
    }
  }
})
</script>