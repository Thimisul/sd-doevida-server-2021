/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author lsilva
 */
@Entity
@Table(name = "doacao")
@NamedQueries({
    @NamedQuery(name = "Doacao.findAll", query = "SELECT d FROM Doacao d"),
    @NamedQuery(name = "Doacao.findById", query = "SELECT d FROM Doacao d WHERE d.id = :id"),
    @NamedQuery(name = "Doacao.findByValue", query = "SELECT d FROM Doacao d WHERE d.value = :value"),
    @NamedQuery(name = "Doacao.findByDateDonation", query = "SELECT d FROM Doacao d WHERE d.dateDonation = :dateDonation")})
public class Doacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "value")
    private float value;
    @Basic(optional = false)
    @Column(name = "date_donation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDonation;
    @JoinColumn(name = "id_donor", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario idDonor;
    @JoinColumn(name = "id_recipient", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario idRecipient;

    public Doacao() {
    }

    public Doacao(Integer id) {
        this.id = id;
    }

    public Doacao(Integer id, float value, Date dateDonation) {
        this.id = id;
        this.value = value;
        this.dateDonation = dateDonation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Date getDateDonation() {
        return dateDonation;
    }

    public void setDateDonation(Date dateDonation) {
        this.dateDonation = dateDonation;
    }

    public Usuario getIdDonor() {
        return idDonor;
    }

    public void setIdDonor(Usuario idDonor) {
        this.idDonor = idDonor;
    }

    public Usuario getIdRecipient() {
        return idRecipient;
    }

    public void setIdRecipient(Usuario idRecipient) {
        this.idRecipient = idRecipient;
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
        if (!(object instanceof Doacao)) {
            return false;
        }
        Doacao other = (Doacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Doacao[ id=" + id + " ]";
    }
    
}
