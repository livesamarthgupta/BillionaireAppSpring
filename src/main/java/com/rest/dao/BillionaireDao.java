package com.rest.dao;

import com.rest.model.Billionaire;

import java.util.List;

public interface BillionaireDao {
    List<Billionaire> getBillionaires();
    Billionaire getBillionaire(Long billionaireId);
    int deleteBillionaire(Long billionaireId);
    Billionaire updateBillionaire(Billionaire billionaire);
    int createBillionaire(Billionaire billionaire);
}
