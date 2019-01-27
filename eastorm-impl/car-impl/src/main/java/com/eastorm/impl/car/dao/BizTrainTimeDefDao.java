/**
 * DefaultDao.java
	Description:
 */
package com.eastorm.impl.car.dao;

import com.eastorm.api.car.domain.BizTrainTimeDef;
import com.eastorm.impl.car.dao.plus.BizTrainTimeDefPlus;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author Pengkun (penkee@163.com)
 *	2013-9-15
 */
public interface BizTrainTimeDefDao extends JpaRepository <BizTrainTimeDef, Integer>, BizTrainTimeDefPlus {
}
