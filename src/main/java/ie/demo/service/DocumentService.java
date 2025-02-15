package ie.demo.service;

import ie.demo.mapper.DocumentMapper;
import ie.demo.domain.repo.DocumentRepository;
import ie.demo.model.DocumentDTO;
import ie.demo.model.LongestWordDTO;
import ie.demo.model.WordCountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ie.demo.service.WordUtils.extractLongestWords;
import static ie.demo.service.WordUtils.extractTopWordFrequencies;
import static ie.demo.service.WordUtils.create;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;
    private final VertexAIService vertexAIService;

    public List<DocumentDTO> findAll(Integer limit) {
        return documentRepository.findAll(PageRequest.of(0, limit))
                .stream()
                .map(documentMapper::toDocumentDTO)
                .collect(Collectors.toList());
    }

    public Optional<DocumentDTO> findById(Integer id) {
        return documentRepository.findById(Long.valueOf(id))
                .map(documentMapper::toDocumentDTO);
    }

    public List<WordCountDTO> findWordCountByDocumentId(Integer id, int resultCount, boolean descending) {
        Optional<DocumentDTO>documentOptional = findById(id);
        if(documentOptional.isPresent()) {
            return extractTopWordFrequencies(documentMapper, documentOptional.get().getBody(), resultCount, descending);
        }
        return List.of();
    }

    public List<LongestWordDTO> findLongestWordByDocumentId(Integer id) {
        Optional<DocumentDTO>documentOptional = findById(id);
        if(documentOptional.isPresent()) {
            List<String>longestWords = extractLongestWords(documentOptional.get().getBody());
            if(!longestWords.isEmpty()) {
                return longestWords.stream()
                        .map(word -> create(word,vertexAIService.synonyms(word)))
                        .collect(Collectors.toList());
            }
        }
        return List.of();
    }
}
