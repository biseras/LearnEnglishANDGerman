package com.example.demo.Repository;

import com.example.demo.Model.Materijali;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterijaliRepository extends JpaRepository<Materijali, Long> {
}
