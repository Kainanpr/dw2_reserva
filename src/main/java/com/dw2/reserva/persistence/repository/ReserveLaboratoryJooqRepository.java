package com.dw2.reserva.persistence.repository;

import com.dw2.reserva.model.ReserveLaboratory;
import com.dw2.reserva.persistence.jooq.tables.records.ReserveLaboratoryRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.dw2.reserva.persistence.jooq.tables.ReserveLaboratory.RESERVE_LABORATORY;

@Repository
@Transactional
public class ReserveLaboratoryJooqRepository implements ReserveLaboratoryRepository {
    private final DSLContext dslContext;
    private final UserRepository userRepository;
    private final LaboratoryRepository laboratoryRepository;

    public ReserveLaboratoryJooqRepository(DSLContext dslContext, UserRepository userRepository, LaboratoryRepository laboratoryRepository) {
        this.dslContext = dslContext;
        this.userRepository = userRepository;
        this.laboratoryRepository = laboratoryRepository;
    }

    @Override
    public ReserveLaboratory getById(Integer id) {
        final Record record = dslContext.select()
                .from(RESERVE_LABORATORY)
                .where(RESERVE_LABORATORY.ID.eq(id))
                .fetchOne();

        return record == null ? null : toReserveLaboratory(record);
    }

    private ReserveLaboratory toReserveLaboratory(Record record) {
        final ReserveLaboratoryRecord reserveLaboratoryRecord = record.into(ReserveLaboratoryRecord.class);

        return new ReserveLaboratory.Builder()
                .setId(reserveLaboratoryRecord.getId())
                .setStartDate(reserveLaboratoryRecord.getStartDate())
                .setEndDate(reserveLaboratoryRecord.getEndDate())
                .setUser(userRepository.getById(reserveLaboratoryRecord.getUserId()))
                .setLaboratory(laboratoryRepository.getById(reserveLaboratoryRecord.getLaboratoryId()))
                .build();
    }

    @Override
    public List<ReserveLaboratory> getAll() {
        final Result<Record> records = dslContext.select()
                .from(RESERVE_LABORATORY)
                .fetch();

        return records.map(record -> toReserveLaboratory(record));
    }

    @Override
    public int save(ReserveLaboratory reserveLaboratory) {
        return dslContext.insertInto(RESERVE_LABORATORY)
                .columns(RESERVE_LABORATORY.ID,
                        RESERVE_LABORATORY.START_DATE,
                        RESERVE_LABORATORY.END_DATE,
                        RESERVE_LABORATORY.USER_ID,
                        RESERVE_LABORATORY.LABORATORY_ID)
                .values(null,
                        reserveLaboratory.getStartDate(),
                        reserveLaboratory.getEndDate(),
                        reserveLaboratory.getUser().getId(),
                        reserveLaboratory.getLaboratory().getId())
                .returning(RESERVE_LABORATORY.ID)
                .fetchOne()
                .getId();
    }

    @Override
    public int update(ReserveLaboratory reserveLaboratory) {
        return dslContext.update(RESERVE_LABORATORY)
                .set(RESERVE_LABORATORY.START_DATE, reserveLaboratory.getStartDate())
                .set(RESERVE_LABORATORY.END_DATE, reserveLaboratory.getEndDate())
                .set(RESERVE_LABORATORY.USER_ID, reserveLaboratory.getUser().getId())
                .set(RESERVE_LABORATORY.LABORATORY_ID, reserveLaboratory.getLaboratory().getId())
                .where(RESERVE_LABORATORY.ID.eq(reserveLaboratory.getId()))
                .execute();
    }

    @Override
    public int delete(Integer id) {
        return dslContext.deleteFrom(RESERVE_LABORATORY)
                .where(RESERVE_LABORATORY.ID.eq(id))
                .execute();
    }
}
