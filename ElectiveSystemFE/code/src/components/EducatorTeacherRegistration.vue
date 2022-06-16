<template>
  <h1>教师管理
  </h1>
  <router-link to="/EducatorMain">主页</router-link>|
  <router-link to="/EducatorTeacherRegistration">教师管理</router-link>|
  <router-link to="/EducatorStudentRegistration">学生管理</router-link>|
  <router-link to="/EducatorScoreVerify">成绩管理</router-link>|
  <router-link to="/EducatorCourseOfferingVerify">开课管理</router-link>|
  <router-link to="/EducatorCourseSelectionVerify">选课管理</router-link>
  <p>
    <vxe-input v-model="TeacherSearch.teacherId" placeholder="【删改】教师ID" clearable></vxe-input>
    <vxe-input v-model="TeacherSearch.teacherName" placeholder="【增改查】教师用户名" clearable></vxe-input>
    <vxe-input v-model="TeacherSearch.teacherPass" placeholder="【增改】教师密码" clearable></vxe-input>

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
import XEUtils from 'xe-utils'
export default defineComponent({
  setup() {
    const TeacherSearch = reactive({
      teacherId: null,
      teacherName: null,
      teacherPass: null
    })
    const clear = () => {
      TeacherSearch.teacherId = null,
        TeacherSearch.teacherName = null,
        TeacherSearch.teacherPass = null
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
      sortConfig: {
        multiple: true,
        chronological: true
      },
      columnConfig: {
        resizable: true
      },
      data: [],
      columns: [
        { type: 'seq', width: 60 },
        { field: 'teacherId', title: 'ID', sortable: true },
        { field: 'teacherName', title: '教师用户名', sortable: true },
        { field: 'teacherPass', title: '密码', sortable: true }
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
        url: 'http://localhost:8081/admin/teacher/list',
        params: {
          teacherName: TeacherSearch.teacherName,
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
        url: 'http://localhost:8081/admin/teacher/update',
        data: {
          teacherId: TeacherSearch.teacherId,
          teacherName: TeacherSearch.teacherName,
          teacherPass: TeacherSearch.teacherPass
        }
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
    const Delete = () => {

      axios({
        method: 'DELETE',
        url: 'http://localhost:8081/admin/teacher/delete/' + TeacherSearch.teacherId
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

    const Add = () => {

      axios({
        method: 'POST',
        url: 'http://localhost:8081/admin/teacher/add',
        data: {
          teacherId: 0,
          teacherName: TeacherSearch.teacherName,
          teacherPass: TeacherSearch.teacherPass
        }
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
      TeacherSearch,
      Update,
      Delete,
      Add,
      clear
    }
  }
})
</script>