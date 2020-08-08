import Vue from 'vue'
import App from './App.vue'
import VModal from 'vue-js-modal'

import {library} from '@fortawesome/fontawesome-svg-core'
import {faPencilAlt, faQuestion} from '@fortawesome/free-solid-svg-icons'
import {FontAwesomeIcon} from '@fortawesome/vue-fontawesome'

library.add(faQuestion, faPencilAlt)

Vue.component('font-awesome-icon', FontAwesomeIcon)
Vue.config.productionTip = false

Vue.use(VModal, {
    dialog: true,
    dynamicDefaults: {
        draggable: true,
        resizable: true,
        adaptive: true
    }
})

new Vue({
    render: h => h(App),
}).$mount('#app')
