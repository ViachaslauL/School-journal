package by.itacademy.javaenterprise.lepnikau.web;

import by.itacademy.javaenterprise.lepnikau.entity.Schedule;
import by.itacademy.javaenterprise.lepnikau.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/find")
    public Schedule findSchedule(@RequestBody String id) {
        return scheduleService.findSchedule(Long.parseLong(id));
    }

    @GetMapping("/find/all")
    public List<Schedule> getAllSchedule() {
        return scheduleService.getAllSchedule();
    }

    @PostMapping("/save")
    public Schedule saveSchedule(@RequestBody Schedule schedule) {
        return scheduleService.saveSchedule(schedule);
    }

    @PostMapping("/update")
    public boolean updateSchedule(@RequestBody Schedule schedule) {
        return scheduleService.updateSchedule(schedule);
    }

    @PostMapping("/delete")
    public boolean deleteSchedule(@RequestBody Schedule schedule) {
        return scheduleService.deleteSchedule(schedule);
    }
}
