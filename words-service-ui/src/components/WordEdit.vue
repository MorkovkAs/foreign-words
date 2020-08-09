<template>
  <div class="WordEdit">
    <div class="box">
      <div class="box-part" id="bp-left">
        <div class="partition-title">Edit the word</div>
        <div class="partition-form">
          <form autocomplete="off">
            <label for="n-name">Name</label><input id="n-name" type="text" v-model="word.name" placeholder="Name">
            <input id="n-translation" type="text" v-model="word.translation" placeholder="Translation">
            <input id="n-partOfSpeech" type="text" v-model="word.partOfSpeech" placeholder="Part Of Speech">
            <input id="n-gender" type="text" v-model="word.gender" placeholder="Gender">
          </form>
          <div style="margin-top: 10px"/>
          <div class="button-set">
            <button id="save" @click="$emit('edit-word-save', word)">Save</button>
            <button id="cancel" @click="$emit('edit-word-cancel')">Cancel</button>
          </div>
        </div>
      </div>
      <div class="box-part" id="bp-right">
        <div class="box-messages"/>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "WordEdit",
  props: {
    initialWord: {
      name: String,
      translation: String,
      partOfSpeech: String,
      gender: String,
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
.box {
  background: white;
  overflow: hidden;
  width: 656px;
  height: 400px;
  border-radius: 2px;
  box-sizing: border-box;
  box-shadow: 0 0 40px black;
  color: #2c3e50;
  font-size: 0;
}

.box-part {
  display: inline-block;
  position: relative;
  vertical-align: top;
  box-sizing: border-box;
  height: 100%;
  width: 50%;
}

#bp-right {
  background: url('../assets/words.jpeg') no-repeat top left;
  border-left: 1px solid #eee;
}

.box-messages {
  position: absolute;
  left: 0;
  bottom: 0;
  width: 100%;
}

.partition {
  width: 100%;
  height: 100%;
}

.partition-title {
  box-sizing: border-box;
  padding: 30px;
  width: 100%;
  text-align: center;
  letter-spacing: 1px;
  font-size: 20px;
  font-weight: 300;
}

.partition-form {
  padding: 0 20px;
  box-sizing: border-box;
}

input[type='password'],
input[type='text'] {
  display: block;
  box-sizing: border-box;
  margin-bottom: 4px;
  width: 100%;
  font-size: 14px;
  line-height: 2;
  border: 0;
  border-bottom: 1px solid #dddedf;
  padding: 4px 8px;
  font-family: inherit;
  transition: 0.5s all;
}

button {
  background: white;
  border-radius: 4px;
  box-sizing: border-box;
  padding: 10px;
  letter-spacing: 1px;
  font-family: 'Open Sans', sans-serif;
  min-width: 100px;
  margin-top: 8px;
  color: #2c3e50;
  cursor: pointer;
  border: 1.2px solid #2c3e50;
  text-transform: uppercase;
  transition: 0.1s all;
  font-size: 10px;
  font-weight: bold;
}

.button-set {
  margin-bottom: 8px;
}

#save,
#cancel {
  margin-left: 8px;
}
</style>