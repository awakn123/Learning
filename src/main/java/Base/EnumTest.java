package Base;

public enum EnumTest {
    ONE("first");
    String desc;
    private EnumTest(String desc) {
        this.desc = desc;
    }

    public static void main(String[] args) {
        System.out.println(ONE);
    }
}
