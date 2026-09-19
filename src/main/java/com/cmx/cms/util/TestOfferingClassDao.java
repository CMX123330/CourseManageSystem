package com.cmx.cms.util;

import java.util.List;

import com.cmx.cms.dao.OfferingClassDao;
import com.cmx.cms.model.OfferingClass;

public class TestOfferingClassDao{
    public static void main(String[] args) {
        try {
            OfferingClassDao ocDao = new OfferingClassDao();
            List<OfferingClass> oc = ocDao.getByOfferingId("KK001");
            for (OfferingClass OC : oc) {
                System.err.println(oc.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}