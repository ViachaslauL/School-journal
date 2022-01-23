package by.itacademy.javaenterprise.lepnikau.web;

import by.itacademy.javaenterprise.lepnikau.dto.ParentDTO;
import by.itacademy.javaenterprise.lepnikau.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parents")
public class ParentController {

    private final ParentService parentService;

    @Autowired
    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @GetMapping
    public List<ParentDTO> finaAllParents() {
        return parentService.findAllParents();
    }

    @GetMapping("/{id}")
    public ParentDTO findParent(@PathVariable String id) {
        return parentService.find(Long.parseLong(id));
    }

    @PostMapping("/save")
    public ParentDTO saveParent(@RequestBody ParentDTO parentDTO) {
        return parentService.saveParent(parentDTO);
    }

    @PostMapping("/update")
    public boolean updateParent(@RequestBody ParentDTO parentDTO) {
        return parentService.updateParent(parentDTO);
    }

    @PostMapping("/delete")
    public boolean deleteParent(@RequestBody ParentDTO parentDTO) {
        return parentService.deleteParent(parentDTO);
    }
}
