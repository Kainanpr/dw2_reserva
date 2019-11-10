package com.dw2.reserva.persistence.repository;

import com.dw2.reserva.model.Laboratory;

import java.util.List;

public interface LaboratoryRepository {
    Laboratory getById(Integer id);

    List<Laboratory> getAll();

    int save(Laboratory laboratory);

    int update(Laboratory laboratory);

    int delete(Integer id);
}
