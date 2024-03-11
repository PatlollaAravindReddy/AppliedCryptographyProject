package com.example.demo.service;


import com.example.demo.dto.Patient;
import com.example.demo.dto.userDetails;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.List;

@Service
public class patientDetails {

      public void storingPatientDetails(Patient patient) {
            Configuration cfg= new Configuration();
            cfg.configure();
            SessionFactory sf= cfg.buildSessionFactory();
            Session s= sf.openSession();
            Transaction ts= s.beginTransaction();
            System.out.println(patient);
            s.save(patient);
            userDetails userDetails = new userDetails();
            userDetails.setUserId(patient.getUserId());
            userDetails.setUserKey(patient.getKey());
            s.save(userDetails);
            ts.commit();
            s.close();
      }

      public String fetchKeyDetails(String userId) {
            Configuration cfg = new Configuration();
            cfg.configure();
            SessionFactory sf = cfg.buildSessionFactory();
            Session s = sf.openSession();
            Transaction ts = s.beginTransaction();
            System.out.println(userId);
            Query<?> query = s.createQuery("SELECT userKey FROM userDetails WHERE userId = :userId");
            query.setParameter("userId", userId);
            String key = (String) query.uniqueResult();
            ts.commit();
            s.close();
            return key;
      }

      public Patient fetchPatientDetails(String userId) {
            Configuration cfg = new Configuration();
            cfg.configure();
            SessionFactory sf = cfg.buildSessionFactory();
            Session s = sf.openSession();
            Transaction ts = s.beginTransaction();
            System.out.println(userId);
            CriteriaBuilder builder = s.getCriteriaBuilder();
            CriteriaQuery<Patient> criteriaQuery = builder.createQuery(Patient.class);
            Root<Patient> root = criteriaQuery.from(Patient.class);
            criteriaQuery.select(root)
                    .where(builder.equal(root.get("userId"), userId));
            List<Patient> patients = s.createQuery(criteriaQuery).getResultList();
            ts.commit();
            s.close();
            return patients.get(0);
      }
}
