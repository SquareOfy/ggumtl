package dream.security.jwt.repository;

import dream.security.jwt.dto.RefreshTokenDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
public class TokenRepository {

    @Value("${jwt.refresh.expiration}")
    private Long refreshTokenExpirationPeriod;
    RedisTemplate redisTemplate;

    public TokenRepository(final RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }


    public void saveRefreshToken(RefreshTokenDto refreshToken){
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(String.valueOf(refreshToken.getRefreshToken()), String.valueOf(refreshToken.getUserId()));
        redisTemplate.expire(String.valueOf(refreshToken.getRefreshToken()), refreshTokenExpirationPeriod, TimeUnit.MICROSECONDS);

    }

    public Optional<RefreshTokenDto> findByRefreshToken(String refreshToken) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        Optional<String> userIdOptional = Optional.ofNullable(valueOperations.get(refreshToken));


        if(userIdOptional.isEmpty()) return Optional.empty();
        Optional<Long> userId = Optional.ofNullable(Long.parseLong(valueOperations.get(refreshToken)));
        return Optional.of(new RefreshTokenDto(userId.get(), refreshToken));
    }

    public void deleteByRefreshToken(String refreshToken){
        redisTemplate.delete(refreshToken);
    }

    public void saveBlackList(String accessToken, Long expiration){
        redisTemplate.opsForValue().set(accessToken, "logout", expiration, TimeUnit.MILLISECONDS);
    }
}
