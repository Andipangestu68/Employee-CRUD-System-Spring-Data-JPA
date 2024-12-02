package com.example.employee.crud.service;

import com.example.employee.crud.dao.EmployeeRepository;
import com.example.employee.crud.entity.Employee;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    // Constructor Dependency Injection untuk EmployeeDAO
    public EmployeeServiceImpl(EmployeeRepository employeeDAO) {
        this.employeeRepository = employeeDAO;
    }

    /**
     * Mengambil semua employee dari database.
     */
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    /**
     * Mencari employee berdasarkan ID.
     * Jika tidak ditemukan, kembalikan null.
     */
    @Override
    public Employee findById(int id) {
        Optional<Employee> result = employeeRepository.findById(id);
        return result.orElse(null);
    }

    /**
     * Menyimpan atau memperbarui employee.
     * Jika ID adalah 0, berarti employee baru; jika tidak, update employee.
     */
    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    /**
     * Menghapus employee berdasarkan ID.
     */
    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}
