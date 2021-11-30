package entidades;

import entidades.Donation;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-11-30T17:54:51")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, Integer> recepValidated;
    public static volatile CollectionAttribute<User, Donation> donationCollection;
    public static volatile SingularAttribute<User, String> city;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, Integer> userType;
    public static volatile SingularAttribute<User, String> federativeUnit;
    public static volatile SingularAttribute<User, String> username;

}