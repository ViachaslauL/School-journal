package by.itacademy.javaenterprise.lepnikau.service;

import by.itacademy.javaenterprise.lepnikau.dao.MarkDAO;
import by.itacademy.javaenterprise.lepnikau.dto.MarkDTO;
import by.itacademy.javaenterprise.lepnikau.entity.Mark;
import by.itacademy.javaenterprise.lepnikau.modelmapper.MarkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MarkService {

    private final MarkDAO markDAO;
    private final MarkMapper markMapper;

    @Autowired
    public MarkService(MarkDAO markDAO, MarkMapper markMapper) {
        this.markDAO = markDAO;
        this.markMapper = markMapper;
    }

    public List<MarkDTO> findAllMarks(int pNumber, int pSize) {
        List<MarkDTO> markDTOList = new ArrayList<>();

        for (Mark mark : markDAO.getAll(pNumber, pSize)) {
            markDTOList.add(markMapper.toDto(mark));
        }

        return markDTOList;
    }

    public MarkDTO findMark(Long id) {
        return markMapper.toDto(markDAO.get(id));
    }

    public MarkDTO saveMark(MarkDTO markDTO) {
        Mark savedMark = markDAO.save(markMapper.toEntity(markDTO));
        return markMapper.toDto(savedMark);
    }

    public boolean updateMark(MarkDTO markDTO) {
        return markDAO.update(markMapper.toEntity(markDTO));
    }

    public boolean deleteMark(MarkDTO markDTO) {
        return markDAO.delete(markMapper.toEntity(markDTO));
    }
}
