package org.example.domain.tuberculo;


import org.example.domain.DAO;
import org.example.domain.ObjectUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

//La implementación del DAO genérico debe ser del tipo de objeto que del que se va a crear el DAO.
public class TuberculoDAO implements DAO<Tuberculo> {

    /**
     * Método para obtener todos los Tubérculos que existan en la Base de Datos.
     * @return Devuelve todos los Tubérculos de la Base de Datos.
     */
    @Override
    public ArrayList<Tuberculo> getAll() {
        var output = new ArrayList<Tuberculo>(0);
        EntityManager entityManager = ObjectUtil.getEntityManagerFactory().createEntityManager();
        try{
            //Fíjate muy bien dónde se cierran las comillas 'Tuberculo.class' NO va entre comillas.
            TypedQuery<Tuberculo> query = entityManager.createQuery("select tub from Tuberculo tub", Tuberculo.class);
            output = (ArrayList<Tuberculo>) query.getResultList();
        } finally {
            entityManager.close();
        }
        return output;
    }

    /**
     * Método para obtener un Tuberculo por su id.
     * @param id Id que se usará para obtener el Tubérculo.
     * @return El Tubérculo que con el mismo id.
     */
    @Override
    public Tuberculo get(Integer id) {
        Tuberculo output = null;
        EntityManager entityManager = ObjectUtil.getEntityManagerFactory().createEntityManager();
        try{
            //Fíjate muy bien en la estructura, sobre t0d0 a la hora de poner parámetros: '= :'.
            TypedQuery<Tuberculo> query = entityManager.createQuery("select tub from Tuberculo tub where tub.id = :id", Tuberculo.class);
            //De esta forma estableces el parámetro que se utilizará arriba. En este caso 'id'.
            query.setParameter("id", id);
            output = query.getSingleResult();

            /*
            'getSingleResult()' funciona, si no llegara a funcionar, puedes hacer esto con un
            'getResultList()'.
            if (result.size() > 0) {
                output = result.get(0);
            }
             */
        } finally {
            entityManager.close();
        }
        return output;
    }

    /**
     * Método para guardar los tubérculos en la Base de Datos.
     * @param data Tubérculo a guardar
     * @return Devuelve la info. del tubérculo que se guarda en la Base de Datos.
     */
    @Override
    public Tuberculo save(Tuberculo data) {
        EntityManager entityManager = ObjectUtil.getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(data);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
        return data;
    }

    /**
     * Método para actualizar un Tubérculo en la Base de Datos.
     * @param data Tubérculo que quiere actualizarse en la Base de Datos.
     * @return La info. del Tubérculo que se actualiza en la Base de Datos.
     */
    @Override
    public Tuberculo update(Tuberculo data) {
        EntityManager entityManager = ObjectUtil.getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            data = entityManager.merge(data);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
        return data;
    }

    /**
     * Método que elimina un tubérculo de la Base de Datos.
     * @param data El Tubérculo que quiere eliminarse de la Base de Datos.
     */
    @Override
    public void delete(Tuberculo data) {
        EntityManager entityManager = ObjectUtil.getEntityManagerFactory().createEntityManager();
        try{
            entityManager.getTransaction().begin();
            /* Este fragmento de código es totalmente necesario.
            * Si el EntityManager no contiene datos, va a hacer un merge para actualizarse.
            * */
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

    /**
     * Guarda una lista de Tubérculos en la Base de Datos.
     * @param data La lista de Tubérculos a guardar en la Base de Datos.
     */
    @Override
    public void saveAll(List<Tuberculo> data) {
        EntityManager entityManager = ObjectUtil.getEntityManagerFactory().createEntityManager();
        try{
            entityManager.getTransaction().begin();
            for (Tuberculo tub: data){
                entityManager.persist(tub);
            }
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}
