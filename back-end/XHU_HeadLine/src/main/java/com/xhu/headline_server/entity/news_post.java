package com.xhu.headline_server.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 新闻帖子类
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class news_post {
    private long authorId;
    private int categoryId;
    private String title;
    private String content;
    private String coverImage;
    private int status; // 0表示草稿 1表示待审核 2表示已发布 3表示已拒绝
    private int viewCount;
    private int likeCount;
    private int commentCount;
    private String createTime;
    private String updateTime;
}
