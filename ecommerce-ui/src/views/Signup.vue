<template>
  <div class="container">
    <!--    Link to Home-->
    <div class="row">
      <div class="col-12 text-center pt-3">
        <router-link :to="{name : 'Home'}">
          <img id="logo" src="../assets/icon.png" />
        </router-link>
      </div>
    </div>
    <!--   Sign up form-->
    <div class="row">
      <div class="col-12 justify-content-center d-flex flex-row pt-5">
        <div id="signup-div" class="flex-item border">
          <h2 class="pt-4 pl-4">Create Account</h2>
          <form @submit="signup" class="pt-4 pl-4 pr-4">
            <div class="form-group">
              <label>Email</label>
              <input type="email" class="form-control" v-model="email" required>
            </div>
            <div class="form-row">
              <div class="col">
                <div class="form-group">
                  <label>First Name</label>
                  <input type="name" class="form-control" v-model="firstName" required>
                </div>
              </div>
              <div class="col">
                <div class="form-group">
                  <label>Last Name</label>
                  <input type="name" class="form-control" v-model="lastName" required>
                </div>
              </div>
            </div>
            <div class="form-group">
              <label>Password</label>
              <input type="password" class="form-control" v-model="password" required>
            </div>
            <div class="form-group">
              <label>Confirm Password</label>
              <input type="password" class="form-control" v-model="passwordConfirm" required>
            </div>
            <button type="submit" class="btn btn-primary mt-2 py-0">Create Account</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
const axios = require('axios')
import Swal from 'sweetalert2'
import { inject } from 'vue'

export default {
  name: 'Signup',
  props : ["baseURL"],
  setup() {
    const authStore = inject('authStore');
    return { authStore };
  },
  data() {
    return {
      email: null,
      firstName: null,
      lastName: null,
      password: null,
      passwordConfirm: null
    }
  },
  methods : {
    async signup(e) {
      e.preventDefault();
      
      // Check if baseURL is available
      if (!this.baseURL) {
        Swal.fire({
          title: 'Error',
          text: "Error de configuración. Inténtalo de nuevo",
          icon: "error",
          confirmButtonText: 'OK',
          allowOutsideClick: false,
        });
        return;
      }
      
      // if the password matches
      if (this.password === this.passwordConfirm) {

        // make the post body
        const user = {
          email: this.email,
          firstName: this.firstName,
          lastName: this.lastName,
          password: this.password
        }

        // call the API
        await axios({
          method : 'post',
          url : this.apiBaseURL + "user/signup",
          data : JSON.stringify(user),
          headers: {
            'Content-Type': 'application/json'
          }
        })
            .then(() => {
              // redirect to home page
              this.$router.replace("/");
              
              // Update auth store
              this.authStore.updateToken();
              
              Swal.fire({
                title: '¡Éxito!',
                text: "Usuario registrado exitosamente. Por favor inicia sesión",
                icon: "success",
                confirmButtonText: 'OK',
                allowOutsideClick: false,
              });
            })
            .catch(err => {
              console.log(err);
              Swal.fire({
                title: 'Error',
                text: "Error al registrar usuario. Inténtalo de nuevo",
                icon: "error",
                confirmButtonText: 'OK',
                allowOutsideClick: false,
              });
            });
      } else {
        // passwords are not matching
        Swal.fire({
          title: 'Error',
          text: "¡Error! Las contraseñas no coinciden",
          icon: "error",
          confirmButtonText: 'OK',
          allowOutsideClick: false,
        });
      }
    }
  },
  computed: {
    // Fallback baseURL if prop is not provided
    apiBaseURL() {
      const base = this.baseURL || "http://localhost:8080";
      // Ensure the URL ends with a slash
      return base.endsWith('/') ? base : base + '/';
    }
  }
}
</script>

<style scoped>

.btn-dark {
  background-color: #e7e9ec;
  color: #000;
  font-size: smaller;
  border-radius: 0;
  border-color: #adb1b8 #a2a6ac #a2a6ac;
}

.btn-primary {
  background-color: #f0c14b;
  color: black;
  border-color: #a88734 #9c7e31 #846a29;
  border-radius: 0;
}

#logo {
  width: 150px;
}

@media only screen and (min-width: 992px) {
  #signup-div {
    width: 40%;
  }
}
</style>