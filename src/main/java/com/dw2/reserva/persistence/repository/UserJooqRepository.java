package com.dw2.reserva.persistence.repository;

import com.dw2.reserva.model.TypeEnum;
import com.dw2.reserva.model.User;
import com.dw2.reserva.persistence.jooq.tables.records.UserRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.dw2.reserva.persistence.jooq.tables.User.USER;

@Repository
@Transactional
public class UserJooqRepository implements UserRepository {
    private final DSLContext dslContext;

    public UserJooqRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public User getById(Integer id) {
        final Record record = dslContext.select()
                .from(USER)
                .where(USER.ID.eq(id))
                .fetchOne();

        return record == null ? null : toUser(record);
    }

    private User toUser(Record record) {
        final UserRecord userRecord = record.into(UserRecord.class);

        return new User.Builder()
                .setId(userRecord.getId())
                .setName(userRecord.getName())
                .setEmail(userRecord.getEmail())
                .setCpf(userRecord.getCpf())
                .setType(TypeEnum.valueOf(userRecord.getType()))
                .build();
    }

    @Override
    public List<User> getAll() {
        final Result<Record> records = dslContext.select()
                .from(USER)
                .fetch();

        return records.map(record -> toUser(record));
    }

    @Override
    public List<User> getAllTeachers() {
        final Result<Record> records = dslContext.select()
                .from(USER)
                .where(USER.TYPE.eq(TypeEnum.TEACHER.name()))
                .fetch();

        return records.map(record -> toUser(record));
    }

    @Override
    public int save(User user) {
        return dslContext.insertInto(USER)
                .columns(USER.ID,
                        USER.NAME,
                        USER.EMAIL,
                        USER.CPF,
                        USER.TYPE)
                .values(null,
                        user.getName(),
                        user.getEmail(),
                        user.getCpf(),
                        user.getType().toString())
                .returning(USER.ID)
                .fetchOne()
                .getId();
    }

    @Override
    public int update(User user) {
        return dslContext.update(USER)
                .set(USER.NAME, user.getName())
                .set(USER.EMAIL, user.getEmail())
                .set(USER.CPF, user.getCpf())
                .set(USER.TYPE, user.getType().toString())
                .where(USER.ID.eq(user.getId()))
                .execute();
    }

    @Override
    public int delete(Integer id) {
        return dslContext.deleteFrom(USER)
                .where(USER.ID.eq(id))
                .execute();
    }
}
