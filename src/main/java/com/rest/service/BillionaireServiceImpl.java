package com.rest.service;

import com.rest.dao.BillionaireDao;
import com.rest.exception.BillionaireNotFoundException;
import com.rest.model.Billionaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BillionaireServiceImpl implements BillionaireService{

    @Autowired
    private BillionaireDao billionaireDao;

    @Override
    public List<Billionaire> listAll() {
        List<Billionaire> billionaires = billionaireDao.getBillionaires();
        return billionaires.size() > 0 ? billionaires : Collections.emptyList();
    }

    @Override
    public Billionaire getBillionaire(Long billionaireId) throws BillionaireNotFoundException {
        Billionaire billionaire = billionaireDao.getBillionaire(billionaireId);
        Optional<Billionaire> optionalBillionaire = Optional.ofNullable(billionaire);
        return optionalBillionaire.orElseThrow(BillionaireNotFoundException::new);
    }

    @Override
    public int createBillionaire(Billionaire billionaire) {
        return billionaireDao.createBillionaire(billionaire);
    }

    @Override
    public Billionaire updateBillionaire(Billionaire billionaire) throws BillionaireNotFoundException {
        Billionaire updatedBillionaire = billionaireDao.updateBillionaire(billionaire);
        Optional<Billionaire> optionalBillionaire = Optional.ofNullable(updatedBillionaire);
        return optionalBillionaire.orElseThrow(BillionaireNotFoundException::new);
    }

    @Override
    public int deleteBillionaire(Long billionaireId) {
        return billionaireDao.deleteBillionaire(billionaireId);
    }
}
