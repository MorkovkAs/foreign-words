<template>
  <div class="WordsLayout">
    <ul>
      <WordItem
          v-for="word of words"
          v-bind:word="word"
          :key="word.id"
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
</style>