<script setup lang="ts">
const runtimeConfig = useRuntimeConfig()
</script>

<template>

  <div>
    <NuxtRouteAnnouncer/>
    <div>
      pages/index.vue
      <h1>{{ runtimeConfig.title }}</h1>
      <p>{{ runtimeConfig.description }}</p>
    </div>
  </div>

</template>

<style scoped>

</style>