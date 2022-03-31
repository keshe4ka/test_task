package com.keshe4ka;

import java.util.Comparator;

public class AirportSorter implements Comparator<Airport> {

    private int indexedColumn;

    public AirportSorter(int indexed_column) {
        this.indexedColumn = indexed_column;
    }

    @Override
    public int compare(Airport o1, Airport o2) {
        return String.CASE_INSENSITIVE_ORDER.compare(o1.getColumn(indexedColumn), o2.getColumn(indexedColumn));
    }

}
