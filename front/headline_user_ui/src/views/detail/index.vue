<script setup>
import { ArrowLeft, MoreFilled, Promotion } from '@element-plus/icons-vue';
import { useRoute, useRouter } from 'vue-router';
import { ref } from 'vue';
import Heart from '@/asset/img/Love.svg'

const route = useRoute()     // 当前路由对象（取参用）
const router = useRouter()   // 路由实例（跳转用）

const articleId = route.query.articleId
const commentContent = ref('')
const articleUserAvatarUrl = ref('https://s2.loli.net/2025/05/22/qrUgRLaV97hxmy8.png')
const avatarUrl = ref('https://s2.loli.net/2025/11/18/XqS4kF6RnIZJLgm.png')
const articleUserName = ref('朝比奈真冬')
const publishTime = ref('2025年12月1日 20:00')

</script>

<template>
  <div class="allContentContainer">
    <!-- 头部 -->
    <div class="header">
      <div class="leftHeaderContainer">
        <el-icon><ArrowLeft /></el-icon>
        <span>文章详情</span>
      </div>
      <el-icon><MoreFilled /></el-icon>
    </div>

    <!-- 中间文章展示 -->
    <div class="articleContentDisplay">
      <div class="articleUserContainer">
        <img style="width: 35px; height: 35px; object-fit: cover; border-radius: 999px;" :src="articleUserAvatarUrl" alt="">
        <div class="nameAndPublishTime">
          <span style="font-size: 14px;">{{ articleUserName }}</span>
          <span style="font-size: 12px; color: rgba(0, 0, 0, 0.3);">{{ publishTime }}</span>
        </div>
      </div>
    </div>

    <!-- 底部导航 -->
    <div class="footer">
      <img class="userSelfAvatar" :src="avatarUrl" alt="用户头像">
      <div class="sendComponents">
        <el-input v-model="commentContent" class="userCommentInput" placeholder="本公主来喵两句"></el-input>
        <el-icon :size="30"><Promotion /></el-icon>
      </div>
      <img class="LikedIcon" :src="Heart" alt="点赞">
    </div>
  </div>


</template>

<style scoped>
.allContentContainer {
  height: 100vh;
  display: flex;
  flex-direction: column;
}
.leftHeaderContainer {
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 10px;
}
.header {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 15px;
  border-bottom: 1px solid rgb(191, 191, 191);
}
.articleContentDisplay {
  flex: 1 1 auto;
  overflow: scroll;
  min-height: 0;
  padding: 10px;
}
.articleUserContainer {
  display: flex;
  flex-direction: row;
  align-items: center;
  gap:10px
}
.nameAndPublishTime {
  display: flex;
  flex-direction: column;
}
.footer {
  border-top: 1px solid rgb(191, 191, 191);
  padding: 10px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.userSelfAvatar {
  width: 30px;
  height: 30px;
  border-radius: 999px;
}
.sendComponents {
  display: flex;
  align-items: center;
}
.userCommentInput {
  border-radius: 90px;
  width: 70vw;
  border: none;
}
.LikedIcon {
  width: 30px;
  height: 30px;
}

/* 输入框样式 */
:deep(.el-input__wrapper) {
  background-color: #f5f7fa;
  border-radius: 10px;
  border: 2px solid #a2a6a9;
  color: #333;
  font-size: 16px;
}
:deep(.el-input__wrapper:hover) {
  box-shadow: 0 0 0 1px rgba(0, 0, 0, 0.6) inset;
}
</style>
