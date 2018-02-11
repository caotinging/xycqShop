package caotinging.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import caotinging.mapper.BaseDictMapper;
import caotinging.pojo.BaseDict;
import caotinging.service.BaseDictService;

@Service
public class BaseDictServiceImpl implements BaseDictService {

	@Autowired
	private BaseDictMapper baseDictMapper;

	@Override
	public List<BaseDict> findListByTypeCode(String typeCode) {
		List<BaseDict> list = baseDictMapper.findListByTypeCode(typeCode);
		return list;
	}
	
}
