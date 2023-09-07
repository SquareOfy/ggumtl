package dream.user.service;

import dream.common.domain.ResultTemplate;
import dream.common.exception.NotFoundException;
import dream.user.domain.User;
import dream.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 예시 - 지워질 코드
    public ResultTemplate getUser(long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        return ResultTemplate.builder().status(HttpStatus.OK).data(user).build();
    }
}
