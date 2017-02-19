package ke.alphacba.cms.core.base.dao;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;

public class BaseDao extends SqlSessionDaoSupport{
	@Lazy
	@Resource
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
	    super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	private static final Logger logger = LoggerFactory.getLogger(BaseDao.class);

}
