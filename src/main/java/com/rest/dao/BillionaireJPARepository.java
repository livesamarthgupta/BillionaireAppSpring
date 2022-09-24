package com.rest.dao;

import com.rest.model.Billionaire;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BillionaireJPARepository implements BillionaireRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Billionaire> getBillionaires() {
        String sql = "SELECT * FROM billionaires;";
        return entityManager.createNativeQuery(sql).getResultList();
    }

    @Override
    public Billionaire getBillionaire(Long billionaireId) {
        return entityManager.find(Billionaire.class, billionaireId);
    }

    @Override
    public void deleteBillionaire(Long billionaireId) {
        Billionaire billionaire = getBillionaire(billionaireId);
        entityManager.remove(billionaire);
    }

    @Override
    public Billionaire updateBillionaire(Billionaire billionaire) {
        return entityManager.merge(billionaire);
    }

    @Override
    public void createBillionaire(Billionaire billionaire) {
        entityManager.persist(billionaire);
    }
}
