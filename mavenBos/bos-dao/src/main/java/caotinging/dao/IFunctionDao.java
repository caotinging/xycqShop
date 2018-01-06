package caotinging.dao;

import java.util.List;

import caotinging.dao.base.IBaseDao;
import caotinging.domain.Function;

public interface IFunctionDao extends IBaseDao<Function> {

	/**
	 * 查询父功能点作为树结构
	 * @return
	 */
	List<Function> findAllAsTree();

}
