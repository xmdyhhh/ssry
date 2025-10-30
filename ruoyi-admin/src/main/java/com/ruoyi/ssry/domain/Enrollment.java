package com.ruoyi.ssry.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {
    private Long id;
    private String studentId;
    private String courseId;
    private String status;
}
