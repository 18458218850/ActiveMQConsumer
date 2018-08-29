package com.zhuokai.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * 在网络中传输，所以需要对象序列化
 * @author 
 *
 */
@Getter
@Setter
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Integer age;
	
}
