<template>
  <div class="home">
    Logged in Page with Bearer token: Bearer: {{getBearer}}
  </div>
</template>

<script>
export default {
  computed: {
    getBearer() {
      return this.$store.getters.getToken;
    }
  }
}
</script>