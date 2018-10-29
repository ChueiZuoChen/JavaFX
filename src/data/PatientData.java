package data;

public class PatientData {
    public PatientData(int id, String name, String surname, String phone, String room, String bed) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.room = room;
        this.bed = bed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;
    String name;
    String surname;
    String phone;
    String room;
    String bed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public PatientData(String name, String surname, String phone, String room, String bed) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.room = room;
        this.bed = bed;
    }


    @Override
    public String toString() {
        return "PatientData{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone=" + phone +
                ", room=" + room +
                ", bed=" + bed +
                '}';
    }
}
