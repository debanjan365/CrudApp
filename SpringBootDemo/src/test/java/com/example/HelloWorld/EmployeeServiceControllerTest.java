package com.example.HelloWorld;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ram.Application;
import com.ram.controller.EmployeeServiceController;
import com.ram.model.Employee;
import com.ram.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ContextConfiguration(classes = Application.class)
@WebMvcTest(EmployeeServiceController.class)

public class EmployeeServiceControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateEmployee() throws Exception {
        // Mock Employee object to be returned by the service
    	Employee mockEmployee = new Employee();
        mockEmployee.setId(1);
        mockEmployee.setName("John Doe");
        mockEmployee.setEmail("johndoe@example.com");
        mockEmployee.setSalary(5000);

        // Mock the service to return the employee when createEmployee is called
        Mockito.when(employeeService.createEmployee(any(Employee.class))).thenReturn(mockEmployee);

        // Create a valid employee request
        Employee employeeRequest = new Employee();
        employeeRequest.setName("John Doe");
        employeeRequest.setEmail("johndoe@example.com");
        employeeRequest.setSalary(5000);

        // Perform POST request and expect a successful creation
        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employeeRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Employee is created successfully with id = " + mockEmployee.getId()));
    } 
    
    @Test
    public void testDeleteEmployee_Success() throws Exception {
        int employeeId = 1;
        when(employeeService.isEmployeeExist(employeeId)).thenReturn(true);

        mockMvc.perform(delete("/employees/{id}", employeeId))
                .andExpect(status().isOk());

        verify(employeeService, times(1)).deleteEmployee(employeeId);
    }
    
    @Test
    public void testDeleteEmployee_NotFound() throws Exception {
        int employeeId = 1;
        when(employeeService.isEmployeeExist(employeeId)).thenReturn(false);

        mockMvc.perform(delete("/employees/{id}", employeeId))
                .andExpect(status().isNotFound());

        verify(employeeService, never()).deleteEmployee(employeeId);
    }


}
