<template>
  <li v-bind:class="getClass()" v-on:click="wordChecked = true">
    <span> {{ word.translation }} </span>
    <p><b v-if="wordChecked"> {{ word.name }} </b></p>
    <button class="helpButton" v-if="!wordChecked">&#63;</button>
    <section v-if="wordChecked">
      <button class="correctButton" v-on:click="clickCorrect">Correct</button>
      <button class="mistakeButton" v-on:click="clickMistake">Mistake</button>
    </section>
  </li>
</template>

<script>
export default {
  name: "WordItem",
  data() {
    return {
      wordChecked: false,
      wordCorrect: false,
      wordMistake: false
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
    },
    clickCorrect() {
      this.wordCorrect = true
      this.wordMistake = false
    },
    clickMistake() {
      this.wordCorrect = false
      this.wordMistake = true
    },
    getClass() {
      return {
        'correct': this.wordCorrect,
        'mistake': this.wordMistake
      }
    }
  }
}
</script>

<style scoped>
li {
  border: 1px solid #3769a7;
  justify-content: space-between;
  padding: .5rem 2rem;
  font-size: 25px;
}

.correct {
  background: #a0ff98;
  color: #000;
}

.mistake {
  background: #ffb8b8;
  color: #000;
}

.helpButton {
  background: yellow;
  color: #000;
  border-radius: 30%;
  font-weight: bold;
  font-size: 20px;
}

.correctButton {
  background: yellowgreen;
  color: #fff;
  border-radius: 10%;
  font-weight: bold;
  font-size: 15px;
  margin-right: 1rem;
}

.mistakeButton {
  background: indianred;
  color: #fff;
  border-radius: 10%;
  font-weight: bold;
  font-size: 15px;
  margin-left: 1rem;
}
</style>