<template>
<h1>课表
</h1>
<router-link to="/StudentMain">主页</router-link>|
<router-link to="/StudentCourseSelection">选课</router-link>|
<router-link to="/StudentCurriculumView">课表</router-link>|
<router-link to="/StudentScoreQuery">查分</router-link>
<p></p>
<h1>我的课表</h1>
<vxe-table
          ref="xTable1"
          border
          auto-resize
          :data="demo1.tableData"
          @toggle-row-expand="toggleExpandChangeEvent"
        >
          <vxe-column type="seq" width="60" :fixed="demo1.seqFixed"></vxe-column>
          <vxe-column type="expand" :fixed="demo1.expandFixed">
            <template #content="{  rowIndex }">
              <div v-if="rowIndex === 0" class="expand-wrapper">
              <vxe-toolbar>
          <template #buttons>
            按点击先后顺序排序：<vxe-switch v-model="isChronological4"></vxe-switch>
          </template>
        </vxe-toolbar>
                <vxe-table
                  border
                   height="300"
          :row-config="{isHover: true}"
          :sort-config="{multiple: true, chronological: isChronological4}"
                  :data="demo1.tableData"
                  @sort-change="sortChangeEvent3">
                  <vxe-column field="id" title="课程ID" sortable></vxe-column>
                  <vxe-column field="name" title="课程名称" sortable></vxe-column>
                   <vxe-column field="day" title="上课日期" sortable></vxe-column>
                    <vxe-column field="time" title="上课时间" sortable></vxe-column>
                     <vxe-column field="teacher" title="上课老师" sortable></vxe-column>
                      <vxe-column field="location" title="上课教室" sortable></vxe-column>
                </vxe-table>
              </div>
            </template>
          </vxe-column>
        </vxe-table>
</template>
<script lang="ts">
import { defineComponent, reactive, ref, nextTick } from 'vue'
        import { VxeTableInstance, VxeTableEvents, VxeColumnPropTypes } from 'vxe-table'

        export default defineComponent({
          setup () {
            const xTable1 = ref({} as VxeTableInstance)

            const isChronological4 = ref(false)
            const demo1 = reactive({
              seqFixed: null as VxeColumnPropTypes.Fixed,
              expandFixed: null as VxeColumnPropTypes.Fixed,
              tableData: [
                { id:'1',name: 'a', day:'周一',time:'08；00-09：35',teacher: '老师', location: '教室101' },
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