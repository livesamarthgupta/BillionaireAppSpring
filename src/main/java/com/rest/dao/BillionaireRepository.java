package com.rest.dao;

import com.rest.model.Billionaire;

import java.util.List;

public interface BillionaireRepository {
    List<Billionaire> getBillionaires();
    Billionaire getBillionaire(Long billionaireId);
    void deleteBillionaire(Long billionaireId);
    Billionaire updateBillionaire(Billionaire billionaire);
    void createBillionaire(Billionaire billionaire);
}
