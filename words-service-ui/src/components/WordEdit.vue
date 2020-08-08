<template>
  <div class="WordEdit">
    <label>Name:
      <input v-model="word.name">
    </label>
    <label>Translation:
      <input v-model="word.translation">
    </label>
    <button v-on:click="$emit('edit-word-save', word)">Save</button>
  </div>
</template>

<script>
export default {
  name: "WordEdit",
  props: {
    initialWord: {
      name: String,
      translation: String,
      required: true
    }
  },
  data: function () {
    return {
      word: JSON.parse(JSON.stringify(this.initialWord))
    }
  }
}
</script>

<style scoped>

</style>