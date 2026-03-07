package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.patient.dto.PatientRegistrationData;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PatientController {

    @PostMapping("/patients")
    public void register(@RequestBody @Valid PatientRegistrationData data){
        System.out.println(data);
    }

}