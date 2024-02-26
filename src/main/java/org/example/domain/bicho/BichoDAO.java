package org.example.domain.bicho;

import org.example.domain.DAO;
import org.example.domain.ObjectUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

public class BichoDAO implements DAO<Bicho> {
    @Override
    public ArrayList<Bicho> getAll() {
        var output = new ArrayList<Bicho>(0);
        EntityManager entityManager = ObjectUtil.getEntityManagerFactory().createEntityManager();
        try{
            TypedQuery<Bicho> query = entityManager.createQuery("select bic from Bicho bic", Bicho.class);
            output = (ArrayList<Bicho>) query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
        return output;
    }

    @Override
    public Bicho get(Integer id) {
        Bicho output = null;
        EntityManager entityManager = ObjectUtil.getEntityManagerFactory().createEntityManager();
        try {                                                       //Acuérdate de que no es 'id', es 'bic.id'.
            TypedQuery<Bicho> query = entityManager.createQuery("select bic from Bicho bic where bic.id= :id", Bicho.class);
            query.setParameter("id", id);
            output = query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
        return output;
    }

    @Override
    public Bicho save(Bicho data) {
        EntityManager entityManager = ObjectUtil.getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(data);
            //'flush()' es importante ya que obliga al búfer a 'soltar' los datos.
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
        return data;
    }

    @Override
    public Bicho update(Bicho data) {
        EntityManager entityManager = ObjectUtil.getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(data);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
        return data;
    }

    @Override
    public void delete(Bicho data) {
        EntityManager entityManager = ObjectUtil.getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();

            if (!entityManager.contains(data)) {
                data = entityManager.merge(data);
            }

            entityManager.remove(data);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void saveAll(List<Bicho> data) {

    }
}
