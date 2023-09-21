package dream.user.service;

import antlr.Token;
import dream.common.domain.ResultTemplate;
import dream.common.exception.*;
import dream.security.jwt.repository.TokenRepository;
import dream.security.jwt.service.JwtService;
import dream.s3.dto.response.ResponseImageUrl;
import dream.user.domain.Follow;
import dream.user.domain.FollowRepository;
import dream.user.domain.User;
import dream.user.domain.UserRepository;
import dream.user.dto.request.RequestNickname;
import dream.user.dto.request.RequestToId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final FollowRepository followRepository;
    // 예시 - 지워질 코드
    public ResultTemplate getUser(long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(user).build();
    }

    public User getUserForDev(long id){
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        return user;
    }



    // 로그아웃
    public ResultTemplate logout(User user, HttpServletRequest request){

        //헤더에서 access를 추출하여 refreshToken 삭제 후 blackList에 등록
        String accessToken = jwtService.extractAccessToken(request).get();
        if(jwtService.isAccessTokenValid(accessToken)){
            String email = user.getEmail();
            Optional<String> refreshToken = tokenRepository.findByKey(email);
            if(!refreshToken.isEmpty()){
                //redis에서 해당 email key 값 삭제
                tokenRepository.deleteByEmail(email);

                //blacklist에 등록
                tokenRepository.saveBlackList(accessToken,  jwtService.getExpiration(accessToken)  );

            }

        }
        return ResultTemplate.builder().status(HttpStatus.OK.value()).data("success").build();
    }

    @Transactional
    public ResultTemplate updateUserImage(User user, String fileName) {

        user.updateProfileUrl(fileName);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data("success").build();
    }

    public ResultTemplate getUserImage(User user) {

        User findUser = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER_NOT_FOUND));

        ResponseImageUrl response = ResponseImageUrl.from(findUser);

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(response).build();
    }

    @Transactional
    public ResultTemplate setNickname(HttpServletResponse response, User user, RequestNickname request){

        if(user.getRole().name().equals("GUEST")){
            user.authorizeUser();
            jwtService.sendTokenDto(response, jwtService.createTokenDto(user.getUserId()));
        }else{
            throw new NotMatchException(NotMatchException.USER_STATUS);
        }
        user.updateNickname(request.getNickname());

        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(user).build();
    }

    @Transactional
    public ResultTemplate updateNickname(User user, RequestNickname request){
        if(user.getNickname().equals(request.getNickname())) throw new BadRequestException(BadRequestException.NO_CHANGE_NICKNAME);

        checkDuplicateNick(request);
        user.updateNickname(request.getNickname());
        return ResultTemplate.builder().status(HttpStatus.OK.value()).data(user).build();
    }

    public ResultTemplate checkDuplicateNick(RequestNickname request) {

        Optional<User> duplicatedUser = userRepository.findByNickname(request.getNickname());

//        log.info("duplicateUser : {} ", userRepository.findByNickname(nickname).get());
        if (duplicatedUser.isEmpty()) {
            return ResultTemplate.builder().status(HttpStatus.OK.value()).data("success").build();
        } else {
            throw new DuplicateException(DuplicateException.NICKNAME_DUPLICATE);
        }
    }

    @Transactional
    public ResultTemplate follow(User user, RequestToId request){

        User toUser = userRepository.findByUserId(request.getToId())
                .orElseThrow(() ->  {
                    throw new NoSuchElementException(NoSuchElementException.NO_SUCH_USER);
                });


        Follow follow = Follow.createFollow(user, toUser);

        int checkExistFollow = followRepository.checkExistFollow(user.getUserId(), toUser.getUserId());
        if(checkExistFollow>0) throw new DuplicateException(DuplicateException.FOLLOW_DUPLICATE);
        if(toUser.getUserId()==user.getUserId()) throw new BadRequestException(BadRequestException.IMPOSSIBLE_FOLLOW_SELF);

        followRepository.save(follow);
        return ResultTemplate.builder().status(HttpStatus.OK.value()).data("success").build();



    }

    @Transactional
    public ResultTemplate unfollowToId(User user, Long toId){

        User toUser = userRepository.findByUserId(toId)
                .orElseThrow(() ->  {
                    throw new NoSuchElementException(NoSuchElementException.NO_SUCH_USER);
                });

        Follow follow = followRepository.findByFromIdAndToId(user.getUserId(), toUser.getUserId())
                .orElseThrow(()->{
                    throw new BadRequestException(BadRequestException.UNFOLLOW_IMPOSSIBLE);
                });

        followRepository.delete(follow);
        return ResultTemplate.builder().status(HttpStatus.OK.value()).data("success").build();

    }




}
