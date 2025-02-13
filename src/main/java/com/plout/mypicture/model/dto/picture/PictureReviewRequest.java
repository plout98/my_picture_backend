package com.plout.mypicture.model.dto.picture;

import lombok.Data;

import java.io.Serializable;

@Data
public class PictureReviewRequest implements Serializable {

    private static final long serialVersionUID = -580438113261032543L;
    /**
     * 图片id
     */
    private Long id;

    /**
     * 审核状态 0-待审核 1-审核通过 2-审核不通过
     */
    private Integer reviewStatus;

    /**
     * 审核信息
     */
    private String reviewMessage;

}
