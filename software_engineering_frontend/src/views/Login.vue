<template>
  <b-container>
    <b-form v-on:submit.prevent="login">
      <b-row>
        <b-col cols="8" offset="2">
          <h3>Login</h3>
          <br />
          <b-alert :show="error != ''" variant="danger">{{ error }}</b-alert>
          <b-form-group label="Email address:" label-for="email">
            <b-form-input
              id="email"
              type="text"
              placeholder="Email"
              v-model="email"
            ></b-form-input>
          </b-form-group>
          <b-form-group label="Password:" label-for="password">
            <b-form-input
              id="password"
              type="password"
              placeholder="Password"
              v-model="password"
            ></b-form-input>
          </b-form-group>
          <b-button type="submit" variant="primary">Login</b-button>
        </b-col>
      </b-row>
    </b-form>
  </b-container>
</template>

<script>
export default {
  data() {
    return {
      email: "",
      password: "",
      error: "",
      isSubmitting: false,
    };
  },
  methods: {
    login() {
      this.isSubmitting = true;

      this.$store
        .dispatch("login", {
          email: this.email,
          password: this.password,
        })
        .then((res) => {
          if (res.data.success) {
            this.reset();
            this.$router.push("/");
          } else {
            this.error = res.data.message;
          }

          this.isSubmitting = false;
        });
    },
    reset() {
      this.email = "";
      this.password = "";
      this.error = "";
      this.isSubmitting = false;
    },
  },
};
</script>