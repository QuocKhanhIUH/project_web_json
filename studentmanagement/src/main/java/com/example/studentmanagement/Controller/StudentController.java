package com.example.studentmanagement.Controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.service.StudentService;
@CrossOrigin
@RestController
@RequestMapping("/api/students")

public class StudentController {
	@Autowired
    private StudentService studentService;

    // API lấy danh sách sinh viên theo Khoa ID
    @GetMapping("/by-khoa/{khoaId}")
    public ResponseEntity<List<Student>> getStudentsByKhoa(@PathVariable Long khoaId) {
        List<Student> students = studentService.getStudentsByKhoa(khoaId);
        return ResponseEntity.ok(students);
    }
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    // 2. SỬA
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student data) {
        Student sv = studentService.getStudentById(id);
        sv.setMaSv(data.getMaSv());
        sv.setHoTen(data.getHoTen());
        sv.setNgaySinh(data.getNgaySinh());
        sv.setKhoa(data.getKhoa()); 
        return studentService.saveStudent(sv);
    }

    // 3. XÓA
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

}
