import {createApp} from 'vue'
import App from './App.vue'
// main.ts
import 'virtual:uno.css'
// Vuetify, a Material Design Component Framework for Vue
import 'vuetify/styles'
import {createVuetify} from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import router from './router';

const vuetify = createVuetify({
    components,
    directives,
})

createApp(App).use(vuetify).use(router).mount('#app')
