<script setup lang="ts">
definePageMeta({
  layout: 'admin',
})
import { useUserStore } from '~/stores/user'
import { storeToRefs } from 'pinia'
import { useRouter } from 'vue-router'
import { LogOut } from 'lucide-vue-next'

const userStore = useUserStore()
const router = useRouter()
const { user } = storeToRefs(userStore)

// 定义不同角色可以看到的统计数据
const stats = ref([
  { name: '总用户数', value: '2,345', change: '+12%', changeType: 'increase' },
  { name: '本月订单', value: '456', change: '+4.75%', changeType: 'increase' },
  { name: '总收入', value: '¥123,456', change: '+10.18%', changeType: 'increase' },
  { name: '平均订单金额', value: '¥270', change: '-3.38%', changeType: 'decrease' },
])

// 最近用户列表
const recentUsers = ref([
  { id: 1, email: 'user1@example.com', createdAt: '2024-03-20', role: 'USER' },
  { id: 2, email: 'user2@example.com', createdAt: '2024-03-19', role: 'USER' },
  // ... 更多用户数据
])

// 判断是否为管理员
const isAdmin = computed(() => user.value?.role === 'ADMIN')

// 添加登出方法
const handleLogout = async () => {
  try {
    await userStore.logout()
    await router.push('/login')
  } catch (error) {
    console.error('登出失败:', error)
  }
}

// 添加客户端专用的欢迎信息
const welcomeMessage = computed(() => {
  return `欢迎回来${user.value?.email ? ', ' + user.value.email : ''}`
})
</script>

<template>
  <div class="p-6">
    <header class="flex justify-between items-center mb-6">
      <div class="space-y-4">
        <h1 class="text-3xl font-bold tracking-tight">仪表盘</h1>
        <client-only>
          <p class="text-muted-foreground">
            {{ welcomeMessage }}
          </p>
        </client-only>
      </div>
      
      <Button 
        variant="outline" 
        @click="handleLogout"
        class="flex items-center gap-2"
      >
        <LogOut class="h-4 w-4" />
        登出
      </Button>
    </header>

    <!-- 使用 client-only 包装所有依赖用户数据的部分 -->
    <client-only>
      <!-- 管理员统计卡片 -->
      <div v-if="isAdmin" class="grid gap-4 md:grid-cols-2 lg:grid-cols-4 mt-6">
        <Card v-for="stat in stats" :key="stat.name">
          <CardHeader class="flex flex-row items-center justify-between space-y-0 pb-2">
            <CardTitle class="text-sm font-medium">
              {{ stat.name }}
            </CardTitle>
          </CardHeader>
          <CardContent>
            <div class="text-2xl font-bold">{{ stat.value }}</div>
            <p class="text-xs text-muted-foreground">
              <span :class="stat.changeType === 'increase' ? 'text-green-600' : 'text-red-600'">
                {{ stat.change }}
              </span>
              相比上月
            </p>
          </CardContent>
        </Card>
      </div>

      <!-- 管理员用户列表 -->
      <div v-if="isAdmin" class="mt-6">
        <Card>
          <CardHeader>
            <CardTitle>最近注册用户</CardTitle>
            <CardDescription>
              查看最近注册的用户列表
            </CardDescription>
          </CardHeader>
          <CardContent>
            <Table>
              <TableHeader>
                <TableRow>
                  <TableHead>ID</TableHead>
                  <TableHead>邮箱</TableHead>
                  <TableHead>注册时间</TableHead>
                  <TableHead>角色</TableHead>
                  <TableHead>操作</TableHead>
                </TableRow>
              </TableHeader>
              <TableBody>
                <TableRow v-for="user in recentUsers" :key="user.id">
                  <TableCell>{{ user.id }}</TableCell>
                  <TableCell>{{ user.email }}</TableCell>
                  <TableCell>{{ user.createdAt }}</TableCell>
                  <TableCell>{{ user.role }}</TableCell>
                  <TableCell>
                    <Button variant="ghost" size="sm">
                      查看详情
                    </Button>
                  </TableCell>
                </TableRow>
              </TableBody>
            </Table>
          </CardContent>
        </Card>
      </div>

      <!-- 普通用户界面 -->
      <div v-else class="mt-6">
        <Card>
          <CardHeader>
            <CardTitle>我的账户信息</CardTitle>
          </CardHeader>
          <CardContent>
            <div class="space-y-4">
              <div class="grid grid-cols-2 gap-4">
                <div>
                  <label class="text-sm font-medium">邮箱</label>
                  <p class="text-muted-foreground">{{ user?.email }}</p>
                </div>
                <div>
                  <label class="text-sm font-medium">角色</label>
                  <p class="text-muted-foreground">{{ user?.role }}</p>
                </div>
              </div>
              
              <div class="pt-4">
                <Button>修改密码</Button>
              </div>
            </div>
          </CardContent>
        </Card>
      </div>
    </client-only>
  </div>
</template>
