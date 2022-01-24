package by.itacademy.javaenterprise.lepnikau.web;

import by.itacademy.javaenterprise.lepnikau.dto.MarkDTO;
import by.itacademy.javaenterprise.lepnikau.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<MarkDTO> findAllMarks(
            @RequestParam("pNumber") int pNumber,
            @RequestParam("pSize") int pSize
    ) {
        return markService.findAllMarks(pNumber, pSize);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public MarkDTO findMark(@PathVariable Long id) {
        return markService.findMark(id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public MarkDTO saveMark(@RequestBody MarkDTO markDTO) {
        return markService.saveMark(markDTO);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public boolean updateMark(@RequestBody MarkDTO markDTO) {
        return markService.updateMark(markDTO);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public boolean deleteMark(@RequestBody MarkDTO markDTO) {
        return markService.deleteMark(markDTO);
    }
}
