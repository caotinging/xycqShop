package caotinging.dao.impl;

import org.springframework.stereotype.Repository;

import caotinging.dao.IUserDao;
import caotinging.dao.base.impl.BaseDaoImpl;
import caotinging.domain.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

}
