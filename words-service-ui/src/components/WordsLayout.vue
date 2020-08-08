<template>
  <div class="WordsLayout">
    <ul :style="gridStyle"
        class="card-list">
      <WordItem
          v-for="word of words"
          v-bind:word="word"
          :key="word.id"
          v-on:update-check-result = "updateCheckResult"
          v-on:edit-word = "editWord"
      />
    </ul>
  </div>
</template>

<script>
import WordItem from '@/components/WordItem.vue'

export default {
  name: "WordsLayout",
  props: [
    'words'
  ],
  data() {
    return {
      numberOfColumns: 4
    }
  },
  computed: {
    gridStyle() {
      return {
        gridTemplateColumns: `repeat(${this.numberOfColumns}, minmax(100px, 1fr))`
      }
    },
  },
  methods: {
    updateCheckResult (id, n) {
      this.$emit ('update-check-result', id, n)
    },
    editWord (id) {
      this.$emit ('edit-word', id)
    }
  },
  components: {
    WordItem
  }
}
</script>

<style scoped>
ul {
  list-style: none;
  margin: 0;
  padding: 0;
}

.card-list {
  display: grid;
  grid-gap: 1em;
}
</style>