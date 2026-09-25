document.addEventListener("DOMContentLoaded", function() {
    loadAllKhoa();
});

// Load Khoa cho cả 2 Combobox
function loadAllKhoa() {
    fetch('/api/khoa').then(res => res.json()).then(data => {
        const khoaSelect = document.getElementById("khoaSelect");
        const formKhoaSelect = document.getElementById("formKhoaSelect");
        data.forEach(khoa => {
            khoaSelect.innerHTML += `<option value="${khoa.id}">${khoa.tenKhoa}</option>`;
            formKhoaSelect.innerHTML += `<option value="${khoa.id}">${khoa.tenKhoa}</option>`;
        });
    });
}

// Load danh sách Sinh Viên và tạo Nút Sửa/Xóa
function LoadStudentsByKhoa() {
    const khoaId = document.getElementById("khoaSelect").value;
    const tbody = document.getElementById("studentTableBody");
    tbody.innerHTML = "";
    if (!khoaId) return;

    fetch(`/api/students/by-khoa/${khoaId}`).then(res => res.json()).then(students => {
        students.forEach(sv => {
            tbody.innerHTML += `
                <tr>
                    <td>${sv.id}</td><td>${sv.maSv}</td><td>${sv.hoTen}</td><td>${sv.ngaySinh}</td>
                    <td>
                        <button onclick="editStudent(${sv.id}, '${sv.maSv}', '${sv.hoTen}', '${sv.ngaySinh}', ${khoaId})">Sửa</button>
                        <button onclick="deleteStudent(${sv.id})">Xóa</button>
                    </td>
                </tr>
            `;
        });
    });
}

// Hàm Xóa
function deleteStudent(id) {
    if (confirm("Xóa nhé?")) {
        fetch(`/api/students/${id}`, { method: 'DELETE' }).then(() => LoadStudentsByKhoa());
    }
}

// Hàm ném dữ liệu lên Form để Sửa (cực nhanh, không cần gọi API)
function editStudent(id, maSv, hoTen, ngaySinh, khoaId) {
    document.getElementById("studentId").value = id;
    document.getElementById("maSv").value = maSv;
    document.getElementById("hoTen").value = hoTen;
    document.getElementById("ngaySinh").value = ngaySinh;
    document.getElementById("formKhoaSelect").value = khoaId;
}

// Hàm Lưu (Thêm/Sửa chung 1 chỗ)
function saveStudent() {
    const id = document.getElementById("studentId").value;
    const data = {
        maSv: document.getElementById("maSv").value,
        hoTen: document.getElementById("hoTen").value,
        ngaySinh: document.getElementById("ngaySinh").value,
        khoa: { id: document.getElementById("formKhoaSelect").value }
    };

    fetch(id ? `/api/students/${id}` : '/api/students', {
        method: id ? 'PUT' : 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    }).then(() => {
        clearForm();
        LoadStudentsByKhoa(); // Load lại bảng
    });
}

function clearForm() {
    document.getElementById("studentId").value = "";
    document.getElementById("maSv").value = "";
    document.getElementById("hoTen").value = "";
    document.getElementById("ngaySinh").value = "";
}