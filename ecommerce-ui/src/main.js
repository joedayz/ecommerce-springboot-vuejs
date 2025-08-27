import { createApp } from 'vue'
import { reactive } from 'vue'
import App from './App.vue'
import router from './router'

window.axios = require('axios')
import Swal from 'sweetalert2';
window.Swal = Swal;

// Create a reactive auth store
const authStore = reactive({
  token: localStorage.getItem('token'),
  updateToken() {
    this.token = localStorage.getItem('token');
  }
});

const app = createApp(App)

// Provide the auth store globally
app.provide('authStore', authStore)

// Global component for Ionicons
app.component('ion-icon', {
  props: ['name'],
  template: '<span v-html="iconSvg"></span>',
  data() {
    return {
      iconSvg: ''
    }
  },
  async mounted() {
    try {
      // Wait for Ionicons to be available
      await this.waitForIonicons();
      const icon = await window.ionicons.loadIcon(this.name);
      this.iconSvg = icon;
    } catch (error) {
      console.warn('Ionicons not available, using fallback');
      // Fallback to Bootstrap Icons
      const iconMap = {
        'cart-outline': '<i class="bi bi-Cart-plus"></i>',
        'heart-outline': '<i class="bi bi-heart"></i>',
        'heart': '<i class="bi bi-heart-fill"></i>',
        'person-outline': '<i class="bi bi-person"></i>',
        'home-outline': '<i class="bi bi-house"></i>',
        'search-outline': '<i class="bi bi-search"></i>'
      };
      this.iconSvg = iconMap[this.name] || '<i class="bi bi-question-circle"></i>';
    }
  },
  methods: {
    waitForIonicons() {
      return new Promise((resolve) => {
        if (window.ionicons) {
          resolve();
        } else {
          const checkInterval = setInterval(() => {
            if (window.ionicons) {
              clearInterval(checkInterval);
              resolve();
            }
          }, 100);
          
          // Timeout after 5 seconds
          setTimeout(() => {
            clearInterval(checkInterval);
            resolve();
          }, 5000);
        }
      });
    }
  }
});

app.use(router).mount('#app')
