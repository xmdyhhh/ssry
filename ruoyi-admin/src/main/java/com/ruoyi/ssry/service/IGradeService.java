package com.ruoyi.ssry.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.ssry.domain.Grade;

import java.util.List;

public interface IGradeService {
    public List<Grade> studentgradelist(String studentid);
}
