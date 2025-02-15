package ie.demo.mapper;

import ie.demo.domain.Document;
import ie.demo.model.DocumentDTO;
import ie.demo.model.WordCountDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface DocumentMapper {

    DocumentDTO toDocumentDTO(Document document);

    WordCountDTO toWordCountDTO(String value, Integer frequency);
}