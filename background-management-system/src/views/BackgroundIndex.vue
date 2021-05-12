<template>

  <el-col :span="6">
    <!-- <h5>自定义颜色</h5> -->
    <el-menu
      :uniqueOpened="true"
      default-active="2"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b">
      <el-menu-item index="1">
        <template #title>
          <i class="el-icon-s-home"></i>
          <span>主页</span>
        </template>
      </el-menu-item>
      <el-submenu index="2">
        <template #title>
          <i class="el-icon-user"></i>
          <span>用户管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="2-1">用户信息</el-menu-item>
          <el-menu-item index="2-2">用户添加</el-menu-item>
          <el-menu-item index="2-3">管理员登记</el-menu-item>
          <el-menu-item index="2-4-1">记录查看</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="3">
        <template #title>
          <i class="el-icon-s-management"></i>
          <span>业务管理</span>
        </template>
        <el-menu-item-group
        >
          <el-menu-item index="3-1">委托</el-menu-item>
          <el-menu-item index="3-2">博客</el-menu-item>
          <el-menu-item index="3-3">文明吐槽</el-menu-item>
          <el-menu-item index="3-4">结伴同行</el-menu-item>
          <el-menu-item index="3-5">失物招领</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="4">
        <template #title>
          <i class="el-icon-setting"></i>
          <span>系统控制</span>
        </template>
        <el-menu-item-group
        >
          <el-menu-item index="4-1">管理员行为日志</el-menu-item>
          <el-menu-item index="4-2">系统概况</el-menu-item>
          <el-menu-item index="4-3">自动化维护设置</el-menu-item>
          <el-menu-item index="4-4">信箱</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
    </el-menu>
  </el-col>

</template>

<script>
export default {
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
  },
};
</script>

<style lang="less" scoped>
.el-menu-vertical-demo{
  max-width: 200px;
  min-width: 160px;
  height: 100%;
  .el-menu-item{
    min-width: 80px;
  }
}
</style>
