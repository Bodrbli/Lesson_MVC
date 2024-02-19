package org.bodrbli.lesson_mvc.controllers;

import lombok.RequiredArgsConstructor;
import org.bodrbli.lesson_mvc.model.Department;
import org.bodrbli.lesson_mvc.services.DepartmentService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RequestMapping("/admin/department")
public class DepartmentControllerVol1 {
    private final DepartmentService daoDepartment;
    @GetMapping
    public String departments(Model model) {
        model.addAttribute("departments", daoDepartment.findAll());
        return "departmentsVol1";
    }

    @PostMapping
    public String add(@RequestParam String name, String note, @RequestParam(required = false) Integer parentId) { //добавляем параметры полученные со страницы department.html
        Department department = Department.builder()
                .name(name)
                .note(note)
                .build();
        if (parentId != null) {
            department.setParentDpt(daoDepartment.findById(parentId));
        }
        daoDepartment.save(department);
        return "redirect:/admin/department";                                                                                    //(required = false) - означает параметр который будет не обязательно передан
    }
}
