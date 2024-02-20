package org.bodrbli.lesson_mvc.controllers;

import lombok.RequiredArgsConstructor;
import org.bodrbli.lesson_mvc.model.Department;
import org.bodrbli.lesson_mvc.model.Profession;
import org.bodrbli.lesson_mvc.services.ProfessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/profession")
public class ProfessionController {
    private final ProfessionService profService;

    @GetMapping
    public String professions(Model model) {
        Profession prof = new Profession();
        model.addAttribute("profession", prof);
        model.addAttribute("professions", profService.findAll());
        return "professions";
    }

    @GetMapping("/{id}")
    public String getEditPage(Model model, @PathVariable String id) {
        model.addAttribute("profession", profService.findById(Integer.parseInt(id)));
        model.addAttribute("professions", profService.findAll());
        return "professions";
    }

    @PostMapping
    public String add(Profession profession) {
        profService.save(profession);
        return "redirect:/admin/profession";                                                                                    //(required = false) - означает параметр который будет не обязательно передан
    }

    @PostMapping("/{id}")
    public String update(Profession profession) {
        Profession profToUpdate = profService.findById(profession.getId());
        update(profToUpdate, profession);
        profService.save(profession);
        return "redirect:/admin/profession";
    }

    private void update(Profession pDb, Profession pUpdate) {
        pDb.setName(pUpdate.getName());
        pDb.setNote(pUpdate.getNote());
    }
    @GetMapping("/delete/{id}")
    public String getEditPageAfterDelete(Model model, @PathVariable String id) {
        Profession profToDelete = profService.findById(Integer.parseInt(id));
        profService.delete(profToDelete.getId());
        Profession prof = new Profession();
        model.addAttribute("profession", prof);
        model.addAttribute("professions", profService.findAll());
        return "professions";
    }
}
