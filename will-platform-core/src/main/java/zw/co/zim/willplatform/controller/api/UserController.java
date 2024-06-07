package zw.co.zim.willplatform.controller.api;

import zw.co.zim.willplatform.dto.UserRecordDto;
import zw.co.zim.willplatform.processor.UserProcessor;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/clients")
@RequiredArgsConstructor
public class UserController {
    private final UserProcessor userProcessor;

    @GetMapping
    public ResponseEntity<List<UserRecordDto>> getAllUsers() {
        List<UserRecordDto> users = userProcessor.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }


    @PostMapping("/save")
    public ResponseEntity<UserRecordDto> createUser(@RequestBody @Valid UserRecordDto userRecordDto) {
        UserRecordDto recordDto = userProcessor.save(userRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserRecordDto> updateUser(@PathVariable("id") Long id, @RequestBody UserRecordDto userRecordDto) {
        UserRecordDto recordDto = userProcessor.update(id, userRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRecordDto> findById(@PathVariable("id") Long id) {
        Optional<UserRecordDto> recordDto = userProcessor.findById(id);
        return recordDto.map(userRecordDto -> ResponseEntity.status(HttpStatus.OK).body(userRecordDto)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        userProcessor.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Record with id of " + id + " successfully deleted");
    }

}
