package com.pfecigma.banking.repositories;

import com.pfecigma.banking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository <User, Integer>{

    //select * from user where  firstname='younes'
    List<User> findAllByFirstname(String firstname);

    // select * from User where  firstname  like 'younes '

    List<User> findAllByFirstnameContaining(String firstname);

    // select * from User where  firstname   ilike 'younes ' and ignore case
    List<User> findAllByFirstnameContainingIgnoreCase(String firstname);



    // select * from User u inner join account a on u.id=a.id_u3ser and a.iban='DE33'
    List<User> findAllByAccountIban(String iban);


    // select * from user  where first name = ali and email = 'yyyg@gmai.com   '
    User findByFirstnameContainingIgnoreCaseAndEmail(String firstname,String email);


    @Query("from User where firstname= :fn ")
    List<User> SearchByFirstname( @Param("fn") String firstname);

    @Query("from User where firstname= '% :firstname% ' ")
    List<User> SearchByFirstnameContaining(  String firstname);


    @Query("from User u inner join Account a on u.id=a.id.user where a.iban= :iban ")
    List<User> searchByIban(String iban);

}
