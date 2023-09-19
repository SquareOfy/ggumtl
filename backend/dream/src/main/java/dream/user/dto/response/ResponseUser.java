package dream.user.dto.response;

import dream.card.domain.Grade;
import dream.card.dto.response.ResponseKeyword;
import dream.common.domain.BaseCheckType;
import dream.user.domain.Role;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class ResponseUser {
    private long userId;
    private String name;
    private String nickname;
    private int point;
    private String profileImageName;
    private String profileUrl;
    private Double wrigglePoint;
    private String email;

    private Role role;

}
