/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import DAO.UsuarioDAO;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author lsilva
 */
@Entity
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.findByName", query = "SELECT u FROM Usuario u WHERE u.name = :name"),
    @NamedQuery(name = "Usuario.findByPassword", query = "SELECT u FROM Usuario u WHERE u.password = :password"),
    @NamedQuery(name = "Usuario.findByType", query = "SELECT u FROM Usuario u WHERE u.type = :type"),
    @NamedQuery(name = "Usuario.findByValidated", query = "SELECT u FROM Usuario u WHERE u.validated = :validated"),
    @NamedQuery(name = "Usuario.findByDateCreation", query = "SELECT u FROM Usuario u WHERE u.dateCreation = :dateCreation"),
    @NamedQuery(name = "Usuario.findByDateValidated", query = "SELECT u FROM Usuario u WHERE u.dateValidated = :dateValidated"),
    @NamedQuery(name = "Usuario.login", query = "SELECT u FROM Usuario u WHERE u.username = :username and u.password = :password")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "type")
    private int type;
    @Basic(optional = false)
    @Column(name = "validated")
    private boolean validated;
    @Basic(optional = false)
    @Column(name = "date_creation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    @Basic(optional = false)
    @Column(name = "date_validated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateValidated;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDonor")
    private Collection<Doacao> doacaoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRecipient")
    private Collection<Doacao> doacaoCollection1;

    public Usuario() {
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    public Usuario(Integer id, String name, String password, int type, boolean validated, Date dateCreation, Date dateValidated) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.type = type;
        this.validated = validated;
        this.dateCreation = dateCreation;
        this.dateValidated = dateValidated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean getValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateValidated() {
        return dateValidated;
    }

    public void setDateValidated(Date dateValidated) {
        this.dateValidated = dateValidated;
    }

    public Collection<Doacao> getDoacaoCollection() {
        return doacaoCollection;
    }

    public void setDoacaoCollection(Collection<Doacao> doacaoCollection) {
        this.doacaoCollection = doacaoCollection;
    }

    public Collection<Doacao> getDoacaoCollection1() {
        return doacaoCollection1;
    }

    public void setDoacaoCollection1(Collection<Doacao> doacaoCollection1) {
        this.doacaoCollection1 = doacaoCollection1;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Usuario[ id=" + id + " ]";
    }

}
