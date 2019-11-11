package com.dw2.reserva.persistence.repository;

import com.dw2.reserva.model.ReserveEquipment;
import com.dw2.reserva.persistence.jooq.tables.records.ReserveEquipmentRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.dw2.reserva.persistence.jooq.tables.ReserveEquipment.RESERVE_EQUIPMENT;

@Repository
@Transactional
public class ReserveEquipmentJooqRepository implements ReserveEquipmentRepository {
    private final DSLContext dslContext;
    private final UserRepository userRepository;
    private final EquipmentRepository equipmentRepository;

    public ReserveEquipmentJooqRepository(DSLContext dslContext, UserRepository userRepository, EquipmentRepository equipmentRepository) {
        this.dslContext = dslContext;
        this.userRepository = userRepository;
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public ReserveEquipment getById(Integer id) {
        final Record record = dslContext.select()
                .from(RESERVE_EQUIPMENT)
                .where(RESERVE_EQUIPMENT.ID.eq(id))
                .fetchOne();

        return record == null ? null : toReserveEquipment(record);
    }

    private ReserveEquipment toReserveEquipment(Record record) {
        final ReserveEquipmentRecord reserveEquipmentRecord = record.into(ReserveEquipmentRecord.class);

        return new ReserveEquipment.Builder()
                .setId(reserveEquipmentRecord.getId())
                .setStartDate(reserveEquipmentRecord.getStartDate())
                .setEndDate(reserveEquipmentRecord.getEndDate())
                .setUser(userRepository.getById(reserveEquipmentRecord.getUserId()))
                .setEquipment(equipmentRepository.getById(reserveEquipmentRecord.getEquipmentId()))
                .build();
    }

    @Override
    public List<ReserveEquipment> getAll() {
        final Result<Record> records = dslContext.select()
                .from(RESERVE_EQUIPMENT)
                .fetch();

        return records.map(record -> toReserveEquipment(record));
    }

    @Override
    public int save(ReserveEquipment reserveEquipment) {
        return dslContext.insertInto(RESERVE_EQUIPMENT)
                .columns(RESERVE_EQUIPMENT.ID,
                        RESERVE_EQUIPMENT.START_DATE,
                        RESERVE_EQUIPMENT.END_DATE,
                        RESERVE_EQUIPMENT.USER_ID,
                        RESERVE_EQUIPMENT.EQUIPMENT_ID)
                .values(null,
                        reserveEquipment.getStartDate(),
                        reserveEquipment.getEndDate(),
                        reserveEquipment.getUser().getId(),
                        reserveEquipment.getEquipment().getId())
                .returning(RESERVE_EQUIPMENT.ID)
                .fetchOne()
                .getId();
    }

    @Override
    public int update(ReserveEquipment reserveEquipment) {
        return dslContext.update(RESERVE_EQUIPMENT)
                .set(RESERVE_EQUIPMENT.START_DATE, reserveEquipment.getStartDate())
                .set(RESERVE_EQUIPMENT.END_DATE, reserveEquipment.getEndDate())
                .set(RESERVE_EQUIPMENT.USER_ID, reserveEquipment.getUser().getId())
                .set(RESERVE_EQUIPMENT.EQUIPMENT_ID, reserveEquipment.getEquipment().getId())
                .where(RESERVE_EQUIPMENT.ID.eq(reserveEquipment.getId()))
                .execute();
    }

    @Override
    public int delete(Integer id) {
        return dslContext.deleteFrom(RESERVE_EQUIPMENT)
                .where(RESERVE_EQUIPMENT.ID.eq(id))
                .execute();
    }
}
