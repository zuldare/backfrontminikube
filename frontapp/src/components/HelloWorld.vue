<template>
  <div class="hello">
    <h1>Dragon Ball Character</h1>
    <div v-if="character">
      <h2>{{ character.name }}</h2>
      <p><strong>race:</strong> {{ character.race }}</p>
      <p><strong>Gender:</strong> {{ character.gender }}</p>
      <img :src="character.image" :alt="character.name" />
    </div>
    <div v-else>
      <p>Loading...</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "HelloWorld",
  data() {
    return {
      character: null,
    };
  },
  mounted() {
    fetch("https://dragonball-api.com/api/characters/1")
      .then((response) => response.json())
      .then((data) => {
        this.character = data;
      })
      .catch((error) => {
        console.error("Error fetching character:", error);
      });
  },
};
</script>

<style scoped>
.hello {
  text-align: center;
  margin-top: 40px;
}
img {
  max-width: 300px;
  height: auto;
}
</style>
