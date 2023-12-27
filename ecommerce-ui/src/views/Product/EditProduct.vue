<template>
  <div class="container">
    <div class="row">
      <div class="col-12 text-center">
        <h3 class="pt-3">Edit Product</h3>
      </div>
    </div>

    <div class="row">
      <div class="col-3"></div>
      <div class="col-md-6 px-5 px-md-0">
        <form>
          <div class="form-group">
            <label>Category</label>
            <select class="form-control" v-model="categoryId" required>
              <option v-for="category of categories" :key="category.id" :value="category.id">{{category.categoryName}}</option>
            </select>
          </div>
          <div class="form-group">
            <label>Name</label>
            <input type="text" class="form-control" v-model="name" required>
          </div>
          <div class="form-group">
            <label>Description</label>
            <input type="text" class="form-control" v-model="description" required>
          </div>
          <div class="form-group">
            <label>ImageURL</label>
            <input type="url" class="form-control" v-model="imageURL" required>
          </div>
          <div class="form-group">
            <label>Price</label>
            <input type="number" class="form-control" v-model="price" required>
          </div>
          <button type="button" class="btn btn-primary" @click="editProduct">Submit</button>
        </form>
      </div>
      <div class="col-3"></div>
    </div>
  </div>
</template>
<script>
var axios = require('axios');
import Swal from 'sweetalert2';

export default {
  data(){
    return {
      id: null,
      categoryId: 0,
      name: null,
      description: null,
      imageURL: null,
      price: null,
      productIndex: null
    }
  },
  props: ["baseURL", "categories", "products"],
  methods: {
    async editProduct(){
      const updatedProduct = {
        id: this.id,
        categoryId: this.categoryId,
        name: this.name,
        description: this.description,
        imageURL: this.imageURL,
        price: this.price
      }
      await axios({
        method: 'post',
        url: this.baseURL + '/product/update/' + this.id,
        data: JSON.stringify(updatedProduct),
        headers: {
            'Content-Type': 'application/json'
        }
      }).then( ()=>{
        this.$emit("fetchData");
        this.$router.push({name: 'AdminProduct'});
        Swal.fire({
          text: 'Product updated successfully!',
          icon: 'success',
          allowOutsideClick: false
        });
      })
      .catch(err => console.log(err));
    }
  },
  mounted() {
    this.id = this.$route.params.id;
    this.productIndex = this.products.findIndex(product => product.id == this.id);
    this.categoryId = this.products[this.productIndex].categoryId;
    this.name = this.products[this.productIndex].name;
    this.description = this.products[this.productIndex].description;
    this.imageURL = this.products[this.productIndex].imageURL;
    this.price = this.products[this.productIndex].price;

  }
}


</script>


<style>
h4 {
  font-family: 'Roboto', sans-serif;
  color: #484848;
  font-weight: 700;
}
</style>