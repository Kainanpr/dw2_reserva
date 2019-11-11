package com.dw2.reserva.persistence.repository;

import com.dw2.reserva.model.ReserveEquipment;

import java.util.List;

public interface ReserveEquipmentRepository {
    ReserveEquipment getById(Integer id);

    List<ReserveEquipment> getAll();

    int save(ReserveEquipment reserveEquipment);

    int update(ReserveEquipment reserveEquipment);

    int delete(Integer id);
}
