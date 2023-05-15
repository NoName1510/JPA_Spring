package com.demo.jpa.repository;

import com.demo.jpa.entity.BookEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, Integer> {

    List<BookEntity> findByAuthor(String author);

//    @Query("select b from BookEntity b where b.name like ?1%")
//        //?1 = para 1  ?2 = para 2
//    List<BookEntity> getBookStartWith(String name);

    @Query(value = "Select b from BookEntity b where b.name like ?1%")
    List<BookEntity> getBookStartWith(String name);

    @Query(value = "select * from book where price < ?1 and numberPage >= ?2",nativeQuery = true)
    List<BookEntity> getBookWherePriceLessThanAndNumOfPageGreaterThan(double price, int numberPage);

}
