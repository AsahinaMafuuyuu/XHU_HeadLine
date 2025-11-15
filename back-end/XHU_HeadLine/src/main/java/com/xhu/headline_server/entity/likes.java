package com.xhu.headline_server.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 点赞关系类
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class likes {
    private String userId; // 点赞的用户ID
    private String targetId; // 被点赞的目标ID（可以是新闻帖子ID或评论ID）
    private Integer target_type ; // 目标类型（1表示新闻帖子，2表示评论）
    private String create_time; // 点赞时间

}
