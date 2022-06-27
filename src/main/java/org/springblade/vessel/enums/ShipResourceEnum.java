package org.springblade.vessel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 船舶资源枚举类
 *
 * @author Ricky
 * @version 2.0.0
 * @since 2022/5/25 16:14
 */
@Getter
@AllArgsConstructor
public enum ShipResourceEnum {

	/**
	 * 船舶
	 */
	SHIP(0, "船舶"),

	/**
	 * 目录
	 */
	RESOURCE(1, "目录"),
	;

	public static ShipResourceEnum of(Integer type) {
		ShipResourceEnum[] shipResourceEnums = values();
		for (ShipResourceEnum typeEnum : shipResourceEnums) {
			if ((typeEnum.type).equals(type)) {
				return typeEnum;
			}
		}
		return ShipResourceEnum.RESOURCE;
	}

	final Integer type;

	final String desc;

}
