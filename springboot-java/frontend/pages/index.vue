<template>
  <div class="container mx-auto p-4">
    <!-- 页面标题 -->
    <Container>
      <h1 class="text-2xl font-bold mb-6">热门商品</h1>
      <!-- 商品网格 -->
      <Grid :cols="{ base: 1, sm: 2, md: 3, lg: 4 }" gap="4">
        <Card v-for="product in products" :key="product.id">
          <template #header>
            <img :src="product.image" :alt="product.name" class="w-full h-48 object-cover">
          </template>
          <h3 class="font-bold text-lg">{{ product.name }}</h3>
          <p class="text-gray-600 mt-2">{{ product.description }}</p>

          <template #footer>
            <div class="flex justify-between items-center">
              <span class="text-primary text-xl font-bold">¥{{ product.price }}</span>
              <Button color="primary">
                加入购物车
              </Button>
            </div>
          </template>
        </Card>
      </Grid>
    </Container>
  </div>
</template>

<script setup>
import {Card} from "~/components/ui/card/index.js"

const products = ref([
  {
    id: 1,
    name: '商品 1',
    description: '这是一个很棒的商品描述',
    price: 199,
    image: 'https://picsum.photos/400/300'
  },
  {
    id: 2,
    name: '商品 2',
    description: '这是另一个很棒的商品描述',
    price: 299,
    image: 'https://picsum.photos/400/300'
  },
  {
    id: 3,
    name: '商品 3',
    description: '这是第三个很棒的商品描述',
    price: 399,
    image: 'https://picsum.photos/400/300'
  },
  {
    id: 4,
    name: '商品 4',
    description: '这是第四个很棒的商品描述',
    price: 499,
    image: 'https://picsum.photos/400/300'
  }
])
</script>