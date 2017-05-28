/**
 * 
 * - Copyright 2016-2017 alphacba.
 * - Licensed under the Apache License, Version 2.0 (the "License");
 * - you may not use this file except in compliance with the License.
 * - You may obtain a copy of the License at
 * -    http://www.apache.org/licenses/LICENSE-2.0
 * - Unless required by applicable law or agreed to in writing, software
 * - distributed under the License is distributed on an "AS IS" BASIS,
 * - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * - See the License for the specific language governing permissions and
 * - limitations under the License. 
 */
package com.cms.base.api.base.query;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cms.base.api.base.dto.BaseResp;

public class BasePageQuery extends BaseResp{
	
	private static final Logger LOG = LoggerFactory.getLogger(BasePageQuery.class);
	static final int defaultPageSize = 10;// 每页默认显示10条
	private static final int defaultFristPage = 1;// 默认页码
	private static final int defaultTotalCount = 0;// 默认总记录数
	private static final int[] pageSizes = { 10, 20, 30, 50, 100};// 每页显示大小选择项
	private Integer pageSize;// 当前每页大小
	private Integer totalCount;// 当前总记录数
	private Integer currentPage;// 当前页码
	private int pageCount = 10;// 可查询前、后十页
	@SuppressWarnings("unused")
	private Integer pageFirstItem;// 分页查询起始值（row >= pageFirstItem）
	@SuppressWarnings("unused")
	private Integer pageLastItem;// 分页查询结束值（row <= pageLastItem）

	// 只用于接收页面传参，控制tab显示。
	private Integer tabIndex = 0;
	private Integer tabCount = 1;

	private boolean distinct = false;
	private String orderByClause;
	
	private boolean includeTotalRows = false;

	private String functionId;
	private String headOperAccount; // WEB页面登陆操作员账号
	private String headOperName;    // WEB页面登陆操作员名称
	private String headOperType;    // WEB页面登陆操作员类型
	private String headOperExchangeId; // WEB页面登陆操作员所属交易所ID
	private String headOperMemCode;    // WEB页面登陆操作员对应的交易商编号
	
	private String upMemCode;
	private String memCode;
	private String exchangeId;
	private String loginIp;
	
	public int[] getPageSizes() {
		return pageSizes;
	}

	public int getPageCount() {
		return this.pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPrePage() {
		if (isFirstPage()) {
			return getCurrentPage().intValue();
		}
		return Math.max(getCurrentPage().intValue() - 10, 1);
	}

	public int getNextPage() {
		if (isLastPage()) {
			return getCurrentPage().intValue();
		}
		return Math.min(getCurrentPage().intValue() + 10, getTotalPage());
	}

	public int getBeginPage() {
		if (getCurrentPage().intValue() > 0) {
			return this.pageCount
					* ((getCurrentPage().intValue() - 1) / this.pageCount) + 1;
		}
		return 0;
	}

	public int getEndPage() {
		if (getCurrentPage().intValue() > 0) {
			return Math.min(this.pageCount
					* ((getCurrentPage().intValue() - 1) / this.pageCount + 1),
					getTotalPage());
		}
		return 0;
	}

	protected Integer getDefaultPageSize() {
		return defaultPageSize;
	}

	public boolean isFirstPage() {
		return this.getCurrentPage().intValue() == 1;
	}

	public int getPreviousPage() {
		int back = this.getCurrentPage().intValue() - 1;
		if (back <= 0) {
			back = 1;
		}
		return back;
	}

	public boolean isLastPage() {
		return this.getTotalPage() == this.getCurrentPage().intValue();
	}

	public Integer getCurrentPage() {
		if (currentPage == null) {
			return defaultFristPage;
		} else {
			return currentPage;
		}
	}

	public void setCurrentPage(Integer cPage) {
		if (cPage == null || cPage.intValue() <= 0) {
			currentPage = defaultFristPage;
		} else {
			currentPage = cPage;
		}
	}

	public void setCurrentPageString(String s) {
		if (StringUtils.isBlank(s)) {
			return;
		}
		try {
			this.setCurrentPage(Integer.parseInt(s));
		} catch (Exception ignore) {
			LOG.error("CurrentPage set is failure.", ignore);
			this.setCurrentPage(defaultFristPage);
		}
	}

	public Integer getPageSize() {
		if (pageSize == null) {
			return this.getDefaultPageSize();
		} else {
			return pageSize;
		}
	}

	public boolean hasSetPageSize() {
		return pageSize != null;
	}

	public void setPageSize(Integer pSize) {
		if (pSize == null) {
			throw new IllegalArgumentException("PageSize not null!");
		}
		if (pSize.intValue() <= 0) {
			throw new IllegalArgumentException("PageSize must great than zero!");
		} else {
			pageSize = pSize;
		}
	}

	public void setPageSizeString(String pageSizeString) {
		if (StringUtils.isBlank(pageSizeString)) {
			return;
		}
		try {
			this.setPageSize(Integer.parseInt(pageSizeString));
		} catch (NumberFormatException ignore) {
			LOG.error("PageSize set is failure.", ignore);
			this.setPageSize(defaultPageSize);
		}
	}

	public Integer getTotalCount() {
		if (totalCount == null) {
			return defaultTotalCount;
		} else {
			return totalCount;
		}
	}

	public void setTotalCount(Integer totalCount) {
		if (totalCount == null) {
			totalCount = 0;
		}
		this.totalCount = totalCount;
		int current = this.getCurrentPage().intValue();
		int lastPage = this.getTotalPage();
		if (current > lastPage) {
			this.setCurrentPage(lastPage);
		}
	}

	public int getTotalPage() {
		int pgSize = this.getPageSize().intValue();
		int totalCount = this.getTotalCount().intValue();
		int result = totalCount / pgSize;
		if (totalCount % pgSize != 0) {
			result++;
		}
		return result;
	}

	public int getExpectPageLastItem() {
		int cPage = this.getCurrentPage().intValue();
		int pgSize = this.getPageSize().intValue();
		return pgSize * cPage;
	}

	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public void setPageFirstItem(Integer pageFirstItem) {
		this.pageFirstItem = pageFirstItem;
	}

	public void setPageLastItem(Integer pageLastItem) {
		this.pageLastItem = pageLastItem;
	}

	public Integer getPageFirstItem() {
		int cPage = this.getCurrentPage().intValue();
		if (cPage == 1) {
			return 0;
		} else {
			cPage--;
			int pgSize = this.getPageSize().intValue();
			return pgSize * cPage;
		}
	}

	public Integer getPageLastItem() {
		int expectPageLastItem = this.getExpectPageLastItem();
		int totalCount = this.getTotalCount().intValue();
		if (expectPageLastItem > totalCount) {
			return totalCount;
		} else {
			return expectPageLastItem;
		}
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public boolean isDistinct() {
		return distinct;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public Integer getTabIndex() {
		return tabIndex;
	}

	public void setTabIndex(Integer tabIndex) {
		this.tabIndex = tabIndex;
	}

	public Integer getTabCount() {
		return tabCount;
	}

	public void setTabCount(Integer tabCount) {
		this.tabCount = tabCount;
	}

	public boolean isIncludeTotalRows() {
		return includeTotalRows;
	}

	public void setIncludeTotalRows(boolean includeTotalRows) {
		this.includeTotalRows = includeTotalRows;
	}

	public String getHeadOperAccount() {
		return headOperAccount;
	}

	public void setHeadOperAccount(String headOperAccount) {
		this.headOperAccount = headOperAccount;
	}

	public String getHeadOperName() {
		return headOperName;
	}

	public void setHeadOperName(String headOperName) {
		this.headOperName = headOperName;
	}

}
