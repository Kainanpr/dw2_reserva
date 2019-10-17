/*
 * This file is generated by jOOQ.
 */
package com.dw2.reserva.persistence.jooq.tables.records;


import com.dw2.reserva.persistence.jooq.tables.Equipment;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
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
public class EquipmentRecord extends UpdatableRecordImpl<EquipmentRecord> implements Record3<Integer, String, Integer> {

    private static final long serialVersionUID = -239548852;

    /**
     * Setter for <code>equipment.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>equipment.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>equipment.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>equipment.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>equipment.laboratory_id</code>.
     */
    public void setLaboratoryId(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>equipment.laboratory_id</code>.
     */
    public Integer getLaboratoryId() {
        return (Integer) get(2);
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
    // Record3 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<Integer, String, Integer> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<Integer, String, Integer> valuesRow() {
        return (Row3) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Equipment.EQUIPMENT.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Equipment.EQUIPMENT.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field3() {
        return Equipment.EQUIPMENT.LABORATORY_ID;
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
    public String component2() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component3() {
        return getLaboratoryId();
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
    public String value2() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value3() {
        return getLaboratoryId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EquipmentRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EquipmentRecord value2(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EquipmentRecord value3(Integer value) {
        setLaboratoryId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EquipmentRecord values(Integer value1, String value2, Integer value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached EquipmentRecord
     */
    public EquipmentRecord() {
        super(Equipment.EQUIPMENT);
    }

    /**
     * Create a detached, initialised EquipmentRecord
     */
    public EquipmentRecord(Integer id, String name, Integer laboratoryId) {
        super(Equipment.EQUIPMENT);

        set(0, id);
        set(1, name);
        set(2, laboratoryId);
    }
}
