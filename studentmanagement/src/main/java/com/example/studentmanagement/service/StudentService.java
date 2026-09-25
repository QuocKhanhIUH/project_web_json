package com.example.studentmanagement.service;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;

    public List<Student> getStudentsByKhoa(Long khoaId) {
        return studentRepository.findByKhoaId(khoaId);
}
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    // 2. Xóa sinh viên
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    // 3. Tìm 1 sinh viên theo ID
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }
}
