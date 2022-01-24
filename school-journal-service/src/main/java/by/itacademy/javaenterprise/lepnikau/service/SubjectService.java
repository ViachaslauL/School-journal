package by.itacademy.javaenterprise.lepnikau.service;

import by.itacademy.javaenterprise.lepnikau.dao.SubjectDAO;
import by.itacademy.javaenterprise.lepnikau.dto.SubjectDTO;
import by.itacademy.javaenterprise.lepnikau.entity.Subject;
import by.itacademy.javaenterprise.lepnikau.modelmapper.SubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService {

    private final SubjectDAO subjectDAO;
    private final SubjectMapper subjectMapper;

    @Autowired
    public SubjectService(SubjectDAO subjectDAO, SubjectMapper subjectMapper) {
        this.subjectDAO = subjectDAO;
        this.subjectMapper = subjectMapper;
    }

    public List<SubjectDTO> findAllSubjects(int pNumber, int pSize) {
        List<SubjectDTO> subjectDTOList = new ArrayList<>();

        for (Subject subject : subjectDAO.getAll(pNumber, pSize)) {
            subjectDTOList.add(subjectMapper.toDto(subject));
        }

        return subjectDTOList;
    }

    public SubjectDTO findSubject(Long id) {
        return subjectMapper.toDto(subjectDAO.get(id));
    }

    public SubjectDTO saveSubject(SubjectDTO subjectDTO) {
        Subject savedSubject = subjectDAO.save(subjectMapper.toEntity(subjectDTO));
        return subjectMapper.toDto(savedSubject);
    }

    public boolean updateSubject(SubjectDTO subjectDTO) {
        return subjectDAO.update(subjectMapper.toEntity(subjectDTO));
    }

    public boolean deleteSubject(SubjectDTO subjectDTO) {
        return subjectDAO.delete(subjectMapper.toEntity(subjectDTO));
    }
}
