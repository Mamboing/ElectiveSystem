package com.ap.electivesystem.controller.admin;

import com.ap.electivesystem.config.auth.annotation.Register;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Register(Register.SCORE_MANAGE)
@RestController
@RequestMapping("/admin/score")
public class ScoreController {
}
