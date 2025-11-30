package com.xhu.headline_server.Controller.user;


import com.xhu.headline_server.entity.NewsPort;
import com.xhu.headline_server.service.NewsService;
import com.xhu.headline_server.service.impl.NewPortServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserPostController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private NewPortServiceImpl newPortServiceImpl;

    // 查咨询列表
    // 请求体示例
    // params : { page , size , keyword , categoryId , sort}
    @PostMapping("/news")
    public Map<String, Object> getNewsList(@RequestParam Map<String, Object> params) {
        try {
            int page = Integer.parseInt(params.getOrDefault("page", "1").toString());
            int size = Integer.parseInt(params.getOrDefault("size", "10").toString());
            String keyword = params.getOrDefault("keyword", "").toString();
            String categoryId = params.getOrDefault("categoryId", "").toString();
            String sort = params.getOrDefault("sort", "time").toString();

            return newsService.getNewsList(page, size, keyword, categoryId, sort);
        } catch (Exception e) {
            return Map.of("error", "Invalid parameters");
        }
    }


    // 查咨询详情
    @GetMapping("/news/{id}")
    public Map<String , Object> getNewsDetail(@PathVariable Long id){
        Map<String , Object> res = new HashMap<>();
        res = newsService.getNewsDetail(id);
        return res;
    }

    // 增加浏览量
    @PostMapping("/news/{id}/view")
    public Map<String , Object> addViewCount(@PathVariable Long id){
        int viewCount = newsService.addViewCount(id);
        return Map.of("ViewCount" , viewCount);
    }

    // 点赞
    // params : { likeCount , linked}
    @PostMapping("/news/{id}/like")
    public Map<String, Object> likeNews(@PathVariable Long id, boolean liked) {
        Map<String, Object> res;
        if (liked) {
            res = newsService.delLikeCount(id);
        } else {
            res = newsService.addLikeCount(id);
        }
        return res;
    }


    // 查看评论
    // params : {page , size}
    @GetMapping("/news/{id}/comments")
    public Map<String , Object> comments(@PathVariable long id, @RequestParam Map<String ,Object> params){
        Map<String , Object> res = new HashMap<>();
        int page = Integer.parseInt(params.getOrDefault("page","1").toString());
        int size = Integer.parseInt(params.getOrDefault("size","5").toString());
        res = newsService.getCommentList(id,page,size);
        return res;
    }

    // 评论回复
    @PostMapping("news/{id}/comments")
    public Map<String, Object> addComments(@PathVariable long id, @RequestBody Map<String, Object> body) {
        String content = body.get("content").toString();
        long parentId = Long.parseLong(body.getOrDefault("parentId", "0").toString());
        return newsService.addComment(id, content, parentId);
    }


    // 用户端发表帖子
    @PostMapping("/news/post")
    public Map<String , Object> PostPort(@RequestBody Map<String , Object> params){
        String title = params.get("title").toString();
        String content = params.get("content").toString();
        int categoryId = Integer.parseInt(params.getOrDefault("categoryId","0").toString());
        String coverImages = params.get("coverImages").toString();
        int status = Integer.parseInt(params.getOrDefault("status", "1").toString());

        NewsPort post = new NewsPort();
        post.setTitle(title);
        post.setContent(content);
        post.setCategoryId(categoryId);
        post.setCoverImages(coverImages);
        post.setStatus(status);

        Long newId = newPortServiceImpl.saveNewsPort(post);

        return Map.of("id", newId);
    }

}
