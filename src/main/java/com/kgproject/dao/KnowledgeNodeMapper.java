package com.kgproject.dao;

import com.kgproject.model.entity.KnowledgeNode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KnowledgeNodeMapper {
    KnowledgeNode findKnowledgeNodeById(Integer id);
    KnowledgeNode findKnowledgeNodeByName(String node);
    List<KnowledgeNode> fuzzyQueryNode(String node);
    List<KnowledgeNode> findAllKnowledgeNode();
    void insertKnowledgeNode(@Param("node") String node, @Param("content") String content);
}
