package com.example.employee.crud.service;

import com.example.employee.crud.entity.Employee;
import java.util.List;

public interface EmployeeService {

    // Mendapatkan semua employee
    List<Employee> findAll();

    // Mencari employee berdasarkan ID
    Employee findById(int id);

    // Menyimpan atau memperbarui employee
    Employee save(Employee employee);

    // Menghapus employee berdasarkan ID
    void deleteById(int id);
}