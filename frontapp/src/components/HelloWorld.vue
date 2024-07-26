<template>
  <div class="hello">
    <h1>{{ message }}</h1>
    <button @click="fetchMessage">Fetch Message</button>
  </div>
</template>

<script>
export default {
  name: "HelloWorld",
  data() {
    return {
      message: "Hello Vue!",
    };
  },
  methods: {
    fetchMessage() {
      console.log("Before fecth");
      //fetch("http://localhost:8080/hello")
      fetch("http://127.0.0.1:53557/hello")
        .then((response) => response.text())
        .then((data) => {
          this.message = data;
          console.log("Response text:", data);
        })
        .catch((error) => {
          console.error("Error fetching the message:", error);
        });
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
