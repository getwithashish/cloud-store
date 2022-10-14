<template>
    <div class="tile is-parent">
    <div class="tile is-child box">
      <p class="title">{{this.data}}</p>
      <p>Testing Data</p>
    </div>
  </div>
</template>

<script>
export default {
    props: ["data"] 
}
</script>
