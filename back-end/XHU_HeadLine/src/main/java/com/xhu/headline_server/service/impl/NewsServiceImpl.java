package com.xhu.headline_server.service.impl;

import com.xhu.headline_server.entity.Comment;
import com.xhu.headline_server.entity.NewsPort;
import com.xhu.headline_server.mapper.CommentMapper;
import com.xhu.headline_server.mapper.NewsPortMapper;
import com.xhu.headline_server.service.NewsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class NewsServiceImpl implements NewsService {
    private final NewsPortMapper newsPortMapper;
    private final CommentMapper commentMapper;

    public NewsServiceImpl(NewsPortMapper newsPortMapper, CommentMapper commentMapper) {
        this.newsPortMapper = newsPortMapper;
        this.commentMapper = commentMapper;
    }

    @Override
    public Map<String, Object> getNewsList(int page, int size, String keyword, String categoryId, String sort) {
        List<NewsPort> rows = newsPortMapper.findNews(keyword, categoryId.isEmpty() ? null : Integer.parseInt(categoryId), sort, size, (page - 1) * size);
        int total = newsPortMapper.countNews(keyword, categoryId.isEmpty() ? null : Integer.parseInt(categoryId));

        return Map.of("rows", rows, "total", total);
    }

    @Override
    public Map<String, Object> getNewsDetail(Long id) {
        return newsPortMapper.findNewsById(id);
    }

    @Override
    public int addViewCount(Long id) {
         newsPortMapper.incrViewCount(id);
        return newsPortMapper.getViewCount(id);
    }

    @Override
    public Map<String, Object> addLikeCount(Long id) {
        newsPortMapper.incrLikeCount(id);
        int likeCount = newsPortMapper.getLikeCount(id);
        return Map.of("likeCount", likeCount, "liked", true);
    }

    @Override
    public Map<String, Object> delLikeCount(Long id) {
        newsPortMapper.decrLikeCount(id);
        int likeCount = newsPortMapper.getLikeCount(id);
        return Map.of("likeCount", likeCount, "liked", false);
    }


    @Override
    public Map<String, Object> getCommentList(long id, int page, int size) {
        int offset = (page - 1) * size;
        List<Comment> rows = commentMapper.listCommentsByPostId(id , offset , size);
        int total = commentMapper.countCommentsByPostId(id);

        return Map.of("rows",rows,"total",total,"page",page,"size",size);
    }


    @Override
    public Map<String, Object> addComment(long id, String content, long parentId) {
        Comment comment = new Comment();
        comment.setPostId(id);
        comment.setContent(content);
        comment.setParentId(parentId);
        comment.setCreateTime(String.valueOf(LocalDateTime.now()));
        commentMapper.insertComment(comment);

        return Map.of(
                "id", comment.getPostId(),
                "content", comment.getContent(),
                "parentId", comment.getParentId(),
                "createTime", comment.getCreateTime()
        );
    }


}
