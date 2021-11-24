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
@Table(name = "donation")
@NamedQueries({
    @NamedQuery(name = "Donation.findAll", query = "SELECT d FROM Donation d"),
    @NamedQuery(name = "Donation.findById", query = "SELECT d FROM Donation d WHERE d.id = :id"),
    @NamedQuery(name = "Donation.findByIdRecipient", query = "SELECT d FROM Donation d WHERE d.idRecipient = :idRecipient"),
    @NamedQuery(name = "Donation.findByDateDonation", query = "SELECT d FROM Donation d WHERE d.dateDonation = :dateDonation"),
    @NamedQuery(name = "Donation.findByIsAnon", query = "SELECT d FROM Donation d WHERE d.isAnon = :isAnon"),
    @NamedQuery(name = "Donation.findByValue", query = "SELECT d FROM Donation d WHERE d.value = :value")})
public class Donation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "id_recipient")
    private int idRecipient;
    @Basic(optional = false)
    @Column(name = "date_donation")
    @Temporal(TemporalType.DATE)
    private Date dateDonation;
    @Basic(optional = false)
    @Column(name = "is_anon")
    private boolean isAnon;
    @Basic(optional = false)
    @Column(name = "value")
    private int value;
    @JoinColumn(name = "id_donor", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User idDonor;

    public Donation() {
    }

    public Donation(Integer id) {
        this.id = id;
    }

    public Donation(Integer id, int idRecipient, Date dateDonation, boolean isAnon, int value) {
        this.id = id;
        this.idRecipient = idRecipient;
        this.dateDonation = dateDonation;
        this.isAnon = isAnon;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdRecipient() {
        return idRecipient;
    }

    public void setIdRecipient(int idRecipient) {
        this.idRecipient = idRecipient;
    }

    public Date getDateDonation() {
        return dateDonation;
    }

    public void setDateDonation(Date dateDonation) {
        this.dateDonation = dateDonation;
    }

    public boolean getIsAnon() {
        return isAnon;
    }

    public void setIsAnon(boolean isAnon) {
        this.isAnon = isAnon;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public User getIdDonor() {
        return idDonor;
    }

    public void setIdDonor(User idDonor) {
        this.idDonor = idDonor;
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
        if (!(object instanceof Donation)) {
            return false;
        }
        Donation other = (Donation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Donation[ id=" + id + " ]";
    }
    
}
