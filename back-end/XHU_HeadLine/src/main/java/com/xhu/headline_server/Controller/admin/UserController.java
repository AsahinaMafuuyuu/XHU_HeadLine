package com.xhu.headline_server.Controller.admin;

import com.xhu.headline_server.entity.User;
import com.xhu.headline_server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 用户管理后台接口
 * 接口前缀：/admin/user
 */
@RestController
@RequestMapping("/admin/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 新增用户
     * POST /admin/user/add
     */
    @PostMapping("/add")
    public Map<String, Object> addUser(@RequestBody User user) {
        Map<String, Object> res = new HashMap<>();
        try {
            userService.addUser(user);
            res.put("code", 1);
            res.put("message", "用户添加成功");
            res.put("userName", user.getUserName());
        } catch (Exception e) {
            res.put("code", 0);
            res.put("message", "用户添加失败");
        }
        return res;
    }

    /**
     * 删除用户（根据 id）
     * POST /admin/user/delete
     * 请求体示例：{"id": 1}
     */
    @PostMapping("/delete")
    public Map<String, Object> deleteUser(@RequestBody Map<String, Object> params) {
        Map<String, Object> res = new HashMap<>();
        Long id = getLongParam(params, "id", null);
        if (id == null) {
            res.put("code", 0);
            res.put("message", "缺少用户 id");
            return res;
        }

        boolean ok = userService.delUserById(id);
        if (ok) {
            res.put("code", 1);
            res.put("message", "用户删除成功");
        } else {
            res.put("code", 0);
            res.put("message", "用户不存在或删除失败");
        }
        return res;
    }

    /**
     * 更新用户信息
     * POST /admin/user/update
     * 约定请求体中必须包含 id
     */
    @PostMapping("/update")
    public Map<String, Object> updateUser(@RequestBody User user) {
        Map<String, Object> res = new HashMap<>();
        if (user == null || user.getId() == null) {
            res.put("code", 0);
            res.put("message", "缺少用户 id，无法更新");
            return res;
        }

        try {
            userService.saveUser(user);
            res.put("code", 1);
            res.put("message", "用户信息已更新");
            res.put("userName", user.getUserName());
        } catch (Exception e) {
            res.put("code", 0);
            res.put("message", "用户更新失败");
        }
        return res;
    }

    /**
     * 根据 id 查询用户
     * POST /admin/user/get
     * 请求体示例：{"id": 1}
     */
    @PostMapping("/get")
    public Map<String, Object> getUser(@RequestBody Map<String, Object> params) {
        Map<String, Object> res = new HashMap<>();
        Long id = getLongParam(params, "id", null);
        if (id == null) {
            res.put("code", 0);
            res.put("message", "缺少用户 id");
            return res;
        }

        User user = userService.getUserById(id);
        if (Objects.isNull(user)) {
            res.put("code", 0);
            res.put("message", "用户不存在");
        } else {
            res.put("code", 1);
            res.put("message", "用户查询成功");
            res.put("user", user);
        }
        return res;
    }

    // ========== 工具方法：从 Map 中安全获取 Long 参数 ==========
    private Long getLongParam(Map<String, Object> params, String key, Long defaultVal) {
        Object v = params == null ? null : params.get(key);
        if (v == null) {
            return defaultVal;
        }
        if (v instanceof Number) {
            return ((Number) v).longValue();
        }
        try {
            return Long.parseLong(v.toString());
        } catch (Exception e) {
            return defaultVal;
        }
    }
}
