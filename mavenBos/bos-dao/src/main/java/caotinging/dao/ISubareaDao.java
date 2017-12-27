package caotinging.dao;

import java.util.List;

import caotinging.dao.base.IBaseDao;
import caotinging.domain.Subarea;

public interface ISubareaDao extends IBaseDao<Subarea> {

	/**
	 * 获取还没有分配定区的分区集合
	 * @return
	 */
	List<Subarea> getNDidSubarea();

}
