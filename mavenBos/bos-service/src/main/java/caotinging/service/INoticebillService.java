package caotinging.service;

import caotinging.domain.Noticebill;

public interface INoticebillService {

	/**
	 * 创建并保存一个新的业务通知单
	 * @param model
	 */
	void saveNoticebill(Noticebill model);

}
