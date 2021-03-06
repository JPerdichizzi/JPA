/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Libreria.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;

/**
 *
 * @author joaqu
 */
@Entity
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    String titulo;
    Integer anio;
    Integer ejemplares;
    Integer ejemplarePrestados;
    Integer ejemplaresRestantes;
    boolean alta;
    @ManyToOne
    Author autor;
    @OneToOne
    Publisher editorial;

    public Book() {
    }

    public Book(Long id, String tituto, Integer anio, Integer ejemplares, Integer ejemplarePrestados, Integer ejemplaresRestantes, boolean alta, Author autor, Publisher editorial) {
        this.id = id;
        this.titulo = titulo;
        this.anio = anio;
        this.ejemplares = ejemplares;
        this.ejemplarePrestados = ejemplarePrestados;
        this.ejemplaresRestantes = ejemplaresRestantes;
        this.alta = alta;
        this.autor = autor;
        this.editorial = editorial;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(Integer ejemplares) {
        this.ejemplares = ejemplares;
    }

    public Integer getEjemplarePrestados() {
        return ejemplarePrestados;
    }

    public void setEjemplarePrestados(Integer ejemplarePrestados) {
        this.ejemplarePrestados = ejemplarePrestados;
    }

    public Integer getEjemplaresRestantes() {
        return ejemplaresRestantes;
    }

    public void setEjemplaresRestantes(Integer ejemplaresRestantes) {
        this.ejemplaresRestantes = ejemplaresRestantes;
    }

    public boolean isAlta() {
        return alta;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    public Author getAutor() {
        return autor;
    }

    public void setAutor(Author autor) {
        this.autor = autor;
    }

    public Publisher getEditorial() {
        return editorial;
    }

    public void setEditorial(Publisher editorial) {
        this.editorial = editorial;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String disponible() {
        String disponible = "disponible";
        if (alta == false) {
            disponible = "libro en desuso pendiente de eliminar";

        }
        return disponible;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", t??tulo=" + titulo + ", a??o de publicaci??n=" + anio + ", ejemplares totales=" + ejemplares + ", ejemplare prestados=" + ejemplarePrestados + ", ejemplares disponibles en stock" + ejemplaresRestantes + ", alta=" + disponible() + ", Autor=" + autor + ", Editorial=" + editorial + '}';
    }

}
