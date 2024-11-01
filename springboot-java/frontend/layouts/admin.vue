<script setup lang="ts">
import { useAuthGuard } from '@/composables/useAuthGuard';

// 使用带有自定义配置的认证守卫
useAuthGuard({
  routes: true,
  redirectTo: '/login',
  adminOnly: false
})
</script>

<template>
  <div>
    <SidebarProvider
      :style="{
        '--sidebar-width': '16rem',
        '--sidebar-width-mobile': '16rem'
      }"
    >
      <AppSidebar />
      <div class="flex-1">
        <SidebarTrigger />
        <slot />
      </div>
    </SidebarProvider>
  </div>
</template>

<style scoped>

</style>