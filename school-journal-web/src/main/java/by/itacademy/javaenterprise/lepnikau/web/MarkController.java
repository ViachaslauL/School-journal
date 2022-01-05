package by.itacademy.javaenterprise.lepnikau.web;

import by.itacademy.javaenterprise.lepnikau.entity.Mark;
import by.itacademy.javaenterprise.lepnikau.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mark")
public class MarkController {

    @Autowired
    private MarkService markService;

    @GetMapping("/find")
    public Mark findMark(@RequestBody String markId) {
        return markService.findMark(Long.parseLong(markId));
    }

    @GetMapping("/find/all")
    public List<Mark> findAllMarks() {
        return markService.getAllMarks();
    }

    @PostMapping("/save")
    public Mark saveMark(@RequestBody Mark mark) {
        return markService.saveMark(mark);
    }

    @PostMapping("/update")
    public boolean updateMark(@RequestBody Mark mark) {
        return markService.updateMark(mark);
    }

    @PostMapping("/delete")
    public boolean deleteMark(@RequestBody Mark mark) {
        return markService.deleteMark(mark);
    }
}
