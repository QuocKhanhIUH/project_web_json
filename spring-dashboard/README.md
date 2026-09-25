# Spring Dashboard — Spring Boot + Thymeleaf + Bootstrap + REST API

Project mau on thi mon Web huong du lieu: dang ky/dang nhap/dang xuat, dashboard,
tim kiem san pham, CRUD san pham (co ca giao dien Thymeleaf lan REST API JSON).

## 1. Cong nghe su dung
- Spring Boot 3.3.4, Java 17, Maven
- Spring Web (MVC + REST)
- Spring Data JPA (Hibernate)
- Spring Security 6 (form login, BCrypt, @PreAuthorize)
- Thymeleaf + thymeleaf-extras-springsecurity6 (the sec:authorize trong HTML)
- Bootstrap 5 (qua CDN, khong can cai dat gi them)
- MySQL (mac dinh) — co san H2 de doi sang neu khong muon cai MySQL
- Lombok

## 2. Cach chay

### Buoc 1: Sua cau hinh database
Mo file `src/main/resources/application.properties`, sua username/password MySQL
cho khop voi may ban. Mac dinh dung `root/root`.

Neu KHONG muon cai MySQL: comment 4 dong cau hinh MySQL lai, mo comment 4 dong H2
ngay ben duoi (da chua san trong file). App se chay bang database trong RAM,
mat du lieu khi tat app — phu hop de test nhanh.

### Buoc 2: Chay ung dung
- Import project vao IntelliJ/Eclipse/STS bang "Import as Maven Project" (tro toi `pom.xml`)
- Chay class `AppApplication.java` (co ham `main`), hoac chay lenh:
```bash
mvn spring-boot:run
```
- App chay o: http://localhost:8080

**Khong can chay SQL tay** — `DataSeeder.java` tu dong tao bang va du lieu mau khi
khoi dong lan dau (dung `spring.jpa.hibernate.ddl-auto=update`).

### Tai khoan mau (tu dong tao):
| Username | Password | Role  |
|----------|----------|-------|
| admin    | 123456   | ADMIN |
| user1    | 123456   | USER  |

## 3. Cac chuc nang & URL chinh

| Chuc nang            | URL                          | Ghi chu |
|-----------------------|-------------------------------|---------|
| Dang ky               | GET/POST `/signup`            | Ai cung dung duoc |
| Dang nhap             | GET/POST `/signin`            | Spring Security xu ly |
| Dang xuat             | POST `/logout`                | Nut trong navbar |
| Dashboard             | GET `/dashboard`               | Sau khi dang nhap |
| Danh sach + tim san pham | GET `/products?keyword=...` | Ai da dang nhap deu xem duoc |
| Them san pham (giao dien) | GET/POST `/products/add`, `/products/save` | Chi ADMIN |
| Sua san pham (giao dien)  | GET `/products/edit/{id}`      | Chi ADMIN |
| Xoa san pham (giao dien)  | GET `/products/delete/{id}`    | Chi ADMIN |
| REST API san pham     | `/api/products` (GET/POST/PUT/DELETE) | Xem: ai cung duoc. Them/sua/xoa: chi ADMIN |

## 4. Kien thuc trong tam de on thi

### a) Kien truc MVC vs REST
- `ProductViewController`: tra ve ten view (String) → Spring tra ve trang HTML (Thymeleaf render)
- `ProductRestController`: dung `@RestController` + `@RequestBody`/`ResponseEntity` → tra ve JSON,
  khong qua view. Day la "virtual REST API" test bang Postman.

### b) Spring Security
- `SecurityConfig` khai bao `SecurityFilterChain`: URL nao permitAll, URL nao can dang nhap,
  URL nao can ROLE_ADMIN.
- `CustomUserDetailsService` implements `UserDetailsService`: Spring Security goi ham
  `loadUserByUsername()` de lay User tu database khi dang nhap.
- `User` implements `UserDetails`: Spring Security can 1 doi tuong co du thong tin
  (username, password da hash, quyen han, tai khoan con hoat dong khong...).
- Mat khau KHONG BAO GIO luu plain text — dung `BCryptPasswordEncoder.encode()` khi luu,
  Spring Security tu so sanh hash khi dang nhap.
- `@PreAuthorize("hasRole('ADMIN')")`: chan o tang method (controller), can `@EnableMethodSecurity`.

### c) Spring Data JPA
- Chi can khai bao interface `extends JpaRepository<Entity, ID>` la co san
  `findAll()`, `save()`, `deleteById()`... khong can viet SQL.
- Dat ten method dung quy uoc (vd `findByNameContainingIgnoreCase`) → Spring tu sinh cau
  query tuong ung (khong can @Query, khong can viet SQL tay).

### d) Thymeleaf
- `th:text`, `th:each`, `th:if`, `th:field` (binding form voi object), `th:action`, `th:href`
- `th:replace="~{fragments/navbar :: navbar}"`: tai su dung 1 doan HTML (fragment) o nhieu trang
  khac nhau — tranh lap code navbar/sidebar.
- `sec:authorize="hasRole('ADMIN')"`: an/hien phan tu HTML dua theo quyen dang nhap.

### e) DTO va Validation
- `SignupForm` la DTO (Data Transfer Object) rieng cho form dang ky, khong dung truc tiep
  Entity `User` de tranh nguoi dung tu gan cac truong nhay cam (vd truc tiep set role=ADMIN).
- `@NotBlank`, `@Size` + `@Valid` trong controller: Spring tu validate du lieu nguoi dung nhap,
  loi duoc gom vao `BindingResult`.

## 5. Cau truc thu muc
```
spring-dashboard/
├── pom.xml
├── README.md
└── src/main/
    ├── java/com/example/app/
    │   ├── AppApplication.java
    │   ├── config/        (SecurityConfig, DataSeeder)
    │   ├── entity/         (User, Product)
    │   ├── repository/     (UserRepository, ProductRepository)
    │   ├── dto/             (SignupForm)
    │   ├── service/         (interface + impl/)
    │   ├── security/        (CustomUserDetailsService)
    │   └── controller/      (AuthController, DashboardController,
    │                          ProductViewController, ProductRestController)
    └── resources/
        ├── application.properties
        ├── templates/        (dashboard.html, auth/, products/, fragments/)
        └── static/css/style.css
```
