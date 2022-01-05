package by.itacademy.javaenterprise.lepnikau.service;

import by.itacademy.javaenterprise.lepnikau.dao.MarkDAO;
import by.itacademy.javaenterprise.lepnikau.entity.Mark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkService {

    @Autowired
    private MarkDAO markDAO;

    public Mark findMark(Long id) {
        return markDAO.get(id);
    }

    public List<Mark> getAllMarks() {
        return markDAO.getAll();
    }

    public Mark saveMark(Mark mark) {
        return markDAO.save(mark);
    }

    public boolean updateMark(Mark mark) {
        return markDAO.update(mark);
    }

    public boolean deleteMark(Mark mark) {
        return markDAO.delete(mark);
    }
}
