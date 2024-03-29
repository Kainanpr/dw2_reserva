/*
 * This file is generated by jOOQ.
 */
package com.dw2.reserva.persistence.jooq.tables.records;


import com.dw2.reserva.persistence.jooq.tables.ReserveEquipment;

import java.time.LocalDateTime;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ReserveEquipmentRecord extends UpdatableRecordImpl<ReserveEquipmentRecord> implements Record5<Integer, LocalDateTime, LocalDateTime, Integer, Integer> {

    private static final long serialVersionUID = -1940280375;

    /**
     * Setter for <code>reserve_equipment.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>reserve_equipment.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>reserve_equipment.start_date</code>.
     */
    public void setStartDate(LocalDateTime value) {
        set(1, value);
    }

    /**
     * Getter for <code>reserve_equipment.start_date</code>.
     */
    public LocalDateTime getStartDate() {
        return (LocalDateTime) get(1);
    }

    /**
     * Setter for <code>reserve_equipment.end_date</code>.
     */
    public void setEndDate(LocalDateTime value) {
        set(2, value);
    }

    /**
     * Getter for <code>reserve_equipment.end_date</code>.
     */
    public LocalDateTime getEndDate() {
        return (LocalDateTime) get(2);
    }

    /**
     * Setter for <code>reserve_equipment.user_id</code>.
     */
    public void setUserId(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>reserve_equipment.user_id</code>.
     */
    public Integer getUserId() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>reserve_equipment.equipment_id</code>.
     */
    public void setEquipmentId(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>reserve_equipment.equipment_id</code>.
     */
    public Integer getEquipmentId() {
        return (Integer) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, LocalDateTime, LocalDateTime, Integer, Integer> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, LocalDateTime, LocalDateTime, Integer, Integer> valuesRow() {
        return (Row5) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return ReserveEquipment.RESERVE_EQUIPMENT.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field2() {
        return ReserveEquipment.RESERVE_EQUIPMENT.START_DATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field3() {
        return ReserveEquipment.RESERVE_EQUIPMENT.END_DATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field4() {
        return ReserveEquipment.RESERVE_EQUIPMENT.USER_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field5() {
        return ReserveEquipment.RESERVE_EQUIPMENT.EQUIPMENT_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime component2() {
        return getStartDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime component3() {
        return getEndDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component4() {
        return getUserId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component5() {
        return getEquipmentId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value2() {
        return getStartDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value3() {
        return getEndDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value4() {
        return getUserId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value5() {
        return getEquipmentId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReserveEquipmentRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReserveEquipmentRecord value2(LocalDateTime value) {
        setStartDate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReserveEquipmentRecord value3(LocalDateTime value) {
        setEndDate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReserveEquipmentRecord value4(Integer value) {
        setUserId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReserveEquipmentRecord value5(Integer value) {
        setEquipmentId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReserveEquipmentRecord values(Integer value1, LocalDateTime value2, LocalDateTime value3, Integer value4, Integer value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ReserveEquipmentRecord
     */
    public ReserveEquipmentRecord() {
        super(ReserveEquipment.RESERVE_EQUIPMENT);
    }

    /**
     * Create a detached, initialised ReserveEquipmentRecord
     */
    public ReserveEquipmentRecord(Integer id, LocalDateTime startDate, LocalDateTime endDate, Integer userId, Integer equipmentId) {
        super(ReserveEquipment.RESERVE_EQUIPMENT);

        set(0, id);
        set(1, startDate);
        set(2, endDate);
        set(3, userId);
        set(4, equipmentId);
    }
}
