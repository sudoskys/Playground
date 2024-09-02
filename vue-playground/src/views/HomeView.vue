<script setup lang="ts">
import HomePage from '@/components/HomePage.vue';
</script>

<template>
  <HomePage>
    <template #nav="{ message }">
      <nav>Child Message:{{ message }}</nav>
    </template>
    <template #content>
      <p>This is the content slot.</p>
    </template>
  </HomePage>
</template>