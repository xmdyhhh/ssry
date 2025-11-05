package com.ruoyi.ssry.mapper;


import com.ruoyi.ssry.domain.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface MessageMapper {

    List<Message> selectNoticeByReceiver(@Param("receiverType") String receiverType, @Param("receiverId") Long receiverId);

    int updateAsRead(Long id);


    Message selectById(Long id);

    int markAllMessagesAsRead(@Param("receiverType") String receiverType,@Param("receiverId") String receiverId);

    void batchInsert(List<Message> messages);

    List<Message> selectadminNoticeByReceiver(@Param("receiverType") String receiverType, @Param("receiverId") Long receiverId);

    int deleteMessagesBefore(@Param("createTime") Date createTime);

    void insert(Message message);

    void updateAppStatus(@Param("id") Long id, @Param("appStatus") String appStatus);
}