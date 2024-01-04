package com.nnutc.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnutc.common.ResultVO;
import java.io.IOException;

public class JsonUtils {

    public static ResultVO parseResult(String json){

        ObjectMapper om = new ObjectMapper();
        ResultVO resultInfo = null;
        try {
            resultInfo = om.readValue(json, ResultVO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultInfo;
    }

}
