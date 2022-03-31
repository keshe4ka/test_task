package com.keshe4ka;

import java.util.Arrays;

public class Airport {
    private String[] airportColumns;

    public Airport(String[] airportColumns) {
        this.airportColumns = airportColumns;
    }

    public String getColumn(int indexed_column) {
        return airportColumns[indexed_column];
    }

    @Override
    public String toString(){
        return Arrays.toString(airportColumns);
    }

}
