/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yosycompany.prograweb.persistencia1;

import com.yosycompany.prograweb.models.Usuarios;
import com.yosycompany.prograweb.persistencia.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author sadam
 */
public class UsuariosJpaController implements Serializable {

    public UsuariosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuarios usuarios) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(usuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuarios usuarios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            usuarios = em.merge(usuarios);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = usuarios.getIdUsuario();
                if (findUsuarios(id) == null) {
                    throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios usuarios;
            try {
                usuarios = em.getReference(Usuarios.class, id);
                usuarios.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.", enfe);
            }
            em.remove(usuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuarios> findUsuariosEntities() {
        return findUsuariosEntities(true, -1, -1);
    }

    public List<Usuarios> findUsuariosEntities(int maxResults, int firstResult) {
        return findUsuariosEntities(false, maxResults, firstResult);
    }

    private List<Usuarios> findUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuarios.class));
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

    public Usuarios findUsuarios(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuarios.class, id);
        } finally {
            em.close();
        }
    }
    
        public Usuarios findUsuario(String nombreUsuario, String pass) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root <Usuarios> root = cq.from(Usuarios.class); 
            cq.select(root);
            Predicate predicateNombreUsuario
              = builder.equal(root.get("nombreUsuario"),nombreUsuario);
            Predicate predicatePassword
              = builder.equal(root.get("password"),pass);
            Predicate predicateFinal   
            = builder.and(predicateNombreUsuario,predicatePassword);
            cq.where(predicateFinal);
            Query q = em.createQuery(cq);
            return (Usuarios) q.getSingleResult();
        } catch(NoResultException e) {
             em.close();
        return null;
    }
    }
        
      public Usuarios findUsuario3(String nombreUsuario) {
    EntityManager em = getEntityManager();
    try {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Usuarios> cq = builder.createQuery(Usuarios.class);
        Root<Usuarios> root = cq.from(Usuarios.class); 
        cq.select(root);

        Predicate predicateNombreUsuario = builder.equal(root.get("nombreUsuario"), nombreUsuario);
        Predicate predicateEstatus = builder.isTrue(root.get("estatus"));

        Predicate predicateFinal = builder.and(predicateNombreUsuario, predicateEstatus);
        cq.where(predicateFinal);

        Query q = em.createQuery(cq);
        return (Usuarios) q.getSingleResult();
    } catch (NoResultException e) {
        return null;
    } finally {
        em.close();
    }
}
  
        
        
public Usuarios findUsuario1(String pass) {
    EntityManager em = getEntityManager();
    try {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Usuarios> cq = builder.createQuery(Usuarios.class);
        Root<Usuarios> root = cq.from(Usuarios.class); 
        cq.select(root);
        Predicate predicatePassword = builder.equal(root.get("password"), pass);
        cq.where(predicatePassword);
        Query q = em.createQuery(cq);
        List<Usuarios> usuarios = q.getResultList();
        
        if (usuarios.size() == 1) {
            return usuarios.get(0);
        } else if (usuarios.size() > 1) {
            throw new NonUniqueResultException("Se encontraron múltiples usuarios con la misma contraseña.");
        } else {
            return null;
        }
    } catch (NoResultException e) {
        return null;
    } finally {
        em.close();
    }
}
public Usuarios findUsuarioByPasswordAndId(String pass, int idUsuario) {
    EntityManager em = getEntityManager();
    try {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Usuarios> cq = builder.createQuery(Usuarios.class);
        Root<Usuarios> root = cq.from(Usuarios.class); 
        cq.select(root);
        Predicate predicatePassword = builder.equal(root.get("password"), pass);
        Predicate predicateIdUsuario = builder.equal(root.get("idUsuario"), idUsuario);
        cq.where(builder.and(predicatePassword, predicateIdUsuario));
        Query q = em.createQuery(cq);
        List<Usuarios> usuarios = q.getResultList();
        
        if (usuarios.size() == 1) {
            return usuarios.get(0);
        } else if (usuarios.size() > 1) {
            throw new NonUniqueResultException("Se encontraron múltiples usuarios con la misma contraseña y idUsuario.");
        } else {
            return null;
        }
    } catch (NoResultException e) {
        return null;
    } finally {
        em.close();
    }
}

public Usuarios findUsuario2(String nombreUsuario, String pass) {
    EntityManager em = getEntityManager();
    try {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Usuarios> cq = builder.createQuery(Usuarios.class);
        Root<Usuarios> root = cq.from(Usuarios.class); 
        cq.select(root);

        Predicate predicateNombreUsuario = builder.equal(root.get("nombreUsuario"), nombreUsuario);
        Predicate predicatePassword = builder.equal(root.get("password"), pass);
        Predicate predicateEstatus = builder.isTrue(root.get("estatus"));

        Predicate predicateFinal = builder.and(predicateNombreUsuario, predicatePassword, predicateEstatus);
        cq.where(predicateFinal);

        Query q = em.createQuery(cq);
        return (Usuarios) q.getSingleResult();
    } catch (NoResultException e) {
        return null;
    } finally {
        em.close();
    }
}

    public int getUsuariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuarios> rt = cq.from(Usuarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
