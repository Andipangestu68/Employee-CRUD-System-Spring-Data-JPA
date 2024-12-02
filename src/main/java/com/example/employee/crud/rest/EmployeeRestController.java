package com.example.employee.crud.rest;

import com.example.employee.crud.entity.Employee;
import com.example.employee.crud.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    // Constructor Dependency Injection untuk menginisialisasi EmployeeService
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * GET: Mendapatkan semua employee
     * URL: /api/employees
     */
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.findAll(); // Mengambil semua data employee
    }

    /**
     * GET: Mendapatkan employee berdasarkan ID
     * URL: /api/employees/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        Employee employee = employeeService.findById(id); // Cari employee berdasarkan ID
        if (employee != null) {
            return ResponseEntity.ok(employee); // Jika ditemukan, kembalikan data
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Jika tidak ditemukan, kembalikan 404
        }
    }

    /**
     * POST: Menambahkan employee baru
     * URL: /api/employees
     */
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        employee.setId(0); // Set ID menjadi 0 agar selalu disimpan sebagai data baru
        Employee savedEmployee = employeeService.save(employee); // Simpan employee
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee); // Kembalikan 201 Created
    }

    /**
     * PUT: Memperbarui employee berdasarkan ID
     * URL: /api/employees/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employeeDetails) {
        Employee existingEmployee = employeeService.findById(id); // Cari employee berdasarkan ID
        if (existingEmployee != null) {
            // Update data employee
            existingEmployee.setFirstname(employeeDetails.getFirstname());
            existingEmployee.setLastname(employeeDetails.getLastname());
            existingEmployee.setEmail(employeeDetails.getEmail());

            employeeService.save(existingEmployee); // Simpan perubahan
            return ResponseEntity.ok(existingEmployee); // Kembalikan data yang diupdate
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Jika tidak ditemukan, kembalikan 404
        }
    }

    //DELETE: Menghapus data atau employee berdasarkan ID

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        Employee employee = employeeService.findById(id); // mencari employee berdasarkan ID
        if (employee != null) {
            employeeService.deleteById(id); // Hapus employee
            return ResponseEntity.ok("berhasil menghapus data dengan ID " + id ); // informasi ketika data berdasarkan ID Berhasil dihapus
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data dengan ID " + id + " tidak berhasil ditemukan."); // Informasi yang akan muncul bila ID Tidak ditemukan
        }
    }
}