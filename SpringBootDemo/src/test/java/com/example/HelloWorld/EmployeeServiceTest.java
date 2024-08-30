package com.example.HelloWorld;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ram.model.Employee;
import com.ram.repository.EmployeeRepository;
import com.ram.service.impl.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void testCreateEmployee() {
        
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("John Doe");
        employee.setEmail("john.doe@gmail.com");
        employee.setSalary(50000);

        when(employeeRepository.save(employee)).thenReturn(employee);

        
        Employee createdEmployee = employeeService.createEmployee(employee);

        // Assert
        assertEquals(1, createdEmployee.getId());
        assertEquals("John Doe", createdEmployee.getName());
        assertEquals("john.doe@gmail.com", createdEmployee.getEmail());
        assertEquals(50000, createdEmployee.getSalary());

        verify(employeeRepository, times(1)).save(employee);
    }
    
    @Test
    public void testUpdateEmployee() {
        
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("John Doe");
        employee.setEmail("jason@gmail.com");
        employee.setSalary(40000);

        when(employeeRepository.save(employee)).thenReturn(employee);

        
        employeeService.updateEmployee(employee);



        verify(employeeRepository, times(1)).save(employee);
    }
    
}
