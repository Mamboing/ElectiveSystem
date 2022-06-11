<template>
  <h1>Score Registration
  </h1>
  <router-link to="/TeacherMain">Score Registration</router-link>|
  <router-link to="/TeacherCourseOffering">Course Offering</router-link>|
  <router-link to="/TeacherScoreRegistration">Score Registration</router-link>
  <p>
    <vxe-table ref="xTable1" border auto-resize :data="demo1.tableData" @toggle-row-expand="toggleExpandChangeEvent">
      <vxe-column type="seq" width="60" :fixed="demo1.seqFixed"></vxe-column>
       <vxe-column field="name" title="Name" width="400"></vxe-column>
      <vxe-column type="expand" :fixed="demo1.expandFixed">
        <template #content="{ rowIndex }">
          <div v-if="rowIndex === 0" class="expand-wrapper">
            <vxe-toolbar>
              <template #buttons>
                按点击先后顺序排序：<vxe-switch v-model="isChronological4"></vxe-switch>
              </template>
            </vxe-toolbar>
            <vxe-table border height="300" :row-config="{ isHover: true }"
              :sort-config="{ multiple: true, chronological: isChronological4 }" :data="demo1.tableData"
              @sort-change="sortChangeEvent3">
              <vxe-column field="id" title="学生ID" sortable></vxe-column>
              <vxe-column field="name" title="学生名称" sortable></vxe-column>
              <vxe-column field="score1" title="平时成绩" sortable></vxe-column>
              <vxe-column field="score2" title="期末成绩" sortable></vxe-column>
              <vxe-column field="score3" title="总评成绩" sortable></vxe-column>
            </vxe-table>
          </div>
        </template>
      </vxe-column>
    </vxe-table>
  </p>
</template>
<script lang="ts">
import { defineComponent, reactive, ref, nextTick } from 'vue'
import { VxeTableInstance, VxeTableEvents, VxeColumnPropTypes } from 'vxe-table'

export default defineComponent({
  setup() {
    const xTable1 = ref({} as VxeTableInstance)

    const isChronological4 = ref(false)
    const demo1 = reactive({
      seqFixed: null as VxeColumnPropTypes.Fixed,
      expandFixed: null as VxeColumnPropTypes.Fixed,
      tableData: [
        { id: '1', name: 'a', score1: 100, score2: 100, score3: 100 },
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
      isChronological4
    }
  }
})
</script>
<style>
.expand-wrapper {
  padding: 20px;
}
</style>