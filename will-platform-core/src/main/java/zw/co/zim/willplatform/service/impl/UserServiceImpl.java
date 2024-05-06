package zw.co.zim.willplatform.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.exceptions.RecordExistsException;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.repository.ClientRepository;
import zw.co.zim.willplatform.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final ClientRepository userRepository;

    @Override
    public List<Client> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<Client> findById(Long id) {
        return Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("User with ID of " + id + " not found")));
    }

    @Override
    public Client save(Client user) {
        Optional<Client> optionalNationalId = userRepository.findFirstByNationalIdNumberAndRecordStatusNot(user.getNationalIdNumber(), RecordStatus.DELETED);
        Optional<Client> optionalPassport = userRepository.findFirstByPassportNumberAAndRecordStatusNot(user.getPassportNumber(), RecordStatus.DELETED);
        if (optionalNationalId.isPresent())
            throw new RecordExistsException("A user with the same national id number of " + user.getNationalIdNumber() + " already exist");

        if (optionalPassport.isPresent())
            throw new RecordExistsException("A user with the same passport number of " + user.getPassportNumber() + " already exist");

        return userRepository.save(user);
    }

    @Override
    public Client update(Long id, Client user) {
        Optional<Client> currentUser = userRepository.findById(id);
        if (currentUser.isEmpty())
            throw new RecordNotFoundException("User with id of " + id + " not found");
        user.setId(currentUser.get().getId());
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Client> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty())
            throw new RecordNotFoundException("User with id of " + id + " not found");
        userRepository.deleteById(id);
    }
}
