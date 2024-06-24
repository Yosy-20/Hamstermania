/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yosycompany.prograweb.persistencia1;

import com.yosycompany.prograweb.models.Publicaciones;
import com.yosycompany.prograweb.models.Usuarios;
import com.yosycompany.prograweb.persistencia1.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author sadam
 */
public class PublicacionesJpaController implements Serializable {

    public PublicacionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Publicaciones publicaciones) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(publicaciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Publicaciones publicaciones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            publicaciones = em.merge(publicaciones);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = publicaciones.getIdPublicacion();
                if (findPublicaciones(id) == null) {
                    throw new NonexistentEntityException("The publicaciones with id " + id + " no longer exists.");
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
            Publicaciones publicaciones;
            try {
                publicaciones = em.getReference(Publicaciones.class, id);
                publicaciones.getIdPublicacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The publicaciones with id " + id + " no longer exists.", enfe);
            }
            em.remove(publicaciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Publicaciones> findPublicacionesEntities() {
        return findPublicacionesEntities(true, -1, -1);
    }

    public List<Publicaciones> findPublicacionesEntities(int maxResults, int firstResult) {
        return findPublicacionesEntities(false, maxResults, firstResult);
    }

   private List<Publicaciones> findPublicacionesEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Publicaciones> cq = builder.createQuery(Publicaciones.class);
        Root<Publicaciones> root = cq.from(Publicaciones.class);

        cq.select(root);

        // Join con la entidad Usuarios
        Join<Publicaciones, Usuarios> usuarioJoin = root.join("usuario");

        // Crear predicados para las condiciones de filtrado
        Predicate predicateEstatusPublicacion = builder.equal(root.get("estatus"), true);
        Predicate predicateEstatusUsuario = builder.equal(usuarioJoin.get("estatus"), true);

        // Combinar los predicados usando AND
        Predicate finalPredicate = builder.and(predicateEstatusPublicacion, predicateEstatusUsuario);

        cq.where(finalPredicate);

        // Ordenar por fechaCreacion en orden descendente
        cq.orderBy(builder.desc(root.get("fechaCreacion")));

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

    
 

public List<Publicaciones> findPublicacionesEntities(int idUsuario) {
    EntityManager em = getEntityManager();
    try {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Publicaciones> cq = builder.createQuery(Publicaciones.class);
        Root<Publicaciones> root = cq.from(Publicaciones.class);
        cq.select(root);
        Join<Publicaciones, Usuarios> usuarioJoin = root.join("usuario");
        Predicate predicateIdUsuario = builder.equal(usuarioJoin.get("idUsuario"), idUsuario);
        cq.where(predicateIdUsuario);
        
        cq.orderBy(builder.desc(root.get("fechaCreacion")));

        Query q = em.createQuery(cq);
        return q.getResultList();
    } catch (NoResultException e) {
        return null;
    } finally {
        if (em != null) {
            em.close();
        }
    }
}

public List<Publicaciones> findPublicacionesEntities(String categoria) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Publicaciones> cq = builder.createQuery(Publicaciones.class);
            Root<Publicaciones> root = cq.from(Publicaciones.class);
            cq.select(root);
            Predicate predicateCategoria = builder.equal(root.get("categoria"), categoria);
            cq.where(predicateCategoria);
            
            cq.orderBy(builder.desc(root.get("fechaCreacion")));

            Query q = em.createQuery(cq);
            return q.getResultList();
        } catch (NoResultException e) {
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


public Publicaciones findPublicaciones(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Publicaciones.class, id);
        } finally {
            em.close();
        }
    }

    
    
    
 public List<Publicaciones> findPublicaciones(String texto) {
    EntityManager em = getEntityManager();
    try {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        
        CriteriaQuery<Publicaciones> cq = builder.createQuery(Publicaciones.class);
        
        Root<Publicaciones> root = cq.from(Publicaciones.class); 
        
        cq.select(root);
        
      
        Join<Publicaciones, Usuarios> usuarioJoin = root.join("usuario");

        
        Predicate predicateLikeTitulo = builder.like(root.get("Titulo"), "%" + texto + "%");
        Predicate predicateLikeDescripcion = builder.like(root.get("Descripcion"), "%" + texto + "%");
        Predicate predicateOr = builder.or(predicateLikeTitulo, predicateLikeDescripcion);

       
        Predicate predicateEstatusPublicacion = builder.equal(root.get("estatus"), true);

        
        Predicate predicateEstatusUsuario = builder.equal(usuarioJoin.get("estatus"), true);

       
        Predicate predicateFinal = builder.and(predicateOr, predicateEstatusPublicacion, predicateEstatusUsuario);
        
        cq.where(predicateFinal);
        
      
        cq.orderBy(builder.desc(root.get("fechaCreacion")));
        
        Query q = em.createQuery(cq);
        
        return q.getResultList();
    } finally {
        em.close();
    }
}

    
    
public List<Publicaciones> findPublicacionesEntities(Integer categoriaId, Date fecha) {
    EntityManager em = getEntityManager();
    try {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Publicaciones> cq = builder.createQuery(Publicaciones.class);
        Root<Publicaciones> root = cq.from(Publicaciones.class);
        cq.select(root);

     
        Join<Publicaciones, Usuarios> usuarioJoin = root.join("usuario");

        
        List<Predicate> predicates = new ArrayList<>();

        // Agregar predicado para categoría si no es nula
        if (categoriaId != null) {
            Predicate predicateCategoria = builder.equal(root.get("categoria").get("idCategoria"), categoriaId);
            predicates.add(predicateCategoria);
        }

        // Agregar predicado para fecha si no es nula
        if (fecha != null) {
            // Truncar fecha en base de datos a día sin hora
            Predicate predicateFecha = builder.equal(
                builder.function("DATE", Date.class, root.get("fechaCreacion")),
                fecha
            );
            predicates.add(predicateFecha);
        }

      
        Predicate predicateEstatusPublicacion = builder.equal(root.get("estatus"), true);
        predicates.add(predicateEstatusPublicacion);


        Predicate predicateEstatusUsuario = builder.equal(usuarioJoin.get("estatus"), true);
        predicates.add(predicateEstatusUsuario);

       
        if (!predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[0]));
        }

       
        cq.orderBy(builder.desc(root.get("fechaCreacion")));

        Query q = em.createQuery(cq);
        return q.getResultList();
    } catch (NoResultException e) {
        return null;
    } finally {
        if (em != null) {
            em.close();
        }
    }
}


    
    public int getPublicacionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Publicaciones> rt = cq.from(Publicaciones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
