/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.service.exercise.apiws;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import web.service.exercise.apiws.exceptions.NonexistentEntityException;
import web.service.exercise.apiws.exceptions.PreexistingEntityException;

/**
 *
 * @author user
 */
public class GameJpaController implements Serializable {

    public GameJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("web.service_exercise.apiws_jar_0.0.1-SNAPSHOTPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public GameJpaController() {
    }
    

    public void create(Game game) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(game);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findGame(game.getIdGame()) != null) {
                throw new PreexistingEntityException("Game " + game + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Game game) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            game = em.merge(game);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = game.getIdGame();
                if (findGame(id) == null) {
                    throw new NonexistentEntityException("The game with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Game game;
            try {
                game = em.getReference(Game.class, id);
                game.getIdGame();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The game with id " + id + " no longer exists.", enfe);
            }
            em.remove(game);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Game> findGameEntities() {
        return findGameEntities(true, -1, -1);
    }

    public List<Game> findGameEntities(int maxResults, int firstResult) {
        return findGameEntities(false, maxResults, firstResult);
    }

    private List<Game> findGameEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Game.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Game findGame(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Game.class, id);
        } finally {
            em.close();
        }
    }

    public int getGameCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Game> rt = cq.from(Game.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
