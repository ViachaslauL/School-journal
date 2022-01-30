package by.itacademy.javaenterprise.lepnikau.web;

import by.itacademy.javaenterprise.lepnikau.dto.MarkDTO;
import by.itacademy.javaenterprise.lepnikau.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marks")
public class MarkController {

    private final MarkService markService;

    @Autowired
    public MarkController(MarkService markService) {
        this.markService = markService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<MarkDTO> findAllMarks(
            @RequestParam("pNumber") int pNumber,
            @RequestParam("pSize") int pSize
    ) {
        return markService.findAllMarks(pNumber, pSize);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public MarkDTO findMark(@PathVariable Long id) {
        return markService.findMark(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public MarkDTO saveMark(@RequestBody MarkDTO markDTO) {
        return markService.saveMark(markDTO);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public boolean updateMark(@RequestBody MarkDTO markDTO) {
        return markService.updateMark(markDTO);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN')")
    public boolean deleteMark(@RequestBody MarkDTO markDTO) {
        return markService.deleteMark(markDTO);
    }
}
