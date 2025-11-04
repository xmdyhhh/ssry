package com.ruoyi.ssry.mapper;


import com.ruoyi.ssry.domain.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageMapper {

    List<Message> selectNoticeByReceiver(@Param("receiverType") String receiverType, @Param("receiverId") Long receiverId);

    int updateAsRead(Long id);

    int markAllMessageAsRead();

    Message selectById(Long id);

//    int insertNotice(Message message);
}