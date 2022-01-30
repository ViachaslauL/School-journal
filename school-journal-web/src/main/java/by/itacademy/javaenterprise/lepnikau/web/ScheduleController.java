package by.itacademy.javaenterprise.lepnikau.web;

import by.itacademy.javaenterprise.lepnikau.dto.ScheduleDTO;
import by.itacademy.javaenterprise.lepnikau.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<ScheduleDTO> getAllSchedule(
            @RequestParam("pNumber") int pNumber,
            @RequestParam("pSize") int pSize
    ) {
        return scheduleService.getAllSchedule(pNumber, pSize);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ScheduleDTO findSchedule(@PathVariable Long id) {
        return scheduleService.findSchedule(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ScheduleDTO saveSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return scheduleService.saveSchedule(scheduleDTO);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public boolean updateSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return scheduleService.updateSchedule(scheduleDTO);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN')")
    public boolean deleteSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return scheduleService.deleteSchedule(scheduleDTO);
    }
}
