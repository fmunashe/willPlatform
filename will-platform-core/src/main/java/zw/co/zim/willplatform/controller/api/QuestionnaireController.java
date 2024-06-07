package zw.co.zim.willplatform.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.QuestionnaireDto;
import zw.co.zim.willplatform.processor.QuestionnaireProcessor;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.messages.request.QuestionnaireRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/questionnaire")
@CrossOrigin(origins = "*", maxAge = 3600)
public class QuestionnaireController {
    private final QuestionnaireProcessor processor;

    public QuestionnaireController(QuestionnaireProcessor processor) {
        this.processor = processor;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<QuestionnaireDto>> getAllQuestionnaires(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<QuestionnaireDto> questionnaires = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(questionnaires);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<QuestionnaireDto>> createQuestionnaire(@RequestBody @Valid QuestionnaireRequest questionnaireRequest) {
        ApiResponse<QuestionnaireDto> recordDto = processor.save(questionnaireRequest);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{questionnaireId}")
    public ResponseEntity<ApiResponse<QuestionnaireDto>> updateQuestionnaire(@RequestBody @Valid QuestionnaireDto questionnaireDto, @PathVariable("questionnaireId") Long questionnaireId) {
        ApiResponse<QuestionnaireDto> recordDto = processor.update(questionnaireId, questionnaireDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }


    @GetMapping("/byClientId/{clientId}")
    public ResponseEntity<ApiResponse<QuestionnaireDto>> findFirstByUserId(@PathVariable("clientId") Long clientId) {
        ApiResponse<QuestionnaireDto> recordDto = processor.findFirstByUserId(clientId);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<QuestionnaireDto>> findById(@PathVariable("id") Long id) {
        ApiResponse<QuestionnaireDto> recordDto = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/{questionnaireId}")
    public ResponseEntity<ApiResponse<QuestionnaireDto>> deleteQuestionnaireById(
        @PathVariable("questionnaireId") Long questionnaireId) {
        ApiResponse<QuestionnaireDto> recordDto = processor.deleteById(questionnaireId);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }
}
