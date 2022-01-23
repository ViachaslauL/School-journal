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

    @GetMapping
    public List<MarkDTO> findAllMarks() {
        return markService.findAllMarks();
    }

    @GetMapping("/{id}")
    public MarkDTO findMark(@PathVariable String id) {
        return markService.findMark(Long.parseLong(id));
    }

    @PostMapping("/save")
    public MarkDTO saveMark(@RequestBody MarkDTO markDTO) {
        return markService.saveMark(markDTO);
    }

    @PostMapping("/update")
    public boolean updateMark(@RequestBody MarkDTO markDTO) {
        return markService.updateMark(markDTO);
    }

    @PostMapping("/delete")
    public boolean deleteMark(@RequestBody MarkDTO markDTO) {
        return markService.deleteMark(markDTO);
    }
}
