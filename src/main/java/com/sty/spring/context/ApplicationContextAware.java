package com.sty.spring.context;

import com.sty.spring.beans.BeansException;
import com.sty.spring.beans.factory.Aware;

/**
 *  应用上下文感知
 *
 * @author tianma
 * @date 2022/7/16
 * @version 1.0.0
 */
public interface ApplicationContextAware extends Aware {

	/**
	 *  设置Bean工厂
	 *  <p>
	 *      实现此接口，既能感知到所属的ApplicationContext
	 *  </p>
	 * @param applicationContext 应用上下文
	 * @throws BeansException 异常
	 */
	void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
