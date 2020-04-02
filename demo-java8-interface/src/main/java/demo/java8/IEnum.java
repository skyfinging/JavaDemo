package demo.java8;

public interface IEnum<K> {
	K getValue();

	default Integer getDefaultValue(){
		return 1;
	}

	static <T extends Enum<?>&IEnum<K>, K> T getEnumByValue(Class<T> enumClass, K value){
		for(T each : enumClass.getEnumConstants()){
			if(each.getValue()==null && value==null
					|| each.getValue()!=null && each.getValue().equals(value)){
				return each;
			}
		}
		return null;
	}

	/**
	 * 从名称转换成枚举值
	 * @param enumClass
	 * @param name
	 * @return
	 */
	static <T extends Enum<?>> T getEnumByName(Class<T> enumClass, String name){
		for(T each : enumClass.getEnumConstants()){
			if(each.name().equals(name))
				return each;
		}
		return null;
	}
}
