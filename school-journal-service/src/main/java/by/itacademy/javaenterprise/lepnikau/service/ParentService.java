package by.itacademy.javaenterprise.lepnikau.service;

import by.itacademy.javaenterprise.lepnikau.dao.ParentDAO;
import by.itacademy.javaenterprise.lepnikau.dto.ParentDTO;
import by.itacademy.javaenterprise.lepnikau.entity.Parent;
import by.itacademy.javaenterprise.lepnikau.modelmapper.ParentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParentService {

    private final ParentDAO parentDAO;
    private final ParentMapper parentMapper;

    @Autowired
    public ParentService(ParentDAO parentDAO, ParentMapper parentMapper) {
        this.parentDAO = parentDAO;
        this.parentMapper = parentMapper;
    }

    public List<ParentDTO> findAllParents() {
        List<ParentDTO> parentDTOList = new ArrayList<>();

        for (Parent parent : parentDAO.getAll()) {
            parentDTOList.add(parentMapper.toDto(parent));
        }

        return parentDTOList;
    }

    public ParentDTO find(Long id) {
        return parentMapper.toDto(parentDAO.get(id));
    }

    public ParentDTO saveParent(ParentDTO parentDTO) {
        Parent savedParent = parentDAO.save(parentMapper.toEntity(parentDTO));
        return parentMapper.toDto(savedParent);
    }

    public boolean updateParent(ParentDTO parentDTO) {
        return parentDAO.update(parentMapper.toEntity(parentDTO));
    }

    public boolean deleteParent(ParentDTO parentDTO) {
        return parentDAO.delete(parentMapper.toEntity(parentDTO));
    }
}
