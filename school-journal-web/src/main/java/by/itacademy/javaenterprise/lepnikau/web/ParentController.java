package by.itacademy.javaenterprise.lepnikau.web;

import by.itacademy.javaenterprise.lepnikau.entity.Parent;
import by.itacademy.javaenterprise.lepnikau.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parent")
public class ParentController {

    @Autowired
    private ParentService parentService;

    @GetMapping("/find")
    public Parent findParent(@RequestBody String id) {
        return parentService.find(Long.parseLong(id));
    }

    @GetMapping("/find/all")
    public List<Parent> finaAllParents() {
        return parentService.findAllParents();
    }

    @PostMapping("/save")
    public Parent saveParent(@RequestBody Parent parent) {
        return parentService.saveParent(parent);
    }

    @PostMapping("/update")
    public boolean updateParent(@RequestBody Parent parent) {
        return parentService.updateParent(parent);
    }

    @PostMapping("/delete")
    public boolean deleteParent(@RequestBody Parent parent) {
        return parentService.deleteParent(parent);
    }
}
