<template>
  <li v-on:click="wordChecked = true">
    <span> {{ word.translation }} </span>
    <span v-if="wordChecked"> {{ word.name }} </span>
    <button class="help" >&#63;</button>
    <button class="correct" v-if="wordChecked">Correct</button>
    <button class="mistake" v-if="wordChecked">Mistake</button>
  </li>
</template>

<script>
export default {
  name: "WordItem",
  data() {
    return {
      wordChecked: false
    }
  },
  props: {
    word: {
      name: Object,
      required: true
    }
  },
  methods: {
    checkTheWord() {
      this.wordChecked = true
    }
  }
}
</script>

<style scoped>
li {
  border: 1px solid #3769a7;
  display: flex;
  justify-content: space-between;
  padding: .5rem 2rem;
  margin-bottom: 1rem;
}

.help {
  background: yellow;
  color: #000;
  border-radius: 30%;
  font-weight: bold;
}

.correct {
  background: yellowgreen;
  color: #fff;
  border-radius: 10%;
  font-weight: bold;
}

.mistake {
  background: indianred;
  color: #fff;
  border-radius: 10%;
  font-weight: bold;
}
</style>