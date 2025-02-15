package ie.demo.cotroller;

import ie.demo.api.DocumentsApi;
import ie.demo.model.DocumentDTO;
import ie.demo.model.LongestWordDTO;
import ie.demo.model.WordCountDTO;
import ie.demo.service.DocumentService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DocumentController implements DocumentsApi {

    private final DocumentService documentService;

    @Override
    public ResponseEntity<List<DocumentDTO>> getDocuments(
            @Parameter(name = "limit", description = "result limit", in = ParameterIn.QUERY) @Valid
            @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit) {
        return ResponseEntity.ofNullable(documentService.findAll(limit));
    }

    @Override
    public ResponseEntity<DocumentDTO> getDocumentById(
            @Parameter(name = "id", description = "ID of the Document to return", required = true, in = ParameterIn.PATH)
            @PathVariable("id") Integer id) {
        return documentService.findById(id).map(ResponseEntity::ofNullable)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<WordCountDTO>> getWordCountByDocumentId(
            @Parameter(name = "id", description = "ID of the Document to return the word count for", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id,
            @Parameter(name = "size", description = "items size to return", in = ParameterIn.QUERY) @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
            @Parameter(name = "sort", description = "items sorting", in = ParameterIn.QUERY) @Valid @RequestParam(value = "sort", required = false, defaultValue = "desc") String sort) {
        return ResponseEntity.ofNullable(documentService.findWordCountByDocumentId(id, size,  "desc".equalsIgnoreCase(sort)));
    }

    @Override
    public ResponseEntity<List<LongestWordDTO>> getLongestWordByDocumentId(
            @Parameter(name = "id", description = "ID of the Document to return the word count for", required = true, in = ParameterIn.PATH)
            @PathVariable("id") Integer id) {
        return ResponseEntity.ofNullable(documentService.findLongestWordByDocumentId(id));
    }

}