<template>
  <div class="login">
    <b-form v-on:submit.prevent="login">
      <h3>Login</h3>
      <br />
      <b-alert :show="error != ''" variant="danger">{{error}}</b-alert> 
      <b-form-group label="Email address:" class="info-value" label-for="email">
        <b-form-input id="email" class="info-title" type="text" placeholder="Email" v-model="email"></b-form-input>
      </b-form-group>
      <b-form-group label="Password:" class="info-value" label-for="password">
        <b-form-input id="password" class="info-title" type="password" placeholder="Password" v-model="password"></b-form-input>
      </b-form-group>
      <b-button type="submit" class="btn-custom primary width-150px">Login</b-button>
    </b-form>
  </div>
</template>

<script>
export default {
    data() {
        return {
            email: "",
            password: "",
            error: "",
            isSubmitting: false,
        }
    },
    methods: {
        login() {
            this.isSubmitting = true;

            this.$store.dispatch('login', {
                email: this.email,
                password: this.password,
            }).then((res) => {
                if(res.data.success) {
                    this.reset();
                    this.$router.push('/');
                } else {
                    this.error = res.data.message;
                }

                this.isSubmitting = false;
            })
        },
        reset() {
            this.email = "";
            this.password = "";
            this.error = "";
            this.isSubmitting = false;
        }
    }
}
</script>