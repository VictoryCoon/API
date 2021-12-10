package com.alive.networks.db.mapper;

import com.alive.networks.db.vo.ApiInfoVo;

import java.util.ArrayList;
import java.util.List;

public interface ApiMapper {

	/* User */
	public int insertUserInfo(ApiInfoVo vo);
	public ApiInfoVo selectUserExistCheck(ApiInfoVo vo);
	public int updateUserInfo(ApiInfoVo vo);
	public int deleteUserInfo(ApiInfoVo vo);
	public ApiInfoVo selectUserInfo(ApiInfoVo vo);

	/* Board */
	public List<ApiInfoVo> selectBoardList(ApiInfoVo vo);
	public List<ApiInfoVo> selectBoardDetailView(ApiInfoVo vo);
	public List<ApiInfoVo> selectBoardContentList(ApiInfoVo vo);
	public List<ApiInfoVo> selectBoardReplyList(ApiInfoVo vo);
	public List<ApiInfoVo> selectBoardReReplyList(ApiInfoVo vo);
	public int insertBoardInfo(ApiInfoVo vo);
	/*public int updateBoardInfo(ApiInfoVo vo);
	public int deleteoardInfo(ApiInfoVo vo);
	public int insertBoardContent(ApiInfoVo vo);
	public int updateBoardPreference(ApiInfoVo vo);

	public ApiInfoVo selectBoardReplyList(ApiInfoVo vo);
	public int insertBoardReplyInfo(ApiInfoVo vo);
	public int updateBoardReplyInfo(ApiInfoVo vo);
	public int deleteBoardReplyInfo(ApiInfoVo vo);
	public int updateBoardReplyPreference(ApiInfoVo vo);

	public ApiInfoVo selectBoardReReplyList(ApiInfoVo vo);
	public int insertBoardReReplyInfo(ApiInfoVo vo);
	public int updateBoardReReplyInfo(ApiInfoVo vo);
	public int deleteBoardReReplyInfo(ApiInfoVo vo);
	public int updateBoardReReplyPreference(ApiInfoVo vo);*/
}
