package com.alive.networks.db.dao;

import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLTimeoutException;
import java.util.List;

import com.alive.networks.constants.Constant;
import com.alive.networks.db.mapper.ApiMapper;
import com.alive.networks.db.session.DBSessionFactory;
import com.alive.networks.db.vo.ApiInfoVo;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiDao {
	private static Logger logger = LoggerFactory.getLogger(ApiDao.class);

	public boolean insertUserInfo(ApiInfoVo vo){
		boolean result = false;
		SqlSession session = DBSessionFactory.getInstance().openSession(false);
		try{
			ApiMapper apiMapper = session.getMapper(ApiMapper.class);
			if(apiMapper.insertUserInfo(vo) != 0){
				result = true;
			}else{
				result = false;
			}
			session.commit();
		} catch(Exception e){
			if(e instanceof SQLTimeoutException){
				logger.error("SQLTimeoutException\n"+e+"\n"+vo.toString());
			}
			else if(e instanceof SQLIntegrityConstraintViolationException){
				logger.warn("SQLIntegrityConstraintViolationException\n"+e+"\n"+vo.toString());
			}
			else{
				logger.error("Else Exception\n"+e+"\n"+vo.toString());
			}
		} finally{
			if(session != null){
				session.close();
			}
		}
		return result;
	}

	public boolean insertBoardInfo(ApiInfoVo vo){
		boolean result = false;
		SqlSession session = DBSessionFactory.getInstance().openSession(false);
		try{
			ApiMapper apiMapper = session.getMapper(ApiMapper.class);
			logger.info(vo.getBoardTitle());
			logger.info(vo.getBoardContent());
			if(apiMapper.insertBoardInfo(vo) != 0){
				logger.info("TRUE");
				result = true;
			}else{
				logger.info("FALSE");
				result = false;
			}
			session.commit();
		} catch(Exception e){
			if(e instanceof SQLTimeoutException){
				logger.error("SQLTimeoutException\n"+e+"\n"+vo.toString());
			}
			else if(e instanceof SQLIntegrityConstraintViolationException){
				logger.warn("SQLIntegrityConstraintViolationException\n"+e+"\n"+vo.toString());
			}
			else{
				logger.error("Else Exception\n"+e+"\n"+vo.toString());
			}
		} finally{
			if(session != null){
				session.close();
			}
		}
		return result;
	}

	public ApiInfoVo selectUserExistCheck(ApiInfoVo vo){
		SqlSession session = DBSessionFactory.getInstance().openSession(false);
		try{
			ApiMapper apiMapper = session.getMapper(ApiMapper.class);
			ApiInfoVo exist = apiMapper.selectUserExistCheck(vo);

			if(exist.getUserId() != Constant.NOT_EXIST_CDOE){
				//ID중복(ID만)
				vo.setCheckParam("F");
				return vo;
			}

			if(exist.getUserNick() != Constant.NOT_EXIST_CDOE){
				//닉네임중복(닉네임만)
				vo.setCheckParam("F");
				return vo;
			}

			if(exist.getUserName() != Constant.NOT_EXIST_CDOE){
				//회원중복(신원정보+휴대폰번호)
				vo.setCheckParam("F");
				return vo;
			}
			session.commit();
		} catch(Exception e){
			if(e instanceof SQLTimeoutException){
				logger.error("SQLTimeoutException\n"+e+"\n"+vo.toString());
			}
			else if(e instanceof SQLIntegrityConstraintViolationException){
				logger.warn("SQLIntegrityConstraintViolationException\n"+e+"\n"+vo.toString());
			}
			else{
				logger.error("Else Exception\n"+e+"\n"+vo.toString());
			}
		} finally{
			if(session != null){
				session.close();
			}
		}
		vo.setCheckParam("T");
		return vo;
	}

	public List<ApiInfoVo> selectBoardList(ApiInfoVo vo) {
		SqlSession session = DBSessionFactory.getInstance().openSession(false);
		List<ApiInfoVo> boardList = null;
		try {
			ApiMapper apiMapper = session.getMapper(ApiMapper.class);
			boardList = apiMapper.selectBoardList(vo);
		} catch (Exception e) {
			if (e instanceof SQLTimeoutException) {
				logger.error("SQLTimeoutException\n"+e+"\n"+vo.toString());
			} else if (e instanceof SQLIntegrityConstraintViolationException) {
				logger.warn("SQLIntegrityConstraintViolationException\n"+e+"\n"+vo.toString());
			} else {
				logger.error("Else Exception\n"+e+"\n"+vo.toString());
			}
		} finally {
			if (session != null) {
				session.commit();
				session.close();
			}
		}
		return boardList;
	}

	public List<ApiInfoVo> selectBoardDetailView(ApiInfoVo vo) {
		SqlSession session = DBSessionFactory.getInstance().openSession(false);
		List<ApiInfoVo> boardDetail = null;
		try {
			ApiMapper apiMapper = session.getMapper(ApiMapper.class);
			boardDetail = apiMapper.selectBoardDetailView(vo);
		} catch (Exception e) {
			if (e instanceof SQLTimeoutException) {
				logger.error("SQLTimeoutException\n"+e+"\n"+vo.toString());
			} else if (e instanceof SQLIntegrityConstraintViolationException) {
				logger.warn("SQLIntegrityConstraintViolationException\n"+e+"\n"+vo.toString());
			} else {
				logger.error("Else Exception\n"+e+"\n"+vo.toString());
			}
		} finally {
			if (session != null) {
				session.commit();
				session.close();
			}
		}
		return boardDetail;
	}

	public List<ApiInfoVo> selectBoardReplyList(ApiInfoVo vo) {
		SqlSession session = DBSessionFactory.getInstance().openSession(false);
		List<ApiInfoVo> boardList = null;
		try {
			ApiMapper apiMapper = session.getMapper(ApiMapper.class);
			boardList = apiMapper.selectBoardReplyList(vo);
		} catch (Exception e) {
			if (e instanceof SQLTimeoutException) {
				logger.error("SQLTimeoutException\n"+e+"\n"+vo.toString());
			} else if (e instanceof SQLIntegrityConstraintViolationException) {
				logger.warn("SQLIntegrityConstraintViolationException\n"+e+"\n"+vo.toString());
			} else {
				logger.error("Else Exception\n"+e+"\n"+vo.toString());
			}
		} finally {
			if (session != null) {
				session.commit();
				session.close();
			}
		}
		return boardList;
	}

	public List<ApiInfoVo> selectBoardReReplyList(ApiInfoVo vo) {
		SqlSession session = DBSessionFactory.getInstance().openSession(false);
		List<ApiInfoVo> boardList = null;
		try {
			ApiMapper apiMapper = session.getMapper(ApiMapper.class);
			boardList = apiMapper.selectBoardReReplyList(vo);
		} catch (Exception e) {
			if (e instanceof SQLTimeoutException) {
				logger.error("SQLTimeoutException\n"+e+"\n"+vo.toString());
			} else if (e instanceof SQLIntegrityConstraintViolationException) {
				logger.warn("SQLIntegrityConstraintViolationException\n"+e+"\n"+vo.toString());
			} else {
				logger.error("Else Exception\n"+e+"\n"+vo.toString());
			}
		} finally {
			if (session != null) {
				session.commit();
				session.close();
			}
		}
		return boardList;
	}

	public List<ApiInfoVo> selectBoardContentList(ApiInfoVo vo) {
		SqlSession session = DBSessionFactory.getInstance().openSession(false);
		List<ApiInfoVo> boardContentList = null;
		try {
			ApiMapper apiMapper = session.getMapper(ApiMapper.class);
			boardContentList = apiMapper.selectBoardContentList(vo);
		} catch (Exception e) {
			if (e instanceof SQLTimeoutException) {
				logger.error("SQLTimeoutException\n"+e+"\n"+vo.toString());
			} else if (e instanceof SQLIntegrityConstraintViolationException) {
				logger.warn("SQLIntegrityConstraintViolationException\n"+e+"\n"+vo.toString());
			} else {
				logger.error("Else Exception\n"+e+"\n"+vo.toString());
			}
		} finally {
			if (session != null) {
				session.commit();
				session.close();
			}
		}
		return boardContentList;
	}
}
