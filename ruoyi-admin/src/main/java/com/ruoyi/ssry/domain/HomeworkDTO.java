// com.ruoyi.ssry.domain.dto.HomeworkDTO.java
package com.ruoyi.ssry.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HomeworkDTO {
    private String title;
    private String content;
    private Long courseId;
    private String deadline; // "2025-12-10T23:59"
    private String requiredFormat;
    private Integer maxSizeMb;
}