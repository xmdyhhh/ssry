package com.ruoyi.ssry.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaveAllowedCollegesDTO {
    private Long[] courseIds;
    private Long[] collegeIds;
}