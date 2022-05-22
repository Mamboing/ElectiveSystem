import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from "../components/LoginView.vue"
const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/logindemo',
    name: 'logindemo',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/LoginDemo.vue')
  },{
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/StudentMain',
    name: 'StudentMain',
    component: () => import( '../components/StudentMain.vue')
  },
  {
    path: '/TeacherMain',
    name: 'TeacherMain',
    component: () => import( '../components/TeacherMain.vue')
  },
  {
    path: '/EducatorMain',
    name: 'EducatorMain',
    component: () => import( '../components/EducatorMain.vue')
  },
  {
    path: '/StudentCourseSelection',
    name: 'StudentCourseSelection',
    component: () => import( '../components/StudentCourseSelection.vue')
  },
  {
    path: '/StudentCurriculumView',
    name: 'StudentCurriculumView',
    component: () => import( '../components/StudentCurriculumView.vue')
  },
  {
    path: '/StudentScoreQuery',
    name: 'StudentScoreQuery',
    component: () => import( '../components/StudentScoreQuery.vue')
  },
  {
    path: '/TeacherCourseOffering',
    name: 'TeacherCourseOffering',
    component: () => import( '../components/TeacherCourseOffering.vue')
  },
  {
    path: '/TeacherScoreRegistration',
    name: 'TeacherScoreRegistration',
    component: () => import( '../components/TeacherScoreRegistration.vue')
  },{
    path: '/EducatorTeacherRegistration',
    name: 'EducatorTeacherRegistration',
    component: () => import( '../components/EducatorTeacherRegistration.vue')
  },{
    path: '/EducatorStudentRegistration',
    name: 'EducatorStudentRegistration',
    component: () => import( '../components/EducatorStudentRegistration.vue')
  },{
    path: '/EducatorScoreVerify',
    name: 'EducatorScoreVerify',
    component: () => import( '../components/EducatorScoreVerify.vue')
  },{
    path: '/EducatorCourseOfferingVerify',
    name: 'EducatorCourseOfferingVerify',
    component: () => import( '../components/EducatorCourseOfferingVerify.vue')
  },{
    path: '/EducatorCourseSelectionVerify',
    name: 'EducatorCourseSelectionVerify',
    component: () => import( '../components/EducatorCourseSelectionVerify.vue')
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
