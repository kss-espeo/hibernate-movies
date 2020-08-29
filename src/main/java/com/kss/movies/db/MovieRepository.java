package com.kss.movies.db;

import com.kss.movies.entity.Movie;
import com.kss.movies.entity.Review;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class MovieRepository {
    public void save(Movie movie) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateConnector.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.saveOrUpdate(movie);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Movie read(String title) {
        Movie result = null;

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateConnector.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Query query = session.createQuery("FROM Movie WHERE title=:title");
            query.setParameter("title", title);
            List movies = query.getResultList();
            if (movies.size() > 0) {
                result = (Movie) movies.get(0);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return result;
    }

    public List<Movie> readAll() {
        List<Movie> result = new ArrayList<>();

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateConnector.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            List movies = session.createQuery("FROM Movie").list();
            for (Iterator iterator = movies.iterator(); iterator.hasNext(); ) {
                result.add((Movie) iterator.next());
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return result;
    }

    public void delete(String title) {

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateConnector.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.delete(new Movie(title));

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
