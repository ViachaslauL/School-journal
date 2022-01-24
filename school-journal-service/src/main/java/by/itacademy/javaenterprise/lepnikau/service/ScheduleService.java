package by.itacademy.javaenterprise.lepnikau.service;

import by.itacademy.javaenterprise.lepnikau.dao.ScheduleDAO;
import by.itacademy.javaenterprise.lepnikau.dto.ScheduleDTO;
import by.itacademy.javaenterprise.lepnikau.entity.Schedule;
import by.itacademy.javaenterprise.lepnikau.modelmapper.ScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleDAO scheduleDAO;
    private final ScheduleMapper scheduleMapper;

    @Autowired
    public ScheduleService(ScheduleDAO scheduleDAO, ScheduleMapper scheduleMapper) {
        this.scheduleDAO = scheduleDAO;
        this.scheduleMapper = scheduleMapper;
    }

    public List<ScheduleDTO> getAllSchedule(int pNumber, int pSize) {
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();

        for (Schedule schedule : scheduleDAO.getAll(pNumber, pSize)) {
            scheduleDTOList.add(scheduleMapper.toDto(schedule));
        }

        return scheduleDTOList;
    }

    public ScheduleDTO findSchedule(Long id) {
        return scheduleMapper.toDto(scheduleDAO.get(id));
    }

    public ScheduleDTO saveSchedule(ScheduleDTO scheduleDTO) {
        Schedule savedSchedule = scheduleDAO.save(scheduleMapper.toEntity(scheduleDTO));
        return scheduleMapper.toDto(savedSchedule);
    }

    public boolean updateSchedule(ScheduleDTO scheduleDTO) {
        return scheduleDAO.update(scheduleMapper.toEntity(scheduleDTO));
    }

    public boolean deleteSchedule(ScheduleDTO scheduleDTO) {
        return scheduleDAO.delete(scheduleMapper.toEntity(scheduleDTO));
    }
}
