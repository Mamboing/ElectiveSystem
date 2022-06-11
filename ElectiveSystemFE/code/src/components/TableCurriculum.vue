<template>
   <vxe-table
          border
          :column-config="{resizable: true}"
          :tree-config="{transform: true}"
          :expand-config="tableExpand"
          :data="tableData">
          <vxe-column field="semester" title="学期" tree-node width="80"></vxe-column>
          <vxe-column type="expand" >
            <template #content="{ row }">
              <div class="expand-wrapper">
                <vxe-grid :columns="row.childCols" :data="row.childData"></vxe-grid>
              </div>
            </template>
          </vxe-column>
        </vxe-table>
</template>
<script lang="ts">
 import { defineComponent, ref } from 'vue'
        import { VxeTablePropTypes } from 'vxe-table'
        import XEUtils from 'xe-utils'

        export default defineComponent({
          setup () {
            const tableData = ref([
              { id: 10000, parentId: null, semester: '1'},
             { id: 10050, parentId: null, semester: '2'},
            ])

            const tableExpand = ref({
              lazy: true,
              loadMethod ({ row }) {
                return new Promise(resolve => {
                  setTimeout(() => {
                    // 随机生成列
                    const childCols = XEUtils.sample([
                      { type: 'seq', title: 'Sequence' },
                      { field: 'id', title: '课程ID' },
                      { field: 'name', title: '课程名称' },
                      { field: 'day', title: '上课日期' },
                      { field: 'time', title: '上课时间' },
                      { field: 'teacher', title: '上课老师' },
                      { field: 'location', title: '上课教室' },
                    ], XEUtils.order)
                    // 随机生成数据
                    const childData = XEUtils.sample([
                      { id:'1',name: 'a', day:'周一',time:'08；00-09：35',teacher: '老师', location: '教室101' },
                    ], XEUtils.order)
                    row.childCols = childCols
                    row.childData = childData
                    resolve()
                  }, 500)
                })
              }
            } as VxeTablePropTypes.ExpandConfig)

            return {
              tableData,
              tableExpand
            }
          }
        })
</script>