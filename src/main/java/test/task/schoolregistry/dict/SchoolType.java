package test.task.schoolregistry.dict;

public enum SchoolType {

    GYMNASIUM("Гімназія"),
    LYCEUM("Ліцей"),
    ZZSO("ЗЗСО");

    private String code;

    SchoolType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static SchoolType fromCode(String code) {
        for (SchoolType type : SchoolType.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown SchoolType: " + code);
    }
}
