package zw.co.zim.willplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.WillDocuments;
import zw.co.zim.willplatform.repository.WillDocumentsRepository;
import zw.co.zim.willplatform.service.WillDocumentsService;
import zw.co.zim.willplatform.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class WillDocumentsServiceImpl implements WillDocumentsService {
    private final WillDocumentsRepository repository;

    public WillDocumentsServiceImpl(WillDocumentsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<WillDocuments> findAll() {
        return repository.findAllByRecordStatusNot(RecordStatus.DELETED);
    }

    @Override
    public Optional<WillDocuments> findById(Long id) {
        return repository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public WillDocuments save(WillDocuments willDocuments) {
        return repository.save(willDocuments);
    }

    @Override
    public WillDocuments update(Long id, WillDocuments willDocuments) {
        Optional<WillDocuments> optional = this.findById(id);
        if (optional.isPresent()) {
            WillDocuments document = optional.get();
            document.setDocumentPath(willDocuments.getDocumentPath());
            document.setUserId(willDocuments.getUserId());
            document.setFileName(willDocuments.getFileName());
            document.setRecordStatus(willDocuments.getRecordStatus());
            return repository.save(document);
        }
        return willDocuments;
    }

    @Override
    public void deleteById(Long id) {
        Optional<WillDocuments> optional = this.findById(id);
        if (optional.isPresent()) {
            WillDocuments document = optional.get();
            document.setRecordStatus(RecordStatus.DELETED);
            repository.save(document);
        }
    }

    @Override
    public Page<WillDocuments> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByRecordStatusNot(pageable, RecordStatus.DELETED);
    }

    @Override
    public Page<WillDocuments> findAllByUserId(Client userId, Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByUserIdAndRecordStatusNot(pageable, userId, RecordStatus.DELETED);
    }
}
