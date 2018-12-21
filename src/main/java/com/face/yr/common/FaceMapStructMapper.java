

package com.face.yr.common;



import com.face.yr.domain.po.FaceClass;
import com.face.yr.domain.po.FaceUser;
import com.face.yr.domain.vo.FaceClassVo;
import com.face.yr.domain.vo.FaceUserVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
@SuppressWarnings("all")
public interface FaceMapStructMapper {

    FaceMapStructMapper INSTANCE = Mappers.getMapper(FaceMapStructMapper.class);


    FaceUserVo UserPoToVo(FaceUser faceUser);

    List<FaceUserVo> UsersPoToVo(List<FaceUser> faceUsers);

    FaceClassVo classePoToVo(FaceClass faceClasses);
    List<FaceClassVo> classesPoToVo(List<FaceClass> faceClasses);
}


	