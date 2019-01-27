/**
 * DefaultDao.java
	Description:
 */
package com.eastorm.impl.car.dao;

import com.eastorm.api.car.domain.BizTrainTime;
import com.eastorm.impl.car.dao.plus.BizTrainTimePlus;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author Pengkun (penkee@163.com)
 *	2013-9-15
 */
public interface BizTrainTimeDao extends JpaRepository <BizTrainTime, Integer>, BizTrainTimePlus {
}
