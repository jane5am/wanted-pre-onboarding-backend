package wanted.preonboarding.recruit.service;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import wanted.preonboarding.dto.CreateUserRequestDto;
import wanted.preonboarding.recruit.Repository.JobPostingRepository;
import wanted.preonboarding.recruit.Repository.UserRepository;
import wanted.preonboarding.recruit.domain.User;

import java.util.Optional;

@Service
@RequiredArgsConstructor()
public class UserService {

    private final UserRepository userRepository;

    public User createUser(CreateUserRequestDto createUserRequestDto) throws BadRequestException {

        // 아이디 중복체크
        if (idCheck(createUserRequestDto.getEmail()).isPresent()) {
            throw new BadRequestException("Email is already in use");
        }

        // 비밀번호 검증
        String password = createUserRequestDto.getPassword();
        if (!isPasswordValid(password)) {
            throw new BadRequestException("Password does not meet the security requirements");
        }

        User user = new User();
        user.setEmail(createUserRequestDto.getEmail());
        user.setName(createUserRequestDto.getName());  // username 설정
        user.setPassword(createUserRequestDto.getPassword()); // 비밀번호 암호화

        return userRepository.save(user);
    }

    // 이메일 존재 확인
    public Optional<User> idCheck(String email) {
        return userRepository.findByEmail(email);
    }

    // 비밀번호 유효성 검사
    private boolean isPasswordValid(String password) {
        String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*?_])[A-Za-z\\d!@#$%^&*?_]{8,16}$";
        return password.matches(passwordPattern);
    }

}
