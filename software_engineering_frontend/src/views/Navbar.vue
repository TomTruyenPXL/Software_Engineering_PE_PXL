<template>
  <b-navbar toggleable="lg">
    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
    <b-collapse id="nav-collapse" is-nav>
      <b-navbar-nav>
        <b-nav-item v-for="link in links" :key="link.title">
          {{ link.title }}
        </b-nav-item>
      </b-navbar-nav>
      <b-navbar-nav class="ml-auto" v-if="isLoggedIn">
        <b-button @click="logout">Logout</b-button>
      </b-navbar-nav>
      <b-navbar-nav class="ml-auto" v-else>
        <b-nav-item :to="{ name: 'Login' }">Sign in</b-nav-item>
        <b-nav-item :to="{ name: 'Register' }">Sign up</b-nav-item>
      </b-navbar-nav>
    </b-collapse>
  </b-navbar>
</template>

<script>
export default {
  name: "Navbar",
  data() {
    return {
      links: [{ title: "Home", name: "Home", roles: ["ALL"] }],
    };
  },
  computed: {
    isLoggedIn() {
      return this.$store.getters.isLoggedIn;
    },
  },
  methods: {
    logout() {
      this.$store.dispatch("logout");
      this.$router.push("/login");
    },
    toggleLogin() {
      this.showLogin = true;
    },
    hideLogin() {
      this.showLogin = false;
    },
  },
};
</script>