package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.WillDocumentsDto;
import zw.co.zim.willplatform.model.WillDocuments;

import java.util.function.Function;

@Service
public class WillDocumentsDtoMapper implements Function<WillDocuments, WillDocumentsDto> {
    @Override
    public WillDocumentsDto apply(WillDocuments willDocuments) {
        return WillDocumentsDto.builder()
            .id(willDocuments.getId())
            .userId(willDocuments.getUserId())
            .documentPath(willDocuments.getDocumentPath())
            .fileName(willDocuments.getFileName())
            .recordStatus(willDocuments.getRecordStatus())
            .createdAt(willDocuments.getCreatedAt())
            .updatedAt(willDocuments.getUpdatedAt())
            .build();
    }
}
