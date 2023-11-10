package hr.algebra.healthyapp.controller;

import hr.algebra.healthyapp.dto.UserDto;
import hr.algebra.healthyapp.mapper.UserMapper;
import hr.algebra.healthyapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping
    @Secured("ADMIN")
    public ResponseEntity<UserDto> getUsersByDoctor(Principal principal) {
        return ResponseEntity.of(userService.getUser(principal.getName()).map(UserMapper.INSTANCE::sourceToDestination));
    }

    @GetMapping("/all")
    @Secured("ADMIN")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(UserMapper.INSTANCE.destinationToSource(userService.getAllPatient()));
    }
}
