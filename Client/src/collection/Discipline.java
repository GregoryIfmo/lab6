package collection;

import java.io.Serializable;

public class Discipline implements Serializable {
    private String name;
    private long selfStudyHours;

    public void setName(String name){this.name = name;}

    public void setSelfStudyHours(long selfStudyHours){this.selfStudyHours=selfStudyHours;}

    @Override
    public String toString() {
        return name+", количество часов: "+selfStudyHours;
    }

    public Discipline(String name, long selfStudyHours) {
        this.name = name;
        this.selfStudyHours = selfStudyHours;
    }
    public Discipline() {

    }
}
