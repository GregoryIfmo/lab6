package collection;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class LabWork  implements Comparable<LabWork>, Serializable {
        private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
        private String name; //Поле не может быть null, Строка не может быть пустой
        private Coordinates coordinates; //Поле не может быть null
        private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
        private double minimalPoint; //Значение поля должно быть больше 0
        private Difficulty difficulty; //Поле может быть null
        private Discipline discipline; //Поле не может быть null


        public int compareTo(LabWork o) {
                return (int)(this.minimalPoint-o.getMinimalPoint());
        }


        public void setCoordinates(Coordinates coordinates){this.coordinates = coordinates;}

        public boolean setName(String name){
                if(name!=null){
                        this.name = name;
                        return true;
                }else {
                        return false;
                }

        }

        public void setDifficulty(Difficulty difficulty){this.difficulty = difficulty; }

        public void setDiscipline(Discipline discipline){this.discipline = discipline; }

        public void setMinimalPoint(double x){this.minimalPoint = x;}

        public void setId(long id){
                id+=1;
                this.id = id;}

        public void printALL(){
                System.out.println(id);
                System.out.println(name);
                System.out.println(minimalPoint);
                System.out.println(discipline);

        }
        public long getId(){
                return id;
        }
        public String getName(){return name;}

        public Coordinates getCoordinates() {

                return coordinates;
        }

        public Date getCreationDate() {
                return creationDate;
        }

        public double getMinimalPoint() {
                return minimalPoint;
        }

        public Difficulty getDifficulty() {
                return difficulty;
        }

        public Discipline getDiscipline() {
                return discipline;
        }

        public void setCreationDate(Date date) {
                this.creationDate = date;
        }

        @Override
        public String toString() {
                return "{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", coordinates=" + coordinates +
                        ", creationDate=" + creationDate +
                        ", minimalPoint=" + minimalPoint +
                        ", difficulty=" + difficulty +
                        ", discipline=" + discipline +
                        '}';
        }

}
