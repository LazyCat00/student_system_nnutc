package com.nnutc.utils;

import java.util.Vector;

public class VectorUtil {
    public static Vector<Vector<Object>> convertVectorOfVectorsToVectorOfObjectVectors(Vector<Vector> vectorOfVectors) {
        Vector<Vector<Object>> vectorOfObjectVectors = new Vector<>();
        for (Vector vector : vectorOfVectors) {
            Vector<Object> objectVector = new Vector<>();
            for (Object object : vector) {
                objectVector.add(object);
            }
            vectorOfObjectVectors.add(objectVector);
        }
        return vectorOfObjectVectors;
    }
}
