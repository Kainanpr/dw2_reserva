package com.dw2.reserva.persistence.repository;

import com.dw2.reserva.model.Equipment;

import java.util.List;

public interface EquipmentRepository {
    Equipment getById(Integer id);

    List<Equipment> getAll();

    int save(Equipment equipment);

    int update(Equipment equipment);

    int delete(Integer id);
}
