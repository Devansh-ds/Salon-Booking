package com.devansh.repo;

import com.devansh.entity.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SalonRepository extends JpaRepository<Salon, Integer> {

    Optional<Salon> getSalonByOwnerId(Integer ownerId);

    @Query(
            "select s from Salon s where " +
                    "lower(s.city) like lower(concat('%', :keyword, '%')) OR " +
                    "lower(s.name) like lower(concat('%', :keyword, '%')) OR " +
                    "lower(s.address) like lower(concat('%', :keyword, '%'))  "
    )
    List<Salon> searchSalon(@Param("keyword") String keyword);
}
