package hr.algebra.healthyapp.controller;

import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/example")
@RequiredArgsConstructor
public class ExampleController {

    @GetMapping
    @Secured("ADMIN")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello");
    }
}
