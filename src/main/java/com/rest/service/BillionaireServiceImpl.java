package com.rest.service;

import com.rest.dao.BillionaireJPARepository;
import com.rest.exception.BillionaireNotFoundException;
import com.rest.model.Billionaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BillionaireServiceImpl implements BillionaireService {

    @Autowired
    private BillionaireJPARepository billionaireRepository;

    @Override
    public List<Billionaire> listAll() {
        List<Billionaire> billionaires = billionaireRepository.findAll();
        return !billionaires.isEmpty() ? billionaires : Collections.emptyList();
    }

    @Override
    public Billionaire getBillionaire(Long billionaireId) throws BillionaireNotFoundException {
        Billionaire billionaire = billionaireRepository.findOne(billionaireId);
        Optional<Billionaire> optionalBillionaire = Optional.ofNullable(billionaire);
        return optionalBillionaire.orElseThrow(BillionaireNotFoundException::new);
    }

    @Override
    public void createBillionaire(Billionaire billionaire) {
        billionaireRepository.save(billionaire);
    }

    @Override
    public Billionaire updateBillionaire(Billionaire billionaire) throws BillionaireNotFoundException {
        if(Objects.isNull(getBillionaire(billionaire.getId()))) {
            throw new BillionaireNotFoundException();
        }
        return billionaireRepository.save(billionaire);
    }

    @Override
    public void deleteBillionaire(Long billionaireId) {
        billionaireRepository.delete(billionaireId);
    }
}
