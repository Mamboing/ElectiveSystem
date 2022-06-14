<template>
<h1>学生管理
  </h1>
  <router-link to="/EducatorMain">主页</router-link>|
  <router-link to="/EducatorTeacherRegistration">教师管理</router-link>|
  <router-link to="/EducatorStudentRegistration">学生管理</router-link>|
  <router-link to="/EducatorScoreVerify">成绩管理</router-link>|
  <router-link to="/EducatorCourseOfferingVerify">开课管理</router-link>|
  <router-link to="/EducatorCourseSelectionVerify">选课管理</router-link>
<p></p>
  <vxe-grid ref="xGrid" v-bind="gridOptions" v-on="gridEvents">
          <template #name_edit="{ row }">
            <vxe-input v-model="row.name"></vxe-input>
          </template>
          <template #nickname_edit="{ row }">
            <vxe-input v-model="row.nickname"></vxe-input>
          </template>
          <template #role_edit="{ row }">
            <vxe-input v-model="row.role"></vxe-input>
          </template>
          <template #address_edit="{ row }">
            <vxe-input v-model="row.address"></vxe-input>
          </template>
        </vxe-grid>
</template>
<script lang="ts">
import { defineComponent, reactive, ref } from 'vue'
        import { VXETable, VxeGridInstance, VxeGridListeners, VxeGridProps } from 'vxe-table'

        export default defineComponent({
          setup () {
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
                { field: 'name', title: '学生姓名', editRender: {}, slots: { edit: 'name_edit' } },
                {
                  title: '帐号信息',
                  children: [
                    { field: 'nickname', title: '帐号', editRender: { autofocus: '.vxe-input--inner' }, slots: { edit: 'nickname_edit' } },
                    { field: 'role', title: '密码', editRender: {}, slots: { edit: 'role_edit' } }
                  ]
                },
                { field: 'address', title: '帐号年份', showOverflow: true, editRender: {}, slots: { edit: 'address_edit' } }
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
                { id: 10001, name: 'Test1', nickname: 'T1', role: 'Develop', sex: 'Man', age: 28, address: 'Shenzhen' },
                { id: 10002, name: 'Test2', nickname: 'T2', role: 'Test', sex: 'Women', age: 22, address: 'Guangzhou' },
                { id: 10003, name: 'Test3', nickname: 'T3', role: 'PM', sex: 'Man', age: 32, address: 'Shanghai' },
                { id: 10004, name: 'Test4', nickname: 'T4', role: 'Designer', sex: 'Women', age: 23, address: 'Shenzhen' },
                { id: 10005, name: 'Test5', nickname: 'T5', role: 'Develop', sex: 'Women', age: 30, address: 'Shanghai' },
                { id: 10006, name: 'Test6', nickname: 'T6', role: 'Designer', sex: 'Women', age: 21, address: 'Shenzhen' },
                { id: 10007, name: 'Test7', nickname: 'T7', role: 'Test', sex: 'Man', age: 29, address: 'Shenzhen' },
                { id: 10008, name: 'Test8', nickname: 'T8', role: 'Develop', sex: 'Man', age: 35, address: 'Shenzhen' }
              ]
            })

            const gridEvents: VxeGridListeners = {
              toolbarButtonClick ({ code }) {
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
              toolbarToolClick ({ code }) {
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