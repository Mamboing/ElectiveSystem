
<template>
  <h1>开课管理
  </h1>
  <router-link to="/EducatorMain">主页</router-link>|
  <router-link to="/EducatorTeacherRegistration">教师管理</router-link>|
  <router-link to="/EducatorStudentRegistration">学生管理</router-link>|
  <router-link to="/EducatorScoreVerify">成绩管理</router-link>|
  <router-link to="/EducatorCourseOfferingVerify">开课管理</router-link>|
  <router-link to="/EducatorCourseSelectionVerify">选课管理</router-link>
  <p></p>
  <vxe-grid ref="xGrid" v-bind="gridOptions" v-on="gridEvents">
    <template #teacher_edit="{ row }">
      <vxe-input v-model="row.teacher"></vxe-input>
    </template>
    <template #semester_edit="{ row }">
      <vxe-input v-model="row.semester"></vxe-input>
    </template>
    <template #id_edit="{ row }">
      <vxe-input v-model="row.id"></vxe-input>
    </template>
    <template #course_edit="{ row }">
      <vxe-input v-model="row.course"></vxe-input>
    </template>
    <template #day_edit="{ row }">
      <vxe-input v-model="row.day"></vxe-input>
    </template>
    <template #time_edit="{ row }">
      <vxe-input v-model="row.time"></vxe-input>
    </template>
    <template #location_edit="{ row }">
      <vxe-input v-model="row.location"></vxe-input>
    </template>
    <template #state_edit="{ row }">
      <vxe-input v-model="row.state"></vxe-input>
    </template>
  </vxe-grid>
</template>
<script lang="ts">
import { defineComponent, reactive, ref } from 'vue'
import { VXETable, VxeGridInstance, VxeGridListeners, VxeGridProps } from 'vxe-table'

export default defineComponent({
  setup() {
    const xGrid = ref({} as VxeGridInstance)

    const gridOptions = reactive<VxeGridProps>({
      border: true,
      keepSource: true,
      id: 'toolbar_demo_1',
      height: 530,
      printConfig: {},
      importConfig: {},
      exportConfig: {},
      columnConfig: {
        resizable: true
      },
      customConfig: {
        storage: true
      },
      editConfig: {
        trigger: 'click',
        mode: 'row',
        showStatus: true
      },
      columns: [
        { type: 'checkbox', width: 50 },
        { type: 'seq', width: 60 },
        { field: 'teacher', title: '授课老师', editRender: {}, slots: { edit: 'teacher_edit' } },
        { field: 'semester', title: '授课学期', editRender: {}, slots: { edit: 'semester_edit' } },
        {
          title: '课程信息',
          children: [
            { field: 'id', title: '课程ID', editRender: { autofocus: '.vxe-input--inner' }, slots: { edit: 'id_edit' } },
            { field: 'course', title: '课程名称', editRender: {}, slots: { edit: 'course_edit' } },
            { field: 'day', title: '课程日期', editRender: {}, slots: { edit: 'day_edit' } },
            { field: 'time', title: '课程时间', editRender: {}, slots: { edit: 'time_edit' } },
            { field: 'location', title: '授课教室', editRender: {}, slots: { edit: 'location_edit' } }
          ]
        },
        { field: 'state', title: '课程状态', showOverflow: true, editRender: {}, slots: { edit: 'state_edit' } }
      ],
      toolbarConfig: {
        buttons: [
          { code: 'myInsert', name: '新增数据' },
          { code: 'mySave', name: '保存数据', status: 'success' },
          { code: 'myExport', name: '导出数据', type: 'text', status: 'warning' },
        ],
        tools: [
          { code: 'myPrint', name: '自定义打印' }
        ],
        import: true,
        export: true,
        print: true,
        zoom: true,
        custom: true
      },
      data: [
        { id: 10001, teacher: 'Teacher', day: '周一', time: '8：00-9：35', semester: '1', state: '审核通过', course: '高级编程', location: '教室101' },
      ]
    })

    const gridEvents: VxeGridListeners = {
      toolbarButtonClick({ code }) {
        const $grid = xGrid.value
        switch (code) {
          case 'myInsert': {
            $grid.insert({
              name: 'xxx'
            })
            break
          }
          case 'mySave': {
            const { insertRecords, removeRecords, updateRecords } = $grid.getRecordset()
            VXETable.modal.message({ content: `新增 ${insertRecords.length} 条，删除 ${removeRecords.length} 条，更新 ${updateRecords.length} 条`, status: 'success' })
            break
          }
          case 'myExport': {
            $grid.exportData({
              type: 'csv'
            })
            break
          }
        }
      },
      toolbarToolClick({ code }) {
        const $grid = xGrid.value
        switch (code) {
          case 'myPrint': {
            $grid.print()
            break
          }
        }
      }
    }

    return {
      xGrid,
      gridOptions,
      gridEvents
    }
  }
})
</script>