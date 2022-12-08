package com.kgproject.controller;

import com.kgproject.model.entity.Connection;
import com.kgproject.model.entity.KnowledgeNode;
import com.kgproject.model.entity.ResponseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class SerachController extends BaseController{

    @ResponseBody
    @RequestMapping("/map")
    public ResponseResult defaultMap() {
        List<Connection> links = connectionService.findAllConnection();
        /*
        对图结点初始化，如果connection中出现了kn中不存在的结点，则往kn中插入该结点。当出现kn结点和connection结点不一致时使用
         */
//        for(Connection link : links) {
//            String src = link.getSource(), dst = link.getTarget();
//            if(knowledgeNodeService.findKnowledgeNodeByName(src) == null) knowledgeNodeService.insertKnowledgeNode(src, "");
//            if(knowledgeNodeService.findKnowledgeNodeByName(dst) == null) knowledgeNodeService.insertKnowledgeNode(dst, "");
//        }
        List<KnowledgeNode> nodes = knowledgeNodeService.findAllKnowledgeNode();
        return ResponseResult.success("查询成功！").add("links", links).add("nodes", nodes);
    }

    @ResponseBody
    @RequestMapping("/search")
    public ResponseResult searchMap(String node) {
        List<KnowledgeNode> nodes = knowledgeNodeService.fuzzyQueryNode(node);
        Set<KnowledgeNode> res = new HashSet<>();
        Set<Connection> links = new HashSet<>();
        for (KnowledgeNode knowledgeNode : nodes) {
            List<Connection> cList = connectionService.findConnectionByNode(knowledgeNode.getNode());
            links.addAll(cList);
            res.add(knowledgeNode);
            for (Connection connection : cList) {
                res.add(knowledgeNodeService.findKnowledgeNodeByName(connection.getSource()));
                res.add(knowledgeNodeService.findKnowledgeNodeByName(connection.getTarget()));
            }
        }
        return ResponseResult.success("查询成功！").add("nodes", res).add("links", links);
    }

    void floyd(boolean[][] f, int n) {
        for(int k = 0;k < n;k++) {
            for(int i = 0;i < n;i++) {
                for(int j = 0;j < n;j++) {
                    if(f[i][k] && f[k][j]) f[i][j] = true;
                }
            }
        }
    }

    @ResponseBody
    @GetMapping("/findPath")
    public ResponseResult findMapPath(String src, String dst) {
        List<KnowledgeNode> allNodes = knowledgeNodeService.findAllKnowledgeNode();
        List<Connection> edges = connectionService.findAllConnection();
        HashMap<String, Integer> nodeMap = new HashMap<>();
        int cnt = 0;
        for (KnowledgeNode node : allNodes) {
            nodeMap.put(node.getNode(), cnt++);
        }
        int st = nodeMap.get(src), ed = nodeMap.get(dst);
        boolean[][] f = new boolean[cnt][cnt];
        for (Connection edge : edges) {
            int from = nodeMap.get(edge.getSource()), to = nodeMap.get(edge.getTarget());
            f[from][to] = true;
        }
        floyd(f, cnt);
        boolean[] vis = new boolean[cnt];
        for (int i = 0;i < cnt;i++) {
            if(f[st][i] && f[i][ed]) vis[i] = true;
        }
        vis[st] = vis[ed] = true;
        List<KnowledgeNode> nodes = new ArrayList<>();
        List<Connection> links = new ArrayList<>();
        for (KnowledgeNode node : allNodes) {
            int id = nodeMap.get(node.getNode());
            if(vis[id]) {
                nodes.add(node);
            }
        }
        for (Connection edge : edges) {
            int from = nodeMap.get(edge.getSource()), to = nodeMap.get(edge.getTarget());
            if(vis[from] && vis[to]) {
                links.add(edge);
            }
        }
        return ResponseResult.success("查询成功！").add("nodes", nodes).add("links", links);
    }
}
