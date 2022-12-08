package com.kgproject.service;

import com.kgproject.model.entity.KnowledgeNode;

import java.util.List;

public interface KnowledgeNodeService {
    KnowledgeNode findKnowledgeNodeById(Integer id);
    KnowledgeNode findKnowledgeNodeByName(String node);
    List<KnowledgeNode> fuzzyQueryNode(String node);
    List<KnowledgeNode> findAllKnowledgeNode();
    void insertKnowledgeNode(String node, String content);
}
