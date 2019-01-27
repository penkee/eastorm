/**
 * DefaultDao.java
	Description:
 */
package com.eastorm.impl.car.dao;

import com.eastorm.api.car.domain.BizCityLine;
import com.eastorm.impl.car.dao.plus.BizCityLinePlus;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author Pengkun (penkee@163.com)
 *	2013-9-15
 */
public interface BizCityLineDao extends JpaRepository <BizCityLine, Integer>, BizCityLinePlus {
}
