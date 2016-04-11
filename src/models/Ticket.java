package models;

public class Ticket {
    private String nameShow;
    private int number;
    private int row;

    public void setRow(int row) {
        this.row = row;
    }

    public String getNameShow() {
        return nameShow;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getRow() {
        return row;
    }

    public void setNameShow(String nameShow) {
        this.nameShow = nameShow;
    }
}
