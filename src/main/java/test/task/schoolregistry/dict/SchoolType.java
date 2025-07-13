package test.task.schoolregistry.dict;

public enum SchoolType {

    GYMNASIUM("Гімназія"),
    LYCEUM("Ліцей"),
    ZZSO("ЗЗСО");

    private String name;

    SchoolType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
