package br.com.fiap.abstracao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.DayOfWeek;
import java.time.LocalDate;

public abstract class Repository {
    public static final EntityManagerFactory factory = Persistence.createEntityManagerFactory(
            LocalDate.now().getDayOfWeek() != DayOfWeek.WEDNESDAY ? "maria-db" : "oracle-fiap"
    );
    public static final EntityManager manager = factory.createEntityManager();
}
