<template>
  <div id="app">
    <h1>Words check</h1>
    <hr>
    <WordsLayout
        v-bind:words="words"/>
    <Footer/>
  </div>
</template>

<script>
import WordsLayout from '@/components/WordsLayout.vue'
import Footer from '@/components/Footer.vue'

export default {
  name: 'App',
  data() {
    return {
      words: [
        {id: 1, name: 'arbeiten', translation: 'work', partOfSpeech: 'Verb', gender: null},
        {id: 2, name: 'lernen', translation: 'learn', partOfSpeech: 'Verb', gender: null},
        {id: 3, name: 'Mutter', translation: 'Mother', partOfSpeech: 'Noun', gender: 'FEMININE'},
        {id: 4, name: 'Vater', translation: 'Father', partOfSpeech: 'Noun', gender: 'MASCULINE'}
      ]
    }
  },
  components: {
    WordsLayout, Footer
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
