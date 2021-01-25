package pl.edu.pjwstk.jaz.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.jaz.Request.Category;
import pl.edu.pjwstk.jaz.Request.Section;
import pl.edu.pjwstk.jaz.Service.SectionRepository;

@RestController
public class SectionController {

    private final SectionRepository sectionRepository;

    public SectionController(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }


    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/addSection")
    public void addSection(@RequestBody Section section){
        sectionRepository.createSection(section.getName());
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/updateSection/{sectionName}")
    public void updateSection(@RequestBody Section section, @PathVariable String sectionName){
        sectionRepository.updateSection(sectionName,section.getName());
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/addCategory")
    public void createCategory(@RequestBody Category category){
        sectionRepository.createCategory(category);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/updateCategory/{categoryName}")
    public void updateCategory(@RequestBody Category category, @PathVariable String categoryName){
        sectionRepository.updateCategory(category, categoryName);
    }

}
