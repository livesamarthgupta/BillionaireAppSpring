package com.rest.service;

import com.rest.exception.BillionaireNotFoundException;
import com.rest.model.Billionaire;

import java.util.List;

public interface BillionaireService {

    List<Billionaire> listAll();
    Billionaire getBillionaire(Long billionaireId) throws BillionaireNotFoundException;
    int createBillionaire(Billionaire billionaires);
    Billionaire updateBillionaire(Billionaire billionaire) throws BillionaireNotFoundException;
    int deleteBillionaire(Long billionaireId);

}