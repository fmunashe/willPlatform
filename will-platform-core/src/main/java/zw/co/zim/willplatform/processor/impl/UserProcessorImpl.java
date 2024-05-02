package zw.co.zim.willplatform.processor.impl;

import zw.co.zim.willplatform.dto.UserRecordDto;
import zw.co.zim.willplatform.dto.mapper.UserDtoMapper;
import zw.co.zim.willplatform.model.User;
import zw.co.zim.willplatform.processor.UserProcessor;
import zw.co.zim.willplatform.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserProcessorImpl implements UserProcessor {
    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    @Override
    public List<UserRecordDto> findAll() {
        return userService.findAll()
            .stream()
            .map(userDtoMapper)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<UserRecordDto> findById(Long id) {
        Optional<User> optionalUser = userService.findById(id);
        return optionalUser.map(userDtoMapper);
    }

    @Override
    public UserRecordDto save(UserRecordDto userRecordDto) {
        User user = userService.save(new User(userRecordDto));
        return userDtoMapper.apply(user);
    }

    @Override
    public UserRecordDto update(Long id, UserRecordDto userRecordDto) {
        User user = userService.update(id, new User(userRecordDto));
        return userDtoMapper.apply(user);
    }

    @Override
    public void deleteById(Long id) {
        userService.deleteById(id);
    }
}
