package caotinging.service;

import java.util.List;

import caotinging.domain.Decidedzone;
import caotinging.domain.Staff;
import caotinging.domain.Subarea;
import caotinging.utils.PageBean;

public interface IDecidedzoneService {

	/**
	 * 获取获取没有作废的取派员的信息
	 * @return
	 */
	List<Staff> getNDelStaff();

	/**
	 * 获取还没有分配定区的分区集合
	 * @return
	 */
	List<Subarea> getNDidSubarea();

	/**
	 * 保存定区对象
	 * @param model 定区对象
	 * @param subareaid 定区中映射的区域对象的id
	 * @param staffid  定区中映射的取派员的id
	 */
	void saveDecidedzone(Decidedzone model, String staffid, String[] subareaid);

	/**
	 * 获取所有decidedzone对象
	 * @param pageBean 
	 * @return
	 */
	void getPageList(PageBean<Decidedzone> pageBean);
	
}
