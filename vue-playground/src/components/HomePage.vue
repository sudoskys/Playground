<script setup lang="ts">
import {ref} from 'vue';

const message = ref('Hello from Child HomePage');
</script>

<template>
  <div>
    <slot name="nav" :message="message"></slot>
    <br/>
    <slot name="content"></slot>
  </div>
</template>

<style scoped>
</style>