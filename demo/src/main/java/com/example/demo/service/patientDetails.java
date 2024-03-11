package com.example.demo.service;


import com.example.demo.dto.Patient;
import com.example.demo.dto.userDetails;
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

      public List<Patient> fetchPatientDetails(String userId) {
            Configuration cfg = new Configuration();
            cfg.configure();
            SessionFactory sf = cfg.buildSessionFactory();
            Session s = sf.openSession();
            Transaction ts = s.beginTransaction();
            System.out.println(userId);
            Query<?> query = s.createQuery("SELECT * FROM userDetails WHERE userId = :userId");
            query.setParameter("userId", userId);
            List<Patient>  key = (List<Patient>) query.uniqueResult();
            ts.commit();
            s.close();
            return key;
      }
}
