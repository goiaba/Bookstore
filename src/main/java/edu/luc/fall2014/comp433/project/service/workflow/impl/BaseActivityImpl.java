package edu.luc.fall2014.comp433.project.service.workflow.impl;

import edu.luc.fall2014.comp433.project.dao.BaseDao;
import edu.luc.fall2014.comp433.project.model.BaseEntity;
import edu.luc.fall2014.comp433.project.service.workflow.BaseActivity;

public abstract class BaseActivityImpl<I extends Number, E extends BaseEntity<I>, D extends BaseDao<I, E>>
		implements BaseActivity<I, E> {

}
