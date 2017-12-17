package com.neil.oms.model;

/**
 * Created by neilmendum on 16/12/2017.
 */
public class ISIN {

    private final Long id;
    private final String code;

    public ISIN(Long id, String code) {
        this.id = id;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ISIN isin1 = (ISIN) o;

        if (!id.equals(isin1.id)) return false;
        return code.equals(isin1.code);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + code.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ISIN{" +
                "id=" + id +
                ", code='" + code + '\'' +
                '}';
    }
}
