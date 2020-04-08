package demo.java8;

public enum TypeEnum implements IEnum<Integer>{
    AType(1),BType(2),CType(3);
    final Integer value;

    TypeEnum(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

}
