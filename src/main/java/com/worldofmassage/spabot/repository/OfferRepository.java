package com.worldofmassage.spabot.repository;

import com.worldofmassage.spabot.entity.Offer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OfferRepository extends CrudRepository<Offer, Integer> {
    List<Offer> findByOrderByIdAsc();
    Offer findById(int id);
    Offer findByTitle(String title);
}
