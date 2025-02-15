package ie.demo.cotroller;

import ie.demo.api.UsersApi;
import ie.demo.model.UserDTO;
import ie.demo.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UsersApi {

    private final UserService userService;

    @Override
    public ResponseEntity<List<UserDTO>> getUsers(
            @Parameter(name = "limit", description = "result limit", in = ParameterIn.QUERY)
            @Valid @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit) {
        return ResponseEntity.ofNullable(userService.findAll(limit));
    }

    @Override
    public ResponseEntity<UserDTO> getUserById(
            @Parameter(name = "id", description = "ID of the User to return", required = true, in = ParameterIn.PATH)
            @PathVariable("id") Integer id) {
        return userService.findById(id).map(ResponseEntity::ofNullable)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<UserDTO>> getInactiveUsers(
            @Parameter(name = "startDate", description = "Start Date to filter with", in = ParameterIn.QUERY)
            @Valid @RequestParam(value = "startDate", required = false) String startDate,
            @Parameter(name = "endDate", description = "End Date to filter with", in = ParameterIn.QUERY)
            @Valid @RequestParam(value = "endDate", required = false) String endDate) {
        return ResponseEntity.ofNullable(userService.findInactiveByDatePeriod(startDate, endDate));
    }
}