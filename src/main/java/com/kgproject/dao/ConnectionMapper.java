package com.kgproject.dao;

import com.kgproject.model.entity.Connection;

import java.util.List;

public interface ConnectionMapper {
    Connection findConnectionById(Integer id);
    List<Connection> findAllConnection();
    List<Connection> findConnectionBySource(String SourceNode);
    List<Connection> findConnectionByTarget(String TargetNode);
    List<Connection> findConnectionByNode(String node);
}
