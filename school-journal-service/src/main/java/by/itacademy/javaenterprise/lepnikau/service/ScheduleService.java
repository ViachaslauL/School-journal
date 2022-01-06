package by.itacademy.javaenterprise.lepnikau.service;

import by.itacademy.javaenterprise.lepnikau.dao.ScheduleDAO;
import by.itacademy.javaenterprise.lepnikau.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleDAO scheduleDAO;

    public Schedule findSchedule(Long id) {
        return scheduleDAO.get(id);
    }

    public List<Schedule> getAllSchedule() {
        return scheduleDAO.getAll();
    }

    public Schedule saveSchedule(Schedule schedule) {
        return scheduleDAO.save(schedule);
    }

    public boolean updateSchedule(Schedule schedule) {
        return scheduleDAO.update(schedule);
    }

    public boolean deleteSchedule(Schedule schedule) {
        return scheduleDAO.delete(schedule);
    }
}
