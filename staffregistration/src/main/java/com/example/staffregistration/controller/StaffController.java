package com.example.staffregistration.controller;

import java.util.List;

import com.example.staffregistration.model.Staff;
import com.example.staffregistration.repository.StaffRepository;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class StaffController {
    @Autowired
    private StaffRepository staffRepository;

    @GetMapping("/")
    public List<Staff> getAllStaff()
    {
        return staffRepository.findAll();
    }


    @PostMapping("/add")
    public Staff addNewStaff(@RequestBody Staff staff)
    {
        return staffRepository.save(staff);
    }

    //get employee by id
    @GetMapping("/{id}")
    public ResponseEntity<Staff> getStaffbyId(@PathVariable int id)
    {
        //Optional<Staff> staff = staffRepository.findById(id);
        Staff staff = staffRepository.findById(id)
        .orElseThrow(()-> new IllegalStateException("Staff with Id "+ id+ " does not exists"));
        return ResponseEntity.ok(staff);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Staff> updateStaff(@PathVariable int id, @RequestBody Staff staff)
    {
        Staff staffId = staffRepository.findById(id)
        .orElseThrow(()-> new IllegalStateException("Staff with Id "+ id+ " does not exists"));
        staffId.setFirstName(staff.getFirstName());
        staffId.setLastName(staff.getLastName());
        staffId.setEmail(staff.getEmail());
        
        Staff updateStaff = staffRepository.save(staffId);

        return ResponseEntity.ok(updateStaff);
    }


    
}
