package com.task.permission.controller;

import com.task.permission.model.Permission;
import com.task.permission.service.PermissionService;
import com.task.permission.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class PermissionController {
    private final PermissionService permissionService;
    private final PersonService personService;

    public PermissionController(PermissionService permissionService, PersonService personService) {
        this.permissionService = permissionService;
        this.personService = personService;
    }

    @GetMapping("/permission/submit/{permissionId}")
    public String submitPermission(@PathVariable("permissionId")Integer permissionId, Model model, Principal principal){
        Permission temp=permissionService.findPermissionById(permissionId);
        temp.setPermissionStatus("ONAYLANDI");
        permissionService.savePermisson(temp);
        model.addAttribute("person",personService.findByUsername(principal.getName()));
        return "redirect:/main?success";
    }

    @GetMapping("/permission/deny/{permissionId}")
    public String denyPermission(@PathVariable("permissionId")Integer permissionId, Model model, Principal principal){
        Permission temp=permissionService.findPermissionById(permissionId);
        temp.setPermissionStatus("REDDEDİLDİ");
        permissionService.savePermisson(temp);
        model.addAttribute("person",personService.findByUsername(principal.getName()));
        return "redirect:/main?success";
    }

}
