<template>
  <li v-bind:class="getClass()" v-on:click="wordChecked = true">
    <section class="line">
      <span class="wordName"> {{ word.translation }} </span>
      <button class="editButton" v-on:click.stop="clickEdit">
        <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
      </button>
    </section>
    <p><b v-show="wordChecked"> {{ word.name }} </b></p>
    <button class="helpButton" v-if="!wordChecked" v-on:click.stop="clickHelp">
      <font-awesome-icon icon="question"/>
    </button>
    <section v-show="wordChecked">
      <button class="correctButton" v-on:click.stop="clickCorrect">Correct</button>
      <button class="mistakeButton" v-on:click.stop="clickMistake">Mistake</button>
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
    clickHelp() {
      console.log('help')
    },
    clickEdit() {
      this.$emit('edit-word', this.word.id)
    },
    clickCorrect() {
      let self = this
      self.wordCorrect = true
      self.wordMistake = false
      self.$emit('update-check-result', self.word.id, 1)
    },
    clickMistake() {
      let self = this
      self.wordCorrect = false
      self.wordMistake = true
      self.$emit('update-check-result', self.word.id, -1)
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
  border: 1.5px solid #390701;
  border-radius: 5%;
  padding: .5rem 2rem;
  font-size: 25px;
  height: 9rem;
}

button {
  cursor: pointer;
  border-radius: 10%;
  font-weight: bold;
}

.correct {
  background: #7bb526;
  color: #fff;
}

.mistake {
  background: #ef8575;
  color: #fff;
}

.helpButton {
  font-size: 20px;
}

.editButton {
  font-size: 20px;
  flex: 0 1 auto;
  margin-left: auto;
}

.correctButton {
  background: #347403;
  color: #fff;
  font-size: 15px;
  margin-right: 1rem;
  width: 6rem
}

.mistakeButton {
  background: #f32c22;
  color: #fff;
  font-size: 15px;
  margin-left: 1rem;
  width: 6rem;
}

.line {
  display: flex;
  justify-content: flex-start;
  position: relative;
}

.wordName {
  flex: 0 1 auto;
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
}
</style>