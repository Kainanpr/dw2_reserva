package com.dw2.reserva.persistence.repository;

import com.dw2.reserva.model.Login;
import com.dw2.reserva.persistence.jooq.tables.records.LoginRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static com.dw2.reserva.persistence.jooq.tables.Login.LOGIN;

@Repository
@Transactional
public class LoginJooqRepository implements LoginRepository {
    private final DSLContext dslContext;

    public LoginJooqRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Login isAuthenticated(Login login) {
        final Record record = dslContext.select()
                .from(LOGIN)
                .where(LOGIN.EMAIL.eq(login.getEmail()))
                .and(LOGIN.PASSWORD.eq(login.getPassword()))
                .fetchOne();

        return record == null ? null : toLogin(record);
    }

    private Login toLogin(Record record) {
        final LoginRecord userRecord = record.into(LoginRecord.class);

        return new Login.Builder()
                .setEmail(userRecord.getEmail())
                .setPassword(userRecord.getPassword())
                .build();
    }
}
