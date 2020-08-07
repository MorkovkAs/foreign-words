<template>
  <div id="app">
    <h1>Words check</h1>
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
      words: []
    }
  },
  mounted() {
    fetch("/api/word/random/5")
      .then(response => response.json())
      .then(json => {
        console.log(json)
        this.words = json
      })
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
