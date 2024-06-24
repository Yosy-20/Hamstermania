package com.yosycompany.prograweb.models;

import com.yosycompany.prograweb.models.Categorias;
import com.yosycompany.prograweb.models.Usuarios;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-06-10T22:56:30", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Publicaciones.class)
public class Publicaciones_ { 

    public static volatile SingularAttribute<Publicaciones, Boolean> estatus;
    public static volatile SingularAttribute<Publicaciones, Categorias> categoria;
    public static volatile SingularAttribute<Publicaciones, Date> fechaCreacion;
    public static volatile SingularAttribute<Publicaciones, Usuarios> usuario;
    public static volatile SingularAttribute<Publicaciones, String> Descripcion;
    public static volatile SingularAttribute<Publicaciones, String> Titulo;
    public static volatile SingularAttribute<Publicaciones, Date> fechaMovimiento;
    public static volatile SingularAttribute<Publicaciones, Integer> idPublicacion;

}