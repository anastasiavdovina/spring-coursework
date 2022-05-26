package main.service;

import main.entity.Groups;

import java.util.List;

public interface GroupService {
    List<Groups> listGroups();
    Groups findGroup(long id);
    Groups addGroup(Groups group);
    void deleteById(Long id);
    void deleteAll();
}
