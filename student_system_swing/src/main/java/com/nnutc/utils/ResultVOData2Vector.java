package com.nnutc.utils;


import com.nnutc.common.Page;
import com.nnutc.common.ResultVO;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class ResultVOData2Vector {

    public static Vector<Vector> convertResultVOData2Vector(ResultVO resultVO) {
        List list = (List) resultVO.getData();
        Vector<Vector> vectors = new Vector<>();
        for (int i = 0; i < list.size(); i++) {
            HashMap map = (HashMap) list.get(i);
            Vector vector = new Vector();
            vector.addAll(map.values());
            vectors.add(vector);
        }
        return vectors;
    }
}
