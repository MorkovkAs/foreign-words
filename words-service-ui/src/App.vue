<template>
  <div id="app">
    <h1>Words check</h1>
    <WordsLayout
        v-on:update-check-result="updateCheckResult"
        v-on:edit-word="editWord"
        v-bind:words="words"
        style="height: 70vh"
    />
    <modal name="my-first-modal"
           :adaptive="true"
           :draggable="true"
           :resizable="true">
      <WordEdit v-bind:initialWord="words[getIndex(idForEdit)]"
                v-on:edit-word-save="editWordSave"
      />
    </modal>
    <Footer/>
  </div>
</template>

<script>
import WordsLayout from '@/components/WordsLayout.vue'
import WordEdit from '@/components/WordEdit.vue'
import Footer from '@/components/Footer.vue'
import axios from 'axios'

export default {
  name: 'App',
  components: {
    WordsLayout, WordEdit, Footer
  },
  data() {
    return {
      words: [],
      idForEdit: 0,
      url: ""// "http://localhost:8080/"
    }
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
          })
          .catch(error => console.log(error));
    },
    editWord(id) {
      this.idForEdit = id
      this.show()
    },
    show() {
      this.$modal.show('my-first-modal');
    },
    hide() {
      this.$modal.hide('my-first-modal');
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
