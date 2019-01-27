/**
 * DefaultDao.java
	Description:
 */
package com.eastorm.impl.car.dao;

import com.eastorm.api.car.domain.BizCar;
import com.eastorm.impl.car.dao.plus.BizCarPlus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;


/**
 * @author Pengkun (penkee@163.com)
 *	2013-9-15
 */
public interface BizCarDao extends JpaRepository <BizCar, Integer>, BizCarPlus {
}
