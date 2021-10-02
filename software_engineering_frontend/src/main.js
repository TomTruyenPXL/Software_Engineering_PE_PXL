import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import {BootstrapVue, IconsPlugin} from 'bootstrap-vue';

import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';

import './style/style.css';

Vue.config.productionTip = false

Vue.use(BootstrapVue);
Vue.use(IconsPlugin);

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.auth)) {
      if (!store.getters.isLoggedIn) {
          next({ name: 'Login' })
      } else {
          document.title = to.meta.title;
          next();
      }
  } else {
      document.title = to.meta.title;
      next() // does not require auth, make sure to always call next()!
  }
})
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
