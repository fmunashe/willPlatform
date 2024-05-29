package zw.co.zim.willplatform.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.repository.ClientRepository;
import zw.co.zim.willplatform.service.ClientsService;
import zw.co.zim.willplatform.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientsServiceImpl implements ClientsService {
    private final ClientRepository userRepository;

    @Override
    public List<Client> findAll() {
        return userRepository.findAllByRecordStatusNot(RecordStatus.DELETED);
    }

    @Override
    public Page<Client> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return userRepository.findAllByRecordStatusNot(pageable, RecordStatus.DELETED);
    }

    @Override
    public Optional<Client> findFirstByEmailOrNationalIdNumber(String email, String nationalId) {
        return userRepository.findFirstByEmailOrNationalIdNumberAndRecordStatusNot(email, nationalId, RecordStatus.DELETED);
    }

    @Override
    public Optional<Client> findFirstByNationalIdNumber(String nationalId) {
        return userRepository.findFirstByNationalIdNumberAndRecordStatusNot(nationalId, RecordStatus.DELETED);
    }

    @Override
    public Optional<Client> findFirstByPassportNumber(String passportNumber) {
        return userRepository.findFirstByPassportNumberAndRecordStatusNot(passportNumber, RecordStatus.DELETED);
    }

    @Override
    public Optional<Client> findById(Long id) {
        return userRepository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public Client save(Client user) {
        return userRepository.save(user);
    }

    @Override
    public Client update(Long id, Client user) {
        Optional<Client> currentUser = this.findById(id);
        if (currentUser.isPresent()) {
            user.setId(currentUser.get().getId());
            return userRepository.save(user);
        }
        return user;
    }

    @Override
    public void deleteById(Long id) {
        Optional<Client> userOptional = this.findById(id);
        if (userOptional.isPresent()) {
            Client client = userOptional.get();
            client.setRecordStatus(RecordStatus.DELETED);
            userRepository.save(client);
        }
    }
}
