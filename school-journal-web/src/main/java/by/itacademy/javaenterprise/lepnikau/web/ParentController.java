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

    @RequestMapping(method = RequestMethod.GET)
    public List<ParentDTO> finaAllParents(
            @RequestParam("pNumber") int pNumber,
            @RequestParam("pSize") int pSize
    ) {
        return parentService.findAllParents(pNumber, pSize);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ParentDTO findParent(@PathVariable Long id) {
        return parentService.find(id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ParentDTO saveParent(@RequestBody ParentDTO parentDTO) {
        return parentService.saveParent(parentDTO);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public boolean updateParent(@RequestBody ParentDTO parentDTO) {
        return parentService.updateParent(parentDTO);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public boolean deleteParent(@RequestBody ParentDTO parentDTO) {
        return parentService.deleteParent(parentDTO);
    }
}
