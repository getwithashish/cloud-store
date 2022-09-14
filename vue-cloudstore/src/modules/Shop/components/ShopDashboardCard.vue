<template>
    <div class="tile is-parent">
    <div class="tile is-child box">
      <p class="title">{{this.data}}</p>
      <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin ornare magna eros, eu pellentesque tortor vestibulum ut. Maecenas non massa sem. Etiam finibus odio quis feugiat facilisis.</p>
    </div>
  </div>
</template>

<script>
export default {
    props: ["data"]
}
</script>
