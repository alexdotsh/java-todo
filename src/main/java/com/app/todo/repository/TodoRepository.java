package com.app.todo.repository;

//import org.jboss.logging.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TodoRepository extends CrudRepository {
    @Query(value = "SELECT sum(amount) from account_transaction atn where atn.account_type = :account_type"/*, nativeQuery = true*/)
    Double findTotalByAccountType(@Param("account_type") String account_type);
}