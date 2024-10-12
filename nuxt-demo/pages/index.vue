<script setup lang="ts">
import AppAlert from "~/components/AppAlert.vue";

const runtimeConfig = useRuntimeConfig()
</script>

<template>
  <div>
    <h1>Welcome to the homepage</h1>
    <AppAlert>
      This is an auto-imported component.
    </AppAlert>
  </div>
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