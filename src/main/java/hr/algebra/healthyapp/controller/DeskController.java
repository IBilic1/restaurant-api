package hr.algebra.healthyapp.controller;

import hr.algebra.healthyapp.dto.DeskDto;
import hr.algebra.healthyapp.mapper.DeskMapper;
import hr.algebra.healthyapp.service.DeskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/desk")
@RequiredArgsConstructor
public class DeskController {

    private DeskService deskService;

    private DeskMapper deskMapper;

    @Autowired
    public DeskController(DeskService deskService, DeskMapper deskMapper) {
        this.deskService = deskService;
        this.deskMapper = deskMapper;
    }

    @GetMapping
    public ResponseEntity<List<DeskDto>> getDesks() {
        return ResponseEntity.ok(deskMapper.destinationToSource(deskService.getAllDesks()));
    }


    @GetMapping("/{id}")
    public ResponseEntity<DeskDto> getDesk(@PathVariable String id) {
        return ResponseEntity.of(deskService.getDesk(id).map(deskMapper::sourceToDestination));
    }

    @PostMapping
    @Secured("ADMIN")
    public ResponseEntity<Void> createDesk(@RequestBody DeskDto desk) {
        deskService.saveDesk(deskMapper.destinationToSource(desk));
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Secured("ADMIN")
    public ResponseEntity<Void> updateDesk(@RequestBody DeskDto deskDto) {
        deskService.saveDesk(deskMapper.destinationToSource(deskDto));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{deskId}")
    @Secured("ADMIN")
    public ResponseEntity<Void> deleteDesk(@PathVariable String deskId) {
        deskService.deleteDesk(deskId);
        return ResponseEntity.ok().build();
    }
}
