package com.hostelDatabase.controller;


import com.hostelDatabase.model.Hosteler;
import com.hostelDatabase.service.HostelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Controller
public class WebController {
    @GetMapping("/hosteler")
    public String greeting() {
        return "inputform";
    }
}
