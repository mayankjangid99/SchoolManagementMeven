package com.school.common.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.school.common.generic.dao.DaoManagerBean;

@Repository
public class UniqueIdDao extends DaoManagerBean  {

	private static Logger LOG = LoggerFactory.getLogger(UniqueIdDao.class);
}
