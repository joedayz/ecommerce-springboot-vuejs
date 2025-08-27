<template>
  <div style="display: flex; align-items: center; justify-content: center">
    <div class="div_class">
      <h3>You will be redirected to payment page</h3>

      <div class="alert alert-primary" role="alert">
        While making payment use card number 4242 4242 4242 4242 and enter random cvv(3 digit)
      </div>

      <button class="btn checkout_button" id="proceed-to-checkout" @click="goToCheckout()">
        Make payment
      </button>
    </div>
  </div>
</template>
<script>

export default {
  data(){
    return {
      token: null,
    }
  },

  name:'Checkout',
  props:["baseURL"],
  methods: {


  },
  mounted(){
    // get the token
    this.token = localStorage.getItem("token");
  },
}

</script>

<style >

.div_class{
  margin-top: 5%;
  width: 50%;
  align-content: center;
}

.checkout_button{
  background-color: #5d3dec;
  border: none;
  color: white;
  padding: 15px 30px;
  text-align: center;
  text-decoration: none;
  font-size: 15px;
  font-weight: bold;
  border-radius: 15px;
}

.checkout_button:focus{
  outline: none;
  box-shadow: none;
}

.checkout_button:disabled{
  background-color: #9b86f7;
  border: none;
  color: white;
  padding: 15px 30px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 15px;
  font-weight: bold;
  border-radius: 15px;
  cursor:not-allowed;
}

</style>
