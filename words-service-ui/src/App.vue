<template>
  <div id="app">
    <h1>Words check</h1>
    <WordsLayout
        v-on:update-check-result="updateCheckResult"
        v-on:edit-word="editWord"
        v-bind:words="words"
        style="height: 70vh"
    />
    <modal name="modal-word-edit" transition="pop-out" :width="modalWidth" :focus-trap="true" :height="320">
      <WordEdit v-bind:initialWord="words[getIndex(idForEdit)]"
                v-on:edit-word-save="editWordSave"
                v-on:edit-word-cancel="hideModalEdit"/>
    </modal>
    <Footer/>
  </div>
</template>

<script>
import WordsLayout from '@/components/WordsLayout.vue'
import WordEdit from '@/components/WordEdit.vue'
import Footer from '@/components/Footer.vue'
import axios from 'axios'

const MODAL_WIDTH = 656

export default {
  name: 'App',
  components: {
    WordsLayout, WordEdit, Footer
  },
  data() {
    return {
      words: [],
      idForEdit: 0,
      url: "http://localhost:8080/",
      modalWidth: MODAL_WIDTH
    }
  },
  created() {
    this.modalWidth =
        window.innerWidth < MODAL_WIDTH ? MODAL_WIDTH / 2 : MODAL_WIDTH
  },
  mounted() {
    this.getWordList()
  },
  methods: {
    getWordList() {
      axios
          .get(this.url + "/api/word/random/9")
          .then(response => {
            this.words = response.data
          })
          .catch(error => console.log(error));
    },
    updateCheckResult(id, n) {
      axios
          .post(this.url + `/api/word/check/${id}/result/${n}`)
          .catch(error => console.log(error));
    },
    editWordSave(word) {
      let self = this
      axios
          .put(this.url + `/api/word/${word.id}`, word)
          .then(response => {
            self.$set(self.words, this.getIndex(response.data.id), response.data)
            self.hideModalEdit()
          })
          .catch(error => console.log(error));
    },
    editWord(id) {
      this.idForEdit = id
      this.showModalEdit()
    },
    showModalEdit() {
      this.$modal.show('modal-word-edit');
    },
    hideModalEdit() {
      this.$modal.hide('modal-word-edit');
    },
    getIndex(id) {
      return this.words.findIndex(word => word.id === id)
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
