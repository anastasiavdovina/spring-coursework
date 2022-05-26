package main.service;

import main.entity.Groups;
import main.exception.GroupNotFoundException;
import main.repository.GroupsRepository;
import main.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService{
    private PeopleRepository peopleRepository;
    @Autowired
    private GroupsRepository groupsRepository;

    @Override
    public List<Groups> listGroups() {
        return (List<Groups>) groupsRepository.findAll();
    }

    @Override
    public Groups findGroup(long id) {
        Optional<Groups> optionalGroup = groupsRepository.findById(id);
        if (optionalGroup.isPresent()) {
            return optionalGroup.get();
        } else {
            throw new GroupNotFoundException("Group not found");
        }
    }

    @Override
    public Groups addGroup(Groups group) {
        return groupsRepository.save(group);
    }

    @Override
    public void deleteById(Long id) {
        if (peopleRepository.existsByGroupId_Id(id)) {
            throw new GroupNotFoundException("Some people study in this group");
        }
        groupsRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        List<Groups> groups = (List<Groups>) groupsRepository.findAll();
        for (Groups g: groups) {
            groupsRepository.deleteById(g.getId());
        }
    }
}
