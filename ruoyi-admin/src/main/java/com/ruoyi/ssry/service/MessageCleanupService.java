package com.ruoyi.ssry.service;

import com.ruoyi.ssry.mapper.MessageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class MessageCleanupService {

    private static final Logger log = LoggerFactory.getLogger(MessageCleanupService.class);

    @Autowired
    private MessageMapper messageMapper;

    /**
     * 每天凌晨 2 点执行：删除 90 天前的所有消息
     */
    @Scheduled(cron = "0 0 2 * * ?") // 每天 02:00:00 执行
    public void cleanupOldMessages() {
        // 计算 90 天前的时间
        LocalDateTime ninetyDaysAgo = LocalDateTime.now().minusDays(90);
        Date cutoffDate = Date.from(ninetyDaysAgo.atZone(ZoneId.systemDefault()).toInstant());

        log.info("开始清理 {} 之前的消息...", cutoffDate);

        int deletedCount = messageMapper.deleteMessagesBefore(cutoffDate);
        log.info("共删除 {} 条过期消息", deletedCount);
    }

}