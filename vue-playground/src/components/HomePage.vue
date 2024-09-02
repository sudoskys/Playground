<script setup lang="ts">
import {ref} from 'vue';

const message = ref('Hello from Child HomePage');
</script>

<template>
  <div class="ma-0 p-6 bg-white bg-opacity-50 backdrop-blur-lg rounded-lg shadow-lg">
    <slot name="nav" :message="message"></slot>
    <br/>
    <slot name="content"></slot>
  </div>
</template>

<style scoped>
</style>