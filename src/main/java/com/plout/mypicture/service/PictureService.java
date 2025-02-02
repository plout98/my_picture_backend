package com.plout.mypicture.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.plout.mypicture.model.dto.picture.PictureQueryRequest;
import com.plout.mypicture.model.dto.picture.PictureUploadRequest;
import com.plout.mypicture.model.entity.Picture;
import com.baomidou.mybatisplus.extension.service.IService;
import com.plout.mypicture.model.entity.User;
import com.plout.mypicture.model.vo.PictureVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
* @author 万帮帮
* @description 针对表【picture(图片)】的数据库操作Service
* @createDate 2025-02-02 21:17:52
*/
public interface PictureService extends IService<Picture> {

    /**
     * 上传图片
     *
     * @param multipartFile
     * @param pictureUploadRequest
     * @param loginUser
     * @return
     */
    PictureVO uploadPicture(MultipartFile multipartFile,
                            PictureUploadRequest pictureUploadRequest,
                            User loginUser);

    /**
     * 获取查询条件
     *
     * @param pictureQueryRequest
     * @return
     */
    public QueryWrapper<Picture> getQueryWrapper(PictureQueryRequest pictureQueryRequest);


    public PictureVO getPictureVO(Picture picture, HttpServletRequest request);

    public Page<PictureVO> getPictureVOPage(Page<Picture> picturePage, HttpServletRequest request);

    public void validPicture(Picture picture);
}
