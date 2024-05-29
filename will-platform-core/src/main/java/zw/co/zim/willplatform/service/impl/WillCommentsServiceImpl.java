package zw.co.zim.willplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.WillComments;
import zw.co.zim.willplatform.repository.WillCommentsRepository;
import zw.co.zim.willplatform.service.WillCommentsService;
import zw.co.zim.willplatform.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class WillCommentsServiceImpl implements WillCommentsService {
    private final WillCommentsRepository repository;

    public WillCommentsServiceImpl(WillCommentsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<WillComments> findAll() {
        return repository.findAllByRecordStatusNot(RecordStatus.DELETED);
    }

    @Override
    public Optional<WillComments> findById(Long id) {
        return repository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public WillComments save(WillComments willComments) {
        return repository.save(willComments);
    }

    @Override
    public WillComments update(Long id, WillComments willComments) {
        Optional<WillComments> optional = this.findById(id);
        if (optional.isPresent()) {
            WillComments comments = optional.get();
            comments.setComments(willComments.getComments());
            comments.setUserId(willComments.getUserId());
            comments.setRecordStatus(willComments.getRecordStatus());
            return repository.save(comments);
        }
        return willComments;
    }

    @Override
    public void deleteById(Long id) {
        Optional<WillComments> optional = this.findById(id);
        if (optional.isPresent()) {
            WillComments comments = optional.get();
            comments.setRecordStatus(RecordStatus.DELETED);
            repository.save(comments);
        }
    }

    @Override
    public Page<WillComments> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByRecordStatusNot(pageable, RecordStatus.DELETED);
    }

    @Override
    public Page<WillComments> findAllByUserId(Client userId, Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByUserIdAndRecordStatusNot(pageable, userId, RecordStatus.DELETED);
    }
}
