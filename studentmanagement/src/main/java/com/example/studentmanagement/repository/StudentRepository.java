package com.example.studentmanagement.repository;
import com.example.studentmanagement.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
	Page<Student> findByKhoaId(Long khoaId, Pageable pageable);
	// Spring Data JPA sẽ tự động generate query dựa trên tên hàm
    List<Student> findByKhoaId(Long khoaId);

}
