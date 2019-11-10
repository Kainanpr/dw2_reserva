package com.dw2.reserva.persistence.repository;

import com.dw2.reserva.model.Equipment;
import com.dw2.reserva.persistence.jooq.tables.records.EquipmentRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.dw2.reserva.persistence.jooq.tables.Equipment.EQUIPMENT;

@Repository
@Transactional
public class EquipmentJooqRepository implements EquipmentRepository {
    private final DSLContext dslContext;
    private final LaboratoryRepository laboratoryRepository;

    public EquipmentJooqRepository(DSLContext dslContext, LaboratoryRepository laboratoryRepository) {
        this.dslContext = dslContext;
        this.laboratoryRepository = laboratoryRepository;
    }

    @Override
    public Equipment getById(Integer id) {
        final Record record = dslContext.select()
                .from(EQUIPMENT)
                .where(EQUIPMENT.ID.eq(id))
                .fetchOne();

        return record == null ? null : toEquipment(record);
    }

    private Equipment toEquipment(Record record) {
        final EquipmentRecord equipmentRecord = record.into(EquipmentRecord.class);

        return new Equipment.Builder()
                .setId(equipmentRecord.getId())
                .setName(equipmentRecord.getName())
                .setLaboratory(laboratoryRepository.getById(equipmentRecord.getLaboratoryId()))
                .build();
    }

    @Override
    public List<Equipment> getAll() {
        final Result<Record> records = dslContext.select()
                .from(EQUIPMENT)
                .fetch();

        return records.map(record -> toEquipment(record));
    }

    @Override
    public int save(Equipment equipment) {
        return dslContext.insertInto(EQUIPMENT)
                .columns(EQUIPMENT.ID,
                        EQUIPMENT.NAME,
                        EQUIPMENT.LABORATORY_ID)
                .values(null,
                        equipment.getName(),
                        equipment.getLaboratory().getId())
                .returning(EQUIPMENT.ID)
                .fetchOne()
                .getId();
    }

    @Override
    public int update(Equipment equipment) {
        return dslContext.update(EQUIPMENT)
                .set(EQUIPMENT.NAME, equipment.getName())
                .set(EQUIPMENT.LABORATORY_ID, equipment.getLaboratory().getId())
                .where(EQUIPMENT.ID.eq(equipment.getId()))
                .execute();
    }

    @Override
    public int delete(Integer id) {
        return dslContext.deleteFrom(EQUIPMENT)
                .where(EQUIPMENT.ID.eq(id))
                .execute();
    }
}
