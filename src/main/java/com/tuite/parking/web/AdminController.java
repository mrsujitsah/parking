package com.tuite.parking.web;

import com.tuite.parking.dto.AdminAddLevelRequest;
import com.tuite.parking.dto.AdminAddSlotRequest;
import com.tuite.parking.dto.StatusResponse;
import com.tuite.parking.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/levels")
    public String addLevel(@Valid @RequestBody AdminAddLevelRequest req){
        adminService.addLevel(req.getLevelId());
        return "Level added: "+ req.getLevelId();
    }

    @PostMapping("/slots")
    public String addSlots(@Valid @RequestBody AdminAddSlotRequest req){
        adminService.addSlots(req.getLevelId(), req.getType(), req.getCount());
        return "Added "+ req.getCount()+" "+req.getType()+" slots to level "+ req.getLevelId();
    }

    @GetMapping("/status")
    public StatusResponse status(){
        return adminService.status();
    }
}
