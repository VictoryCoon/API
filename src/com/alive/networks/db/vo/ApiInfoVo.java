package com.alive.networks.db.vo;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class ApiInfoVo {
	private static Logger logger = LoggerFactory.getLogger(ApiInfoVo.class);

	/* Code */
	private String cSid;
	private String cLiteral;
	private String cCode;
	private String cKind;

	/* User */
	private String userSid;
	private String userId;
	private String userPw;
	private String userName;
	private String userNick;
	private String userPhone;
	private String userEmailId;
	private String userEmailPf;
	private String userBirth;
	private String userGender;
	private String userGrade;
	private int userLoginFailCnt;
	private String userLoginBlockAt;
	private String userLastLoginDate;

	/* Board */
	private String boardSid;
	private String boardSearchType;
	private String boardSearchString;
	private String boardTitle;
	private String boardContent;
	private String boardKindCode;
	private String boardTopViewAt;
	private int boardViewCnt;
	private int boardLikeCnt;
	private int boardHateCnt;

	private String boardReplySid;
	private String boardReplyContent;
	private int boardReplyLikeCnt;
	private int boardReplyHateCnt;

	private String boardReReplySid;
	private String boardReReplyContent;
	private int boardReReplyLikeCnt;
	private int boardReReplyHateCnt;

	private String boardExtsnSid;
	private String boardExtsnDirectoryRoute;
	private String boardExtsnFileName;
	private String boardExtsnFileExtsnNm;
	private String boardExtsnFileExtsnCd;

	/* Common */
	private String useAt;
	private String delYn;
	private String regDate;
	private String regUser;
	private String uptDate;
	private String uptUser;
	private String totalCnt;

	/* Validation */
	private String checkParam;

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		ApiInfoVo.logger = logger;
	}

	public String getcSid() {
		return cSid;
	}

	public void setcSid(String cSid) {
		this.cSid = cSid;
	}

	public String getcLiteral() {
		return cLiteral;
	}

	public void setcLiteral(String cLiteral) {
		this.cLiteral = cLiteral;
	}

	public String getcCode() {
		return cCode;
	}

	public void setcCode(String cCode) {
		this.cCode = cCode;
	}

	public String getcKind() {
		return cKind;
	}

	public void setcKind(String cKind) {
		this.cKind = cKind;
	}

	public String getUserSid() {
		return userSid;
	}

	public void setUserSid(String userSid) {
		this.userSid = userSid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserEmailId() {
		return userEmailId;
	}

	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}

	public String getUserEmailPf() {
		return userEmailPf;
	}

	public void setUserEmailPf(String userEmailPf) {
		this.userEmailPf = userEmailPf;
	}

	public String getUserBirth() {
		return userBirth;
	}

	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getUserGrade() {
		return userGrade;
	}

	public void setUserGrade(String userGrade) {
		this.userGrade = userGrade;
	}

	public int getUserLoginFailCnt() {
		return userLoginFailCnt;
	}

	public void setUserLoginFailCnt(int userLoginFailCnt) {
		this.userLoginFailCnt = userLoginFailCnt;
	}

	public String getUserLoginBlockAt() {
		return userLoginBlockAt;
	}

	public void setUserLoginBlockAt(String userLoginBlockAt) {
		this.userLoginBlockAt = userLoginBlockAt;
	}

	public String getUserLastLoginDate() {
		return userLastLoginDate;
	}

	public void setUserLastLoginDate(String userLastLoginDate) {
		this.userLastLoginDate = userLastLoginDate;
	}

	public String getBoardSid() {
		return boardSid;
	}

	public void setBoardSid(String boardSid) {
		this.boardSid = boardSid;
	}

	public String getBoardSearchType() {
		return boardSearchType;
	}

	public void setBoardSearchType(String boardSearchType) {
		this.boardSearchType = boardSearchType;
	}

	public String getBoardSearchString() {
		return boardSearchString;
	}

	public void setBoardSearchString(String boardSearchString) {
		this.boardSearchString = boardSearchString;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardKindCode() {
		return boardKindCode;
	}

	public void setBoardKindCode(String boardKindCode) {
		this.boardKindCode = boardKindCode;
	}

	public String getBoardTopViewAt() {
		return boardTopViewAt;
	}

	public void setBoardTopViewAt(String boardTopViewAt) {
		this.boardTopViewAt = boardTopViewAt;
	}

	public int getBoardViewCnt() {
		return boardViewCnt;
	}

	public void setBoardViewCnt(int boardViewCnt) {
		this.boardViewCnt = boardViewCnt;
	}

	public int getBoardLikeCnt() {
		return boardLikeCnt;
	}

	public void setBoardLikeCnt(int boardLikeCnt) {
		this.boardLikeCnt = boardLikeCnt;
	}

	public int getBoardHateCnt() {
		return boardHateCnt;
	}

	public void setBoardHateCnt(int boardHateCnt) {
		this.boardHateCnt = boardHateCnt;
	}

	public String getBoardReplySid() {
		return boardReplySid;
	}

	public void setBoardReplySid(String boardReplySid) {
		this.boardReplySid = boardReplySid;
	}

	public String getBoardReplyContent() {
		return boardReplyContent;
	}

	public void setBoardReplyContent(String boardReplyContent) {
		this.boardReplyContent = boardReplyContent;
	}

	public int getBoardReplyLikeCnt() {
		return boardReplyLikeCnt;
	}

	public void setBoardReplyLikeCnt(int boardReplyLikeCnt) {
		this.boardReplyLikeCnt = boardReplyLikeCnt;
	}

	public int getBoardReplyHateCnt() {
		return boardReplyHateCnt;
	}

	public void setBoardReplyHateCnt(int boardReplyHateCnt) {
		this.boardReplyHateCnt = boardReplyHateCnt;
	}

	public String getBoardReReplySid() {
		return boardReReplySid;
	}

	public void setBoardReReplySid(String boardReReplySid) {
		this.boardReReplySid = boardReReplySid;
	}

	public String getBoardReReplyContent() {
		return boardReReplyContent;
	}

	public void setBoardReReplyContent(String boardReReplyContent) {
		this.boardReReplyContent = boardReReplyContent;
	}

	public int getBoardReReplyLikeCnt() {
		return boardReReplyLikeCnt;
	}

	public void setBoardReReplyLikeCnt(int boardReReplyLikeCnt) {
		this.boardReReplyLikeCnt = boardReReplyLikeCnt;
	}

	public int getBoardReReplyHateCnt() {
		return boardReReplyHateCnt;
	}

	public void setBoardReReplyHateCnt(int boardReReplyHateCnt) {
		this.boardReReplyHateCnt = boardReReplyHateCnt;
	}

	public String getBoardExtsnSid() {
		return boardExtsnSid;
	}

	public void setBoardExtsnSid(String boardExtsnSid) {
		this.boardExtsnSid = boardExtsnSid;
	}

	public String getBoardExtsnDirectoryRoute() {
		return boardExtsnDirectoryRoute;
	}

	public void setBoardExtsnDirectoryRoute(String boardExtsnDirectoryRoute) {
		this.boardExtsnDirectoryRoute = boardExtsnDirectoryRoute;
	}

	public String getBoardExtsnFileName() {
		return boardExtsnFileName;
	}

	public void setBoardExtsnFileName(String boardExtsnFileName) {
		this.boardExtsnFileName = boardExtsnFileName;
	}

	public String getBoardExtsnFileExtsnNm() {
		return boardExtsnFileExtsnNm;
	}

	public void setBoardExtsnFileExtsnNm(String boardExtsnFileExtsnNm) {
		this.boardExtsnFileExtsnNm = boardExtsnFileExtsnNm;
	}

	public String getBoardExtsnFileExtsnCd() {
		return boardExtsnFileExtsnCd;
	}

	public void setBoardExtsnFileExtsnCd(String boardExtsnFileExtsnCd) {
		this.boardExtsnFileExtsnCd = boardExtsnFileExtsnCd;
	}

	public String getUseAt() {
		return useAt;
	}

	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getRegUser() {
		return regUser;
	}

	public void setRegUser(String regUser) {
		this.regUser = regUser;
	}

	public String getUptDate() {
		return uptDate;
	}

	public void setUptDate(String uptDate) {
		this.uptDate = uptDate;
	}

	public String getUptUser() {
		return uptUser;
	}

	public void setUptUser(String uptUser) {
		this.uptUser = uptUser;
	}

	public String getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(String totalCnt) {
		this.totalCnt = totalCnt;
	}

	public String getCheckParam() {
		return checkParam;
	}

	public void setCheckParam(String checkParam) {
		this.checkParam = checkParam;
	}

	@Override
	public String toString() {
		StringBuffer sb = null;
		String parameter = "====================================== API_Info_Vo_All S ======================================\n"
				+"[cSid]="+cSid+"\n"
				+"[cLiteral]="+cLiteral+"\n"
				+"[cCode]="+cCode+"\n"
				+"[cKind]="+cKind+"\n"
				+"[userSid]="+userSid+"\n"
				+"[userId]="+userId+"\n"
				+"[userId]="+userPw+"\n"
				+"[userName]="+userName+"\n"
				+"[userNick]="+userNick+"\n"
				+"[userPhone]="+userPhone+"\n"
				+"[userEmailId]="+userEmailId+"\n"
				+"[userEmailPf]="+userEmailPf+"\n"
				+"[userBirth]="+userBirth+"\n"
				+"[userGender]="+userGender+"\n"
				+"[userGrade]="+userGrade+"\n"
				+"[userLoginFailCnt]="+userLoginFailCnt+"\n"
				+"[userLoginBlockAt]="+userLoginBlockAt+"\n"
				+"[userLastLoginDate]="+userLastLoginDate+"\n"
				+"[boardSid]="+boardSid+"\n"
				+"[boardSearchType]="+boardSearchType+"\n"
				+"[boardSearchString]="+boardSearchString+"\n"
				+"[boardTitle]="+boardTitle+"\n"
				+"[boardContent]="+boardContent+"\n"
				+"[boardKindCode]="+boardKindCode+"\n"
				+"[boardTopViewAt]="+boardTopViewAt+"\n"
				+"[boardLikeCnt]="+boardViewCnt+"\n"
				+"[boardLikeCnt]="+boardLikeCnt+"\n"
				+"[boardHateCnt]="+boardHateCnt+"\n"
				+"[boardReplySid]="+boardReplySid+"\n"
				+"[boardReplyContent]="+boardReplyContent+"\n"
				+"[boardReReplyLikeCnt]="+boardReReplyLikeCnt+"\n"
				+"[boardReReplyHateCnt]="+boardReReplyHateCnt+"\n"
				+"[boardExtsnSid]="+boardExtsnSid+"\n"
				+"[boardExtsnDirectoryRoute]="+boardExtsnDirectoryRoute+"\n"
				+"[boardExtsnFileName]="+boardExtsnFileName+"\n"
				+"[boardExtsnFileExtsnNm]="+boardExtsnFileExtsnNm+"\n"
				+"[boardExtsnFileExtsnCd]="+boardExtsnFileExtsnCd+"\n"
				+"[useAt]="+useAt+"\n"
				+"[delYn]="+delYn+"\n"
				+"[regDate]="+regDate+"\n"
				+"[regUser]="+regUser+"\n"
				+"[uptDate]="+uptDate+"\n"
				+"[uptUser]="+uptUser+"\n"
				+"[totalCnt]="+totalCnt+"\n"
		+"====================================== API_Info_Vo_All E ======================================\n";
		return parameter;
	}
}
