
<template>
  <h1>选课管理
  </h1>
  <router-link to="/EducatorMain">主页</router-link>|
  <router-link to="/EducatorTeacherRegistration">教师管理</router-link>|
  <router-link to="/EducatorStudentRegistration">学生管理</router-link>|
  <router-link to="/EducatorScoreVerify">成绩管理</router-link>|
  <router-link to="/EducatorCourseOfferingVerify">开课管理</router-link>|
  <router-link to="/EducatorCourseSelectionVerify">选课管理</router-link>
  <p>
    <vxe-input v-model="CourseSearch.courseId" placeholder="【查】课程ID" clearable></vxe-input>
    <vxe-input v-model="CourseSearch.courseName" placeholder="【查】课程名称" clearable></vxe-input>
    <vxe-input v-model="CourseSearch.studentId" placeholder="【查】学生ID
" clearable></vxe-input>
    <vxe-input v-model="CourseSearch.studentName" placeholder="【查】上课学生
" clearable></vxe-input>
    <vxe-input v-model="CourseSearch.teacherId" placeholder="【查】教师ID
" clearable></vxe-input>
    <vxe-input v-model="CourseSearch.teacherName" placeholder="【查】授课教师
" clearable></vxe-input>
    <vxe-select v-model="CourseSearch.weekday" placeholder="【查】授课日" clearable>
      <vxe-option value="周一" label="周一"></vxe-option>
      <vxe-option value="周二" label="周二"></vxe-option>
      <vxe-option value="周三" label="周三"></vxe-option>
      <vxe-option value="周四" label="周四"></vxe-option>
      <vxe-option value="周五" label="周五"></vxe-option>
    </vxe-select>
    <vxe-select v-model="CourseSearch.time" placeholder="【查】授课时间" clearable>
      <vxe-option value="8:00 - 9:35" label="8:00 - 9:35"></vxe-option>
      <vxe-option value="9:50 - 11:25" label="9:50 - 11:25"></vxe-option>
      <vxe-option value="13:00 - 14:35" label="13:00 - 14:35"></vxe-option>
      <vxe-option value="14:50 - 16:25" label="14:50 - 16:25"></vxe-option>
      <vxe-option value="18:00 - 20:35" label="18:00 - 20:35"></vxe-option>
    </vxe-select>
    <vxe-select v-model="CourseSearch.courseRoom" placeholder="【查】上课教室" clearable>
      <vxe-option value="101" label="教室 101"></vxe-option>
      <vxe-option value="102" label="教室 102"></vxe-option>
      <vxe-option value="103" label="教室 103"></vxe-option>
      <vxe-option value="104" label="教室 104"></vxe-option>
      <vxe-option value="105" label="教室 105"></vxe-option>
      <vxe-option value="106" label="教室 106"></vxe-option>
      <vxe-option value="107" label="教室 107"></vxe-option>
      <vxe-option value="108" label="教室 108"></vxe-option>
      <vxe-option value="109" label="教室 109"></vxe-option>
      <vxe-option value="110" label="教室 110"></vxe-option>
      <vxe-option value="111" label="教室 111"></vxe-option>
      <vxe-option value="112" label="教室 112"></vxe-option>
      <vxe-option value="113" label="教室 113"></vxe-option>
      <vxe-option value="114" label="教室 114"></vxe-option>
      <vxe-option value="115" label="教室 115"></vxe-option>
      <vxe-option value="116" label="教室 116"></vxe-option>
      <vxe-option value="117" label="教室 117"></vxe-option>
      <vxe-option value="118" label="教室 118"></vxe-option>
      <vxe-option value="119" label="教室 119"></vxe-option>
      <vxe-option value="120" label="教室 120"></vxe-option>
    </vxe-select>
    <vxe-select v-model="CourseSearch.offerState" placeholder="【查】课程状态" clearable>
      <vxe-option value="0" label="待审核"></vxe-option>
      <vxe-option value="1" label="已审核"></vxe-option>
    </vxe-select>

  </p>
  <p>
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

export default defineComponent({
  setup() {
    const CourseSearch = reactive({
      courseId: null,
      courseName: null,
      studentId: null,
      studentName: null,
      teacherId: null,
      teacherName: null,
      time: null,
      weekday: null,
      offerState: null,
      courseRoom: null
    })
    const clear = () => {
      CourseSearch.courseId = null,
        CourseSearch.courseName = null,
        CourseSearch.courseRoom = null,
        CourseSearch.offerState = null,
        CourseSearch.teacherName = null,
        CourseSearch.teacherId = null,
        CourseSearch.time = null,
        CourseSearch.weekday = null,
        CourseSearch.studentId = null,
        CourseSearch.studentName = null,
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
        { field: 'studentName', title: '上课学生', sortable: true },
        { field: 'teacherId', title: '教师ID', sortable: true },
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

        url: 'http://localhost:8081/admin/select/course/list',
        params: {
          pageNo: tablePage.currentPage,
          pageSize: tablePage.pageSize
        },
        data: {
          courseID: CourseSearch.courseId,
          courseName: CourseSearch.courseName,
          studentId: CourseSearch.studentId,
          studentName: CourseSearch.studentName,
          teacherId: CourseSearch.teacherId,
          teacherName: CourseSearch.teacherName,
          time: CourseSearch.time,
          weekday: CourseSearch.weekday
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



    return {
      tablePage,
      gridOptions,
      searchEvent,
      handlePageChange,
      ShowList,
      CourseSearch,
      clear
    }
  }
})
</script>