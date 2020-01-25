package com.euthpic.order.utils;

import com.euthpic.order.vo.ResultVo;

public class ResultVoUtil {
    public static ResultVo success(Object object) {
        ResultVo resultVO = new ResultVo<>();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }
}
