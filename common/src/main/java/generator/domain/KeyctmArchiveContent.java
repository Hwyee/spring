package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 大客档案表
 * @TableName keyctm_archive_content
 */
@TableName(value ="keyctm_archive_content")
@Data
public class KeyctmArchiveContent implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 业务
     */
    @TableField(value = "biz_type")
    private Integer bizType;

    /**
     * 档案类型
     */
    @TableField(value = "archive_type")
    private String archiveType;

    /**
     * 档案内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 状态，1正式0草稿
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 创建人
     */
    @TableField(value = "creator")
    private Integer creator;

    /**
     * 修改人
     */
    @TableField(value = "modifier")
    private Integer modifier;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(value = "modify_time")
    private LocalDateTime modifyTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}