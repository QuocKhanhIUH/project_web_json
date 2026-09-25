package com.example.app.controller;

import com.example.app.service.ProductService;
import com.example.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final ProductService productService;
    private final UserService userService;

    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model model) {
        model.addAttribute("totalProducts", productService.count());
        model.addAttribute("totalUsers", userService.countUsers());
        model.addAttribute("recentProducts", productService.findTop5Recent());
        return "dashboard";
    }
}
