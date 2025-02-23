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
import java.time.LocalTime;
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

    /**
     * Find users that weren't uploading a document(s) between the {@code startDate} and {@code endDate}
     * @param startDate
     * @param endDate
     * @return
     */
    public List<UserDTO> findInactiveByDatePeriod(String startDate, String endDate) {
        if(StringUtils.isEmpty(startDate) && StringUtils.isEmpty(endDate)) {
            return mapCollect(userRepository
                    .findUsersWithoutDocumentUpdates());
        } else if(StringUtils.isEmpty(endDate)) {
            return mapCollect(userRepository.findUsersWithoutDocumentUpdatesOnOrAfter(
                    LocalDate.parse(startDate).atStartOfDay()));
        } else if(StringUtils.isEmpty(startDate)) {
            return mapCollect(userRepository.findUsersWithoutDocumentUpdatesOnOrBefore(
                    LocalDate.parse(endDate).atTime(LocalTime.MAX)));
        } else {
            return mapCollect(userRepository.findUsersWithoutDocumentUpdatesBetween(
                    LocalDate.parse(startDate).atStartOfDay(), LocalDate.parse(endDate).atTime(LocalTime.MAX)));
        }
    }

    private List<UserDTO> mapCollect(List<User> usersStream) {
        return usersStream.stream()
                .map(userMapper::toUserDTO)
                .collect(Collectors.toList());
    }
}
