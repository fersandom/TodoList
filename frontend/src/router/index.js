import { createRouter, createWebHistory } from 'vue-router'
import { useAuthStore } from "../stores/authStore"

const routes = [
   {
      path: '/',
      name: '/',
      meta: { auth: true },
      component: () =>
         import("../views/ToDoPanel.vue")
   },
   {
      path: '/auth',
      name: 'auth',
      meta: { auth: false },
      component: () =>
         import('../views/AuthPage.vue')
   }
]

const router = createRouter({
   history: createWebHistory(),
   routes
})

//Este es el "bueno"
// router.beforeEach(async (to, from) => {
//    const authStore = useAuthStore()
//    if (to.meta.auth && !authStore.isAuthenticated) {
//       return '/auth'
//    }

// })

// router.beforeEach(async (to, from) => {
//    const authStore = useAuthStore()
//    if(to.meta.auth === false &&authStore.isAuthenticated){
//       return "/"
//    }
// })







export default router