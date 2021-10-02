<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/login" v-if="!isLoggedIn">Login</router-link>
      <router-link to="/register" v-if="!isLoggedIn">Register</router-link>
      <b-button @click="logout" v-if="isLoggedIn">Logout</b-button>
    </div>
    <router-view/>
  </div>
</template>

<script>
export default {
  computed: {
    isLoggedIn() {
      return this.$store.getters.isLoggedIn;
    }
  },
  methods: {
    logout() {
      this.$store.dispatch("logout");
      this.$router.push('/login');
    }
  }
}
</script>
