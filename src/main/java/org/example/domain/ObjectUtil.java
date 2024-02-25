package org.example.domain;

import lombok.Getter;
import lombok.extern.java.Log;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//Importante crear esta clase para ObjectDB.
@Log
public class ObjectUtil {

    @Getter
    private final static EntityManagerFactory entityManagerFactory;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("data.odb");
    }
}
