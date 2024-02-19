package org.bodrbli.lesson_mvc.controllers;

import lombok.RequiredArgsConstructor;
import org.bodrbli.lesson_mvc.model.Department;
import org.bodrbli.lesson_mvc.services.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/department")
public class DepartmentController {
    private final DepartmentService deptService;
    @GetMapping
    public String departments(Model model) {
        Department dpt = new Department();
        System.out.println(dpt);
        model.addAttribute("department", dpt);
        model.addAttribute("departments", deptService.findAll());
        return "departments";
    }

    @GetMapping("/{id}")
    public String getEditPage(Model model, @PathVariable String id) {
        model.addAttribute("department", deptService.findById(Integer.parseInt(id)));
        model.addAttribute("departments", deptService.findAll());
        return "departments";
    }

    @PostMapping
    public String add(Department department, @RequestParam(required = false) Integer parentId) { //добавляем параметры полученные со страницы department.html
        if (parentId != null) {
            department.setParentDpt(deptService.findById(parentId));
        }
        deptService.save(department);
        return "redirect:/admin/department";                                                                                    //(required = false) - означает параметр который будет не обязательно передан
    }

    @PostMapping("/{id}")
    public String update(Department department, @RequestParam(required = false) Integer parentId) {
        Department deptToUpdate = deptService.findById(department.getId());
        department.setParentDpt(deptService.findById(parentId));
        update(deptToUpdate, department);
        deptService.save(deptToUpdate);
        return "redirect:/admin/department";
    }

    private void update(Department dDb, Department dUpdate) {
        dDb.setName(dUpdate.getName());
        dDb.setNote(dUpdate.getNote());
        dDb.setParentDpt(dUpdate.getParentDpt());
    }
}
