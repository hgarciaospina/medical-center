package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.doctor.dto.DoctorRegistrationData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MedicalController {
@PostMapping("/doctors")
    public void register(@RequestBody  @Valid DoctorRegistrationData data){
    System.out.println(data);
    }
}
