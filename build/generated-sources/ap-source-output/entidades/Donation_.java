package entidades;

import entidades.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-11-28T03:52:47")
@StaticMetamodel(Donation.class)
public class Donation_ { 

    public static volatile SingularAttribute<Donation, Boolean> isAnon;
    public static volatile SingularAttribute<Donation, Date> dateDonation;
    public static volatile SingularAttribute<Donation, Integer> id;
    public static volatile SingularAttribute<Donation, Integer> value;
    public static volatile SingularAttribute<Donation, User> idDonor;
    public static volatile SingularAttribute<Donation, Integer> idRecipient;

}