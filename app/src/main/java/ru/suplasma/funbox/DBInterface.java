package ru.suplasma.funbox;

public interface DBInterface {
    void write(int id, String name, String price, String quantity);

    void read();
}
