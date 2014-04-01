/*
 * Copyright 2007-2107 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.ymate.platform.mvc.web.view;

import net.ymate.platform.mvc.view.IView;

/**
 * <p>
 * IWebView
 * </p>
 * <p>
 * 基于Web应用的MVC视图接口；
 * </p>
 * 
 * @author 刘镇(suninformation@163.com)
 * @version 0.0.0
 *          <table style="border:1px solid gray;">
 *          <tr>
 *          <th width="100px">版本号</th><th width="100px">动作</th><th
 *          width="100px">修改人</th><th width="100px">修改时间</th>
 *          </tr>
 *          <!-- 以 Table 方式书写修改历史 -->
 *          <tr>
 *          <td>0.0.0</td>
 *          <td>创建类</td>
 *          <td>刘镇</td>
 *          <td>2012-12-20下午7:11:43</td>
 *          </tr>
 *          </table>
 */
public interface IWebView extends IView {

	public String VIEW_REDIRECT = "redirect:";

	public String VIEW_FORWARD = "forward:";

	public String VIEW_HTTP_STATUS = "http:";

	public String VIEW_JSP = "jsp:";

	public String VIEW_JSON = "json:";

	public String VIEW_FTL = "ftl:";

	public String VIEW_INLINE_FILE = "inline_file:";

	public String VIEW_FILE = "file:";

	/**
	 * @return 返回视图内容类型
	 */
	public String getContentType();

	/**
	 * 设置视图内容类型
	 * 
	 * @param contentType 内容类型
	 */
	public IWebView setContentType(String contentType);

	/**
	 * @param name
	 * @param date
	 * @return 设置请求回应头
	 */
	public IWebView addDateHeader(String name, long date);

	/**
	 * @param name
	 * @param value
	 * @return 设置请求回应头
	 */
	public IWebView addHeader(String name, String value);

	/**
	 * @param name
	 * @param value
	 * @return 设置请求回应头
	 */
	public IWebView addIntHeader(String name, int value);

}
