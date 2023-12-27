import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import AddCategory from "@/views/Category/AddCategory.vue";
import Category from "@/views/Category/Category.vue";
import EditCategory from "@/views/Category/EditCategory.vue";
import AddProduct from "@/views/Product/AddProduct.vue";
import Product from "@/views/Product/Product.vue";
import EditProduct from "@/views/Product/EditProduct.vue";

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: "/admin/category/add",
    name: "AddCategory",
    component: AddCategory,
  },
  {
    path: "/admin/category",
    name: "AdminCategory",
    component: Category,
  },
  {
    path: "/admin/category/:id",
    name: "EditCategory",
    component: EditCategory,
  },
  {
    path: "/admin/product/add",
    name: "AddProduct",
    component: AddProduct,
  },
  {
    path: "/admin/product",
    name: "AdminProduct",
    component: Product,
  },
  {
    path: "/admin/product/:id",
    name: "EditProduct",
    component: EditProduct,
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
