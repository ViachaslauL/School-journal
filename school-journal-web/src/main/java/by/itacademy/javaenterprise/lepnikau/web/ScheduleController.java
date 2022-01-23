package by.itacademy.javaenterprise.lepnikau.web;

import by.itacademy.javaenterprise.lepnikau.dto.ScheduleDTO;
import by.itacademy.javaenterprise.lepnikau.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedule() {
        return scheduleService.getAllSchedule();
    }

    @GetMapping("/{id}")
    public ScheduleDTO findSchedule(@PathVariable("id") String id) {
        return scheduleService.findSchedule(Long.parseLong(id));
    }

    @PostMapping("/save")
    public ScheduleDTO saveSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return scheduleService.saveSchedule(scheduleDTO);
    }

    @PostMapping("/update")
    public boolean updateSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return scheduleService.updateSchedule(scheduleDTO);
    }

    @PostMapping("/delete")
    public boolean deleteSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return scheduleService.deleteSchedule(scheduleDTO);
    }
}
