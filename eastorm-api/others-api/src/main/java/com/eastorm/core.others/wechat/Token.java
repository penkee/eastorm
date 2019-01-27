package com.eastorm.core.others.wechat;

import java.util.Date;

/**
	 * 令牌
	 * @author 郑波
	 *
	 */
public	class Token {

		/* token值 */
		private String value;
		/* 有效时长，单位：秒 */
		private int timeLength;
		/* 上一次刷新时间 */
		private Date refreshTime;
		/* 刷新令牌值的refreshToken */
		private String refreshToken;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public int getTimeLength() {
			return timeLength;
		}

		public void setTimeLength(int timeLength) {
			this.timeLength = timeLength;
		}

		public Date getRefreshTime() {
			return refreshTime;
		}

		public void setRefreshTime(Date refreshTime) {
			this.refreshTime = refreshTime;
		}

		public String getRefreshToken() {
			return refreshToken;
		}

		public void setRefreshToken(String refreshToken) {
			this.refreshToken = refreshToken;
		}
	}