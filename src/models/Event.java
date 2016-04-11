package models;

public class Event {
    private String name;
    private String regie;
    private String distributie;
    private String dataPremierei;
    private int numberOfTockets;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegie() {
        return regie;
    }

    public void setRegie(String regie) {
        this.regie = regie;
    }

    public String getDistributie() {
        return distributie;
    }

    public void setDistributie(String distributie) {
        this.distributie = distributie;
    }

    public String getDataPremierei() {
        return dataPremierei;
    }

    public void setDataPremierei(String dataPremierei) {
        this.dataPremierei = dataPremierei;
    }

    public int getNumberOfTockets() {
        return numberOfTockets;
    }

    public void setNumberOfTockets(int numberOfTockets) {
        this.numberOfTockets = numberOfTockets;
    }

    public String toString() {
        return getName() + "; " + getRegie() + "; " + getDistributie() + "; " + getDataPremierei() + "; "
                + getNumberOfTockets();
    }
}
