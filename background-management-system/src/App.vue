<template>
<div class="app">
  <div class="slider-nav">
  <el-row class="tac">
    <background-index />
  </el-row>
  </div>
</div>
</template>
<script>
import BackgroundIndex from '@/views/BackgroundIndex.vue';

export default {
  components: {
    BackgroundIndex,
  },
};
</script>
<style lang="less">
  #app{
    height: 100%;
    .app{
      height: 100%;
      .slider-nav{
        height: 100%;
        .tac{
          height: 100%;
        }
      }
    }
  }
</style>
