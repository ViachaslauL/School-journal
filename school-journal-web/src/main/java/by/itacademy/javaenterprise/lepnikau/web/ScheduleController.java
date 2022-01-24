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

    @RequestMapping(method = RequestMethod.GET)
    public List<ScheduleDTO> getAllSchedule(
            @RequestParam("pNumber") int pNumber,
            @RequestParam("pSize") int pSize
    ) {
        return scheduleService.getAllSchedule(pNumber, pSize);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ScheduleDTO findSchedule(@PathVariable Long id) {
        return scheduleService.findSchedule(id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ScheduleDTO saveSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return scheduleService.saveSchedule(scheduleDTO);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public boolean updateSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return scheduleService.updateSchedule(scheduleDTO);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public boolean deleteSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return scheduleService.deleteSchedule(scheduleDTO);
    }
}
