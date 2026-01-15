package com.atithiai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.atithiai.entities.MenuItem;
import com.atithiai.services.MenuItemService;


@Controller
@RequestMapping("/menu")
public class MenuViewController {
	
	private final MenuItemService menuItemService;

    public MenuViewController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GetMapping("/add")
    public String showAddMenuForm(Model model) {
        model.addAttribute("menuItem", new MenuItem());
        return "menu/addMenu";
    }

    @PostMapping("/save")
    public String saveMenuItem(@ModelAttribute MenuItem menuItem) {
        menuItemService.addMenuItem(menuItem);
        return "redirect:/menu/list";
    }

    @GetMapping("/list")
    public String listMenuItems(Model model) {
        model.addAttribute("menuItems", menuItemService.getAllMenuItems());
        return "menu/menuList";
    }

}
