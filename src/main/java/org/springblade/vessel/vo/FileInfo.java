package org.springblade.vessel.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 文件信息
 *
 * @author Ricky
 * @version 2.0.0
 * @since 2022/5/26 0:12
 */
@Data
public class FileInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 文件名
	 */
	private String label;

	/**
	 * 文件地址
	 */
	private String value;
}
