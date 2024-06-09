package zw.co.zim.willplatform.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.ClientDto;
import zw.co.zim.willplatform.processor.ClientServiceProcessor;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.messages.request.ChangePasswordRequest;
import zw.co.zim.willplatform.utils.messages.request.ClientRequest;
import zw.co.zim.willplatform.utils.messages.request.LoginRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/clients")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ClientController {
    private final ClientServiceProcessor processor;

    public ClientController(ClientServiceProcessor processor) {
        this.processor = processor;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<ClientDto>> getAllClients(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<ClientDto> users = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<ClientDto>> createClient(@RequestBody @Valid ClientRequest clientRequest) {
        ApiResponse<ClientDto> recordDto = processor.save(clientRequest);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<ClientDto>> updateClient(@PathVariable("id") Long id, @RequestBody ClientDto clientDto) {
        ApiResponse<ClientDto> recordDto = processor.update(id, clientDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ClientDto>> findById(@PathVariable("id") Long id) {
        ApiResponse<ClientDto> recordDto = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<ClientDto>> deleteClient(@PathVariable("id") Long id) {
        ApiResponse<ClientDto> response = processor.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/findClientByEmailOrNationalId/{email}/{nationalId}")
    public ResponseEntity<ApiResponse<ClientDto>> findByEmailOrNationalId(
        @PathVariable("email") String email,
        @PathVariable("nationalId") String nationalId) {
        ApiResponse<ClientDto> recordDto = processor.findFirstByEmailOrNationalIdNumber(email, nationalId);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/findClientByNationalId/{nationalId}")
    public ResponseEntity<ApiResponse<ClientDto>> findByNationalId(
        @PathVariable("nationalId") String nationalId) {
        ApiResponse<ClientDto> recordDto = processor.findFirstByNationalIdNumber(nationalId);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/findClientByPassportNumber/{passport}")
    public ResponseEntity<ApiResponse<ClientDto>> findByPassportNumber(
        @PathVariable("passport") String passport) {
        ApiResponse<ClientDto> recordDto = processor.findFirstByPassportNumber(passport);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<ApiResponse<ClientDto>> changePassword(@RequestBody @Valid ChangePasswordRequest changePasswordRequest) {
        ApiResponse<ClientDto> recordDto = processor.changePassword(changePasswordRequest);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<ClientDto>> clientLogin(@RequestBody @Valid LoginRequest loginRequest) {
        ApiResponse<ClientDto> recordDto = processor.login(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

}
