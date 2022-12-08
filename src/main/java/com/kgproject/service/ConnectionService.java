package com.kgproject.service;

import com.kgproject.model.entity.Connection;

import java.util.List;

public interface ConnectionService {
    Connection findConnectionById(Integer id);
    List<Connection> findConnectionBySource(String SourceNode);
    List<Connection> findConnectionByTarget(String TargetNode);
    List<Connection> findConnectionByNode(String Node);
    List<Connection> findAllConnection();
}
