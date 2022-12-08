package com.kgproject.service.impl;

import com.kgproject.model.entity.Connection;
import com.kgproject.dao.ConnectionMapper;
import com.kgproject.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectionServiceimpl implements ConnectionService {
    @Autowired
    ConnectionMapper connectionMapper;

    @Override
    public Connection findConnectionById(Integer id) {
        return connectionMapper.findConnectionById(id);
    }

    @Override
    public List<Connection> findConnectionBySource(String SourceNode) {
        return connectionMapper.findConnectionBySource(SourceNode);
    }

    @Override
    public List<Connection> findConnectionByTarget(String TargetNode) {
        return connectionMapper.findConnectionByTarget(TargetNode);
    }

    @Override
    public List<Connection> findConnectionByNode(String Node) {
        return connectionMapper.findConnectionByNode(Node);
    }

    @Override
    public List<Connection> findAllConnection() {
        return connectionMapper.findAllConnection();
    }
}

