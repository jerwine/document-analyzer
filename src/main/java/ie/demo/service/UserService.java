package ie.demo.service;

import ie.demo.mapper.UserMapper;
import ie.demo.domain.User;
import ie.demo.domain.repo.UserRepository;
import ie.demo.model.UserDTO;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDTO> findAll(Integer limit) {
        return userRepository.findAll(PageRequest.of(0, limit))
                .stream()
                .map(userMapper::toUserDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> findById(Integer id) {
        return userRepository.findById(Long.valueOf(id))
                .map(userMapper::toUserDTO);
    }

    public List<UserDTO> findInactiveByDatePeriod(String startDate, String endDate) {
        if(StringUtils.isEmpty(startDate) && StringUtils.isEmpty(endDate)) {
            return List.of();
        } else if(StringUtils.isEmpty(endDate)) {
            return mapCollect(userRepository.findUsersDocumentUpdateAfter(
                    LocalDate.parse(startDate).atStartOfDay()));
        } else if(StringUtils.isEmpty(startDate)) {
            return mapCollect(userRepository.findUsersWithDocumentUpdateBefore(
                    LocalDate.parse(endDate).atStartOfDay()));
        } else {
            return mapCollect(userRepository.findUsersWithDocumentUpdateBetween(
                    LocalDate.parse(startDate).atStartOfDay(), LocalDate.parse(endDate).atStartOfDay()));
        }
    }

    private List<UserDTO> mapCollect(List<User> usersStream) {
        return usersStream.stream()
                .map(userMapper::toUserDTO)
                .collect(Collectors.toList());
    }
}
