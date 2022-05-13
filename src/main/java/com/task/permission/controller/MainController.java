package com.task.permission.controller;

import com.task.permission.model.Manager;
import com.task.permission.model.Permission;
import com.task.permission.model.Person;
import com.task.permission.model.Role;
import com.task.permission.service.PermissionService;
import com.task.permission.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {
    private final PersonService personService;
    private final PermissionService permissionService;


    public MainController(PersonService personService, PermissionService permissionService) {
        this.personService = personService;
        this.permissionService = permissionService;
    }

    @GetMapping("/")
    public String login(){
        return "index";
    }

    @GetMapping("/main")
    public String mainPage(Model model,Principal principal){
        Person temp= personService.findByUsername(principal.getName());
        if(temp.getRole().equals(Role.ROLE_MANAGER)){
            List<Permission> permissions= personService.findByManager((Manager)temp).stream().map(e->e.getPermissions()).findAny().get();
            model.addAttribute("permissionList",permissions);
        }
        model.addAttribute("person",personService.findByUsername(principal.getName()));
        return "main";
    }

    @GetMapping("/permission")
    public String permission(Model model,Principal principal){
        model.addAttribute("permission",new Permission());
        model.addAttribute("person",personService.findByUsername(principal.getName()));
        return "permission";
    }

    @PostMapping("/permission")
    public String savePermission(@ModelAttribute("permission") Permission permission ,Model model,Principal principal){
        Person temp=personService.findByUsername(principal.getName());
        permission.setPerson(temp);
        permission.setPermissionStatus("BEKLEMEDE");
        permissionService.savePermisson(permission);
        model.addAttribute("person",temp);
        return "redirect:main";
    }
}
