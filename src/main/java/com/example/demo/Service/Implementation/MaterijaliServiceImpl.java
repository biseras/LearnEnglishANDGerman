package com.example.demo.Service.Implementation;

import com.example.demo.Model.Materijali;
import com.example.demo.Model.exception.NotGoodException;
import com.example.demo.Model.exception.PasswordsDoNotMatchException;
import com.example.demo.Repository.MaterijaliRepository;
import com.example.demo.Service.MaterijaliService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterijaliServiceImpl implements MaterijaliService {
    private final MaterijaliRepository materijaliRepository;

    public MaterijaliServiceImpl(MaterijaliRepository materijaliRepository) {
        this.materijaliRepository = materijaliRepository;
    }

    @Override
    public Optional<Materijali> findById(Long id) {
        return materijaliRepository.findById(id);
    }

    @Override
    public List<Materijali> listAll() {
        return materijaliRepository.findAll();
    }

    @Override
    public Optional<Materijali> save(String naslov, String opis, String link) {
        Materijali materijali=new Materijali(naslov, opis, link);
        return Optional.of(this.materijaliRepository.save(materijali));
    }

    @Override
    public Optional<Materijali> edit(Long id, String naslov, String opis, String link) {
        Materijali materijali=materijaliRepository.findById(id).orElseThrow(()->new PasswordsDoNotMatchException());
        materijali.setNaslov(naslov);
        materijali.setOpis(opis);
        materijali.setLink(link);
        return Optional.of(this.materijaliRepository.save(materijali));
    }

    @Override
    public void deleteById(Long id) {
        materijaliRepository.deleteById(id);
    }
}
