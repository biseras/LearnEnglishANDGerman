package com.example.demo.Service;

import com.example.demo.Model.Materijali;

import java.util.List;
import java.util.Optional;

public interface MaterijaliService {
    Optional<Materijali> findById(Long id);
    List<Materijali> listAll();

    Optional<Materijali> save(String naslov, String opis, String link);
    Optional<Materijali> edit(Long id, String naslov, String opis, String link);


    void deleteById(Long id);
}
