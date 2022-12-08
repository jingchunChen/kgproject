package com.kgproject.controller;

import com.kgproject.service.*;
import com.kgproject.utils.JwtUtil;
import com.kgproject.utils.OssUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class BaseController {
    @Autowired
    protected OssUtil ossUtil;

    @Autowired
    protected ConnectionService connectionService;

    @Autowired
    protected KnowledgeNodeService knowledgeNodeService;
    @Autowired
    protected UserService userService;

    @Autowired
    protected LoginService loginService;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    protected MindService mindService;

    @Autowired
    protected IBmsBillboardService bmsBillboardService;

    @Autowired
    protected IBmsCommentService bmsCommentService;

    @Autowired
    protected IBmsPostService iBmsPostService;

    @Autowired
    protected IBmsFollowService bmsFollowService;

    @Autowired
    protected IBmsPostService postService;

    @Autowired
    protected IBmsTagService bmsTagService;

    @Autowired
    protected UserQuesService userQuesService;

    @Autowired
    protected QuesService quesService;

    protected String getUserIdByToken(HttpServletRequest request) throws Exception {
        String token = request.getHeader("token");
        // 到达此处时已通过JwtAuthenticationTokenFilter的过滤，实际上并不会存在token非法的情况
        Claims claims = JwtUtil.parseJWT(token);
        String userid = claims.getSubject();
        return userid;
    }
}
