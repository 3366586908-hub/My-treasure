package cn.cup.qs2023.music.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("mo_audit_log")
public class AuditLog extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long operatorId;
    private String targetType;
    private Long targetId;
    private String action;
    private String remark;
}