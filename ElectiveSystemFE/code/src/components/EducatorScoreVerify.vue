
<template>
  <h1>成绩管理
  </h1>
  <router-link to="/EducatorMain">主页</router-link>|
  <router-link to="/EducatorTeacherRegistration">教师管理</router-link>|
  <router-link to="/EducatorStudentRegistration">学生管理</router-link>|
  <router-link to="/EducatorScoreVerify">成绩管理</router-link>|
  <router-link to="/EducatorCourseOfferingVerify">开课管理</router-link>|
  <router-link to="/EducatorCourseSelectionVerify">选课管理</router-link>
  <p>
    <vxe-table ref="xTable1" border auto-resize :data="demo1.tableData" @toggle-row-expand="toggleExpandChangeEvent">
      <vxe-column type="seq" width="60" :fixed="demo1.seqFixed"></vxe-column>
      <vxe-column field="name" title="课程名称" sortable></vxe-column>
      <vxe-column field="semester" title="授课学期" sortable></vxe-column>
      <vxe-column field="teacher" title="授课老师" sortable></vxe-column>
      <vxe-column field="id" title="课程ID" sortable></vxe-column>
      <vxe-column field="course" title="课程名称" sortable></vxe-column>
      <vxe-column field="day" title="授课日期" sortable></vxe-column>
      <vxe-column field="time" title="授课时间" sortable></vxe-column>
      <vxe-column field="location" title="授课教室" sortable></vxe-column>
      <vxe-column type="expand" :fixed="demo1.expandFixed">
        <template #content="{ rowIndex }">
          <div v-if="rowIndex === 0" class="expand-wrapper">
            <vxe-grid ref="xGrid" v-bind="gridOptions" v-on="gridEvents">
              <template #name_edit="{ row }">
                <vxe-input v-model="row.name"></vxe-input>
              </template>
              <template #id_edit="{ row }">
                <vxe-input v-model="row.id"></vxe-input>
              </template>
              <template #score1_edit="{ row }">
                <vxe-input v-model="row.score1"></vxe-input>
              </template>
              <template #score2_edit="{ row }">
                <vxe-input v-model="row.score2"></vxe-input>
              </template>
              <template #score3_edit="{ row }">
                <vxe-input v-model="row.score3"></vxe-input>
              </template>
              <template #year_edit="{ row }">
                <vxe-input v-model="row.year"></vxe-input>
              </template>
            </vxe-grid>
          </div>
        </template>
      </vxe-column>
    </vxe-table>
  </p>
  <p>

  </p>
</template>
<script lang="ts">
import { defineComponent, reactive, ref, nextTick } from 'vue'
import { VxeTableInstance, VxeTableEvents, VxeColumnPropTypes } from 'vxe-table'
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
        { field: 'id', title: '学生ID', editRender: {}, slots: { edit: 'id_edit' } },
        { field: 'name', title: '学生姓名', editRender: {}, slots: { edit: 'name_edit' } },
        {
          title: '学生成绩',
          children: [
            { field: 'score1', title: '平时成绩', editRender: { autofocus: '.vxe-input--inner' }, slots: { edit: 'score1_edit' } },
            { field: 'score2', title: '期末成绩', editRender: {}, slots: { edit: 'score2_edit' } },
            { field: 'score3', title: '总评成绩', editRender: {}, slots: { edit: 'score3_edit' } }
          ]
        },
        { field: 'year', title: '学生年份', showOverflow: true, editRender: {}, slots: { edit: 'year_edit' } }
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
        { id: 10001, name: 'Test1', score1: 100, score2: 100, score3: 100, year: 'Shenzhen' },
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
    const xTable1 = ref({} as VxeTableInstance)

    const isChronological4 = ref(false)
    const demo1 = reactive({
      seqFixed: null as VxeColumnPropTypes.Fixed,
      expandFixed: null as VxeColumnPropTypes.Fixed,
      tableData: [
        { id: '1', name: 'a', semester: 'b', teacher: 'c' },
      ]
    })

    const toggleSeqFixed = () => {
      demo1.seqFixed = demo1.seqFixed ? null : 'left'
      nextTick(() => {
        const $table = xTable1.value
        $table.refreshColumn()
      })
    }

    const toggleExpandFixed = () => {
      demo1.expandFixed = demo1.expandFixed ? null : 'left'
      nextTick(() => {
        const $table = xTable1.value
        $table.refreshColumn()
      })
    }

    const toggleExpandChangeEvent: VxeTableEvents.ToggleRowExpand = ({ expanded }) => {
      console.log('行展开事件' + expanded)
    }

    const sortChangeEvent3: VxeTableEvents.SortChange = ({ sortList }) => {
      console.info(sortList.map((item) => `${item.property},${item.order}`).join('; '))
    }
    return {
      demo1,
      toggleSeqFixed,
      toggleExpandFixed,
      toggleExpandChangeEvent,
      sortChangeEvent3,
      isChronological4,
      xGrid,
      gridOptions,
      gridEvents
    }
  }
})
</script>
<style>
.expand-wrapper {
  padding: 20px;
}
</style>