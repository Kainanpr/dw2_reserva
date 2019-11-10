package com.dw2.reserva.persistence.repository;

import com.dw2.reserva.model.Laboratory;
import com.dw2.reserva.persistence.jooq.tables.records.LaboratoryRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.dw2.reserva.persistence.jooq.tables.Laboratory.LABORATORY;

@Repository
@Transactional
public class LaboratoryJooqRepository implements LaboratoryRepository {
    private final DSLContext dslContext;

    public LaboratoryJooqRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Laboratory getById(Integer id) {
        final Record record = dslContext.select()
                .from(LABORATORY)
                .where(LABORATORY.ID.eq(id))
                .fetchOne();

        return record == null ? null : toLaboratory(record);
    }

    private Laboratory toLaboratory(Record record) {
        final LaboratoryRecord laboratoryRecord = record.into(LaboratoryRecord.class);

        return new Laboratory.Builder()
                .setId(laboratoryRecord.getId())
                .setName(laboratoryRecord.getName())
                .build();
    }

    @Override
    public List<Laboratory> getAll() {
        final Result<Record> records = dslContext.select()
                .from(LABORATORY)
                .fetch();

        return records.map(record -> toLaboratory(record));
    }

    @Override
    public int save(Laboratory laboratory) {
        return dslContext.insertInto(LABORATORY)
                .columns(LABORATORY.ID,
                        LABORATORY.NAME)
                .values(null,
                        laboratory.getName())
                .returning(LABORATORY.ID)
                .fetchOne()
                .getId();
    }

    @Override
    public int update(Laboratory laboratory) {
        return dslContext.update(LABORATORY)
                .set(LABORATORY.NAME, laboratory.getName())
                .where(LABORATORY.ID.eq(laboratory.getId()))
                .execute();
    }

    @Override
    public int delete(Integer id) {
        return dslContext.deleteFrom(LABORATORY)
                .where(LABORATORY.ID.eq(id))
                .execute();
    }
}
