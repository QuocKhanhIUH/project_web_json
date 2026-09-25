package com.example.app.config;

import com.example.app.entity.Product;
import com.example.app.entity.User;
import com.example.app.repository.ProductRepository;
import com.example.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("123456"));
            admin.setRole("ADMIN");
            admin.setEnabled(true);
            userRepository.save(admin);

            User user1 = new User();
            user1.setUsername("user1");
            user1.setPassword(passwordEncoder.encode("123456"));
            user1.setRole("USER");
            user1.setEnabled(true);
            userRepository.save(user1);

            System.out.println(">> Da tao tai khoan mau: admin/123456 (ADMIN), user1/123456 (USER)");
        }

        if (productRepository.count() == 0) {
            productRepository.save(new Product(null, "Iphone", "Điện thoại",
                    new BigDecimal("28990000"), 15, "Các dòng sản phẩm của Apple"));
            productRepository.save(new Product(null, "Tai nghe JBL Pro2", "Âm thanh",
                    new BigDecimal("1990000"), 40, "Tai nghe cách âm cao"));
            productRepository.save(new Product(null, "Bàn phím", "Phụ Kiện",
                    new BigDecimal("2190000"), 25, "Ban phim co, ket noi Bluetooth"));
            productRepository.save(new Product(null, "Màn hình LG UltraWide 29\"", "Màn hình",
                    new BigDecimal("6490000"), 10, "Màn hình công 29 inch, 75Hz"));
            productRepository.save(new Product(null, "Tai nghe Sony WH-1000XM5", "Âm thanh",
                    new BigDecimal("7990000"), 20, "Tai nghe chong on chu dong"));

            System.out.println(">> Da tao 5 san pham mau");
        }
    }
}
