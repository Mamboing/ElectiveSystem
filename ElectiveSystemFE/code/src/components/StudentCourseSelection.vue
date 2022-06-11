<template>
<h1>Course Selection
</h1>
<router-link to="/StudentMain">Main Page</router-link>|
<router-link to="/StudentCourseSelection">Course Selection</router-link>|
<router-link to="/StudentCurriculumView">Curriculum View</router-link>|
<router-link to="/StudentScoreQuery">Score Query</router-link>
<vxe-toolbar>
          <template #buttons>
            <vxe-input v-model="demo1.filterName" type="search" placeholder="搜索" @keyup="searchEvent1"></vxe-input>
          </template>
        </vxe-toolbar>

        <vxe-table
          border
          height="300"
          :data="demo1.list">
          <vxe-column type="seq" width="80"></vxe-column>
          <vxe-column type="checkbox" width="60"></vxe-column>
           <vxe-column field="id" title="课程ID" type="html"></vxe-column>
           <vxe-column field="name" title="课程名称" type="html"></vxe-column>
          <vxe-column field="day" title="课程日期" type="html"></vxe-column>
          <vxe-column field="time" title="课程时间" type="html"></vxe-column>
          <vxe-column field="teacher" title="课程老师" type="html"></vxe-column>
          <vxe-column field="location" title="课程位置" type="html"></vxe-column>
          <template #empty>
            <span style="color: red;">
              <img src="https://n.sinaimg.cn/sinacn17/w120h120/20180314/89fc-fyscsmv5911424.gif">
              <p>没有更多数据了！</p>
            </span>
          </template>
        </vxe-table>
</template>
<script lang="ts">
import { defineComponent, reactive } from 'vue'
        import XEUtils from 'xe-utils'

        export default defineComponent({
          setup () {
            const demo1 = reactive({
              filterName: '',
              list: [] as any[],
              tableData: [
                { id: 10001, name: 'Test1', day: 'Monday', time: '8:00-9:35', teacher: '老师', location: '教室101'},
                { id: 10002, name: 'Test2', day: 'Monday', time: '8:00-9:35', teacher: '老师', location: '教室101'},
              ] as any[]
            })

            const searchEvent1 = () => {
              const filterName = XEUtils.toValueString(demo1.filterName).trim().toLowerCase()
              if (filterName) {
                const filterRE = new RegExp(filterName, 'gi')
                const searchProps = ['id', 'name', 'day', 'time','teacher','location']
                const rest = demo1.tableData.filter(item => searchProps.some(key => XEUtils.toValueString(item[key]).toLowerCase().indexOf(filterName) > -1))
                demo1.list = rest.map(row => {
                  const item = Object.assign({}, row)
                  searchProps.forEach(key => {
                    item[key] = XEUtils.toValueString(item[key]).replace(filterRE, match => `<span class="keyword-lighten">${match}</span>`)
                  })
                  return item
                })
              } else {
                demo1.list = demo1.tableData
              }
            }

            searchEvent1()

            return {
              demo1,
              searchEvent1
            }
          }
        })
</script>
<style>
.keyword-lighten {
          color: #000;
          background-color: #FFFF00;
        }
        
</style>