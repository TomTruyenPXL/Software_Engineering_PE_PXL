import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate';

import API from '../api/Api';

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token: '',
  },
  plugins: [createPersistedState()],
  mutations: {
    login(state, token) {
      state.token = token;
    },
    logout(state, _) {
      state.token = "";
    }
  },
  getters: {
    isLoggedIn(state) {
      return state.token != '';
    },
    getToken(state) {
      return state.token;
    }
  },
  actions: {
    register(context, payload) {
      const email = payload.email;
      const password = payload.password;

      return API.register(email, password);
    },
    login(context, payload) {
      const email = payload.email;
      const password = payload.password;

      return API.login(email, password).then((res) => {
        context.commit('login', res.data.token);

        return res;
      });
    },
    logout(context, _) {
      context.commit('logout');
    }
  },
  modules: {
  }
})
