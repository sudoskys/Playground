<script setup lang="ts">
import HomePage from '@/components/HomePage.vue';
</script>

<template>
  <div
      class="bg-amber text-white p-4 w-full h-full flex flex-col justify-center items-center"
  >
  <HomePage>
    <template #nav="{ message }">
      <nav>Child Message:{{ message }}</nav>
    </template>
    <template #content>
      <p>This is the content slot.</p>
    </template>
  </HomePage>
  </div>
</template>