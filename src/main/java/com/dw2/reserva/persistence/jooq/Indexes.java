/*
 * This file is generated by jOOQ.
 */
package com.dw2.reserva.persistence.jooq;


import com.dw2.reserva.persistence.jooq.tables.Equipment;
import com.dw2.reserva.persistence.jooq.tables.Laboratory;
import com.dw2.reserva.persistence.jooq.tables.Login;
import com.dw2.reserva.persistence.jooq.tables.ReserveEquipment;
import com.dw2.reserva.persistence.jooq.tables.ReserveLaboratory;
import com.dw2.reserva.persistence.jooq.tables.User;

import javax.annotation.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code></code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index EQUIPMENT_EQUIPMENT_FK_LABORATORY = Indexes0.EQUIPMENT_EQUIPMENT_FK_LABORATORY;
    public static final Index EQUIPMENT_PRIMARY = Indexes0.EQUIPMENT_PRIMARY;
    public static final Index LABORATORY_PRIMARY = Indexes0.LABORATORY_PRIMARY;
    public static final Index LOGIN_USER_UK_EMAIL = Indexes0.LOGIN_USER_UK_EMAIL;
    public static final Index RESERVE_EQUIPMENT_PRIMARY = Indexes0.RESERVE_EQUIPMENT_PRIMARY;
    public static final Index RESERVE_EQUIPMENT_RESERVE_EQUIPMENT_FK_EQUIPMENT = Indexes0.RESERVE_EQUIPMENT_RESERVE_EQUIPMENT_FK_EQUIPMENT;
    public static final Index RESERVE_EQUIPMENT_RESERVE_EQUIPMENT_FK_USER = Indexes0.RESERVE_EQUIPMENT_RESERVE_EQUIPMENT_FK_USER;
    public static final Index RESERVE_LABORATORY_PRIMARY = Indexes0.RESERVE_LABORATORY_PRIMARY;
    public static final Index RESERVE_LABORATORY_RESERVE_LABORATORY_FK_LABORATORY = Indexes0.RESERVE_LABORATORY_RESERVE_LABORATORY_FK_LABORATORY;
    public static final Index RESERVE_LABORATORY_RESERVE_LABORATORY_FK_USER = Indexes0.RESERVE_LABORATORY_RESERVE_LABORATORY_FK_USER;
    public static final Index USER_PRIMARY = Indexes0.USER_PRIMARY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index EQUIPMENT_EQUIPMENT_FK_LABORATORY = Internal.createIndex("equipment_fk_laboratory", Equipment.EQUIPMENT, new OrderField[] { Equipment.EQUIPMENT.LABORATORY_ID }, false);
        public static Index EQUIPMENT_PRIMARY = Internal.createIndex("PRIMARY", Equipment.EQUIPMENT, new OrderField[] { Equipment.EQUIPMENT.ID }, true);
        public static Index LABORATORY_PRIMARY = Internal.createIndex("PRIMARY", Laboratory.LABORATORY, new OrderField[] { Laboratory.LABORATORY.ID }, true);
        public static Index LOGIN_USER_UK_EMAIL = Internal.createIndex("user_uk_email", Login.LOGIN, new OrderField[] { Login.LOGIN.EMAIL }, true);
        public static Index RESERVE_EQUIPMENT_PRIMARY = Internal.createIndex("PRIMARY", ReserveEquipment.RESERVE_EQUIPMENT, new OrderField[] { ReserveEquipment.RESERVE_EQUIPMENT.ID }, true);
        public static Index RESERVE_EQUIPMENT_RESERVE_EQUIPMENT_FK_EQUIPMENT = Internal.createIndex("reserve_equipment_fk_equipment", ReserveEquipment.RESERVE_EQUIPMENT, new OrderField[] { ReserveEquipment.RESERVE_EQUIPMENT.EQUIPMENT_ID }, false);
        public static Index RESERVE_EQUIPMENT_RESERVE_EQUIPMENT_FK_USER = Internal.createIndex("reserve_equipment_fk_user", ReserveEquipment.RESERVE_EQUIPMENT, new OrderField[] { ReserveEquipment.RESERVE_EQUIPMENT.USER_ID }, false);
        public static Index RESERVE_LABORATORY_PRIMARY = Internal.createIndex("PRIMARY", ReserveLaboratory.RESERVE_LABORATORY, new OrderField[] { ReserveLaboratory.RESERVE_LABORATORY.ID }, true);
        public static Index RESERVE_LABORATORY_RESERVE_LABORATORY_FK_LABORATORY = Internal.createIndex("reserve_laboratory_fk_laboratory", ReserveLaboratory.RESERVE_LABORATORY, new OrderField[] { ReserveLaboratory.RESERVE_LABORATORY.LABORATORY_ID }, false);
        public static Index RESERVE_LABORATORY_RESERVE_LABORATORY_FK_USER = Internal.createIndex("reserve_laboratory_fk_user", ReserveLaboratory.RESERVE_LABORATORY, new OrderField[] { ReserveLaboratory.RESERVE_LABORATORY.USER_ID }, false);
        public static Index USER_PRIMARY = Internal.createIndex("PRIMARY", User.USER, new OrderField[] { User.USER.ID }, true);
    }
}
