package com.kgproject.service.impl;

import com.kgproject.model.entity.KnowledgeNode;
import com.kgproject.dao.KnowledgeNodeMapper;
import com.kgproject.service.KnowledgeNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowledgeNodeServiceImpl implements KnowledgeNodeService {
    @Autowired
    KnowledgeNodeMapper knowledgeNodeMapper;
    @Override
    public KnowledgeNode findKnowledgeNodeById(Integer id) {
        return knowledgeNodeMapper.findKnowledgeNodeById(id);
    }

    @Override
    public KnowledgeNode findKnowledgeNodeByName(String node) {
        return knowledgeNodeMapper.findKnowledgeNodeByName(node);
    }

    @Override
    public List<KnowledgeNode> fuzzyQueryNode(String node) {
        return knowledgeNodeMapper.fuzzyQueryNode(node);
    }

    @Override
    public List<KnowledgeNode> findAllKnowledgeNode() {
        return knowledgeNodeMapper.findAllKnowledgeNode();
    }

    @Override
    public void insertKnowledgeNode(String node, String content) {
        knowledgeNodeMapper.insertKnowledgeNode(node, content);
    }
}
