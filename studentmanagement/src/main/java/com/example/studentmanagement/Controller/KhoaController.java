package com.example.studentmanagement.Controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentmanagement.entity.Khoa;
import com.example.studentmanagement.repository.KhoaRepository;
@CrossOrigin
@RestController
@RequestMapping("/api/khoa")
public class KhoaController {
	@Autowired
    private KhoaRepository khoaRepository;

    @GetMapping
    public List<Khoa> getAllKhoa() {
        // Dùng thẳng Repository để lấy danh sách cho nhanh
        return khoaRepository.findAll();
    }

}
