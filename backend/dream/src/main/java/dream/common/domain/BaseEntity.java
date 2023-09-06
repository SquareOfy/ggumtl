package dream.common.domain;

import lombok.Getter;

import java.time.LocalDateTime;

//@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseEntity {

//    @CreatedDate
    private LocalDateTime createdAt;

//    @LastModifiedDate
    private LocalDateTime updatedAt;
}