package zw.co.zim.willplatform.service.impl;

import zw.co.zim.willplatform.exceptions.RecordExistsException;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.User;
import zw.co.zim.willplatform.repository.UserRepository;
import zw.co.zim.willplatform.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("User with ID of " + id + " not found")));
    }

    @Override
    public User save(User user) {
        Optional<User> optionalNationalId = userRepository.findFirstByNationalIdNumber(user.getNationalIdNumber());
        Optional<User> optionalPassport = userRepository.findFirstByPassportNumber(user.getPassportNumber());
        if (optionalNationalId.isPresent())
            throw new RecordExistsException("A user with the same national id number of " + user.getNationalIdNumber() + " already exist");

        if (optionalPassport.isPresent())
            throw new RecordExistsException("A user with the same passport number of " + user.getPassportNumber() + " already exist");

        return userRepository.save(user);
    }

    @Override
    public User update(Long id, User user) {
        Optional<User> currentUser = userRepository.findById(id);
        if (currentUser.isEmpty())
            throw new RecordNotFoundException("User with id of " + id + " not found");
        user.setId(currentUser.get().getId());
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty())
            throw new RecordNotFoundException("User with id of " + id + " not found");
        userRepository.deleteById(id);
    }
}
