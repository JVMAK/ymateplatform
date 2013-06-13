/**
 * <p>文件名:	DateValidator.java</p>
 * <p>版权:		详见产品版权说明书</p>
 * <p>公司:		YMateSoft Co., Ltd.</p>
 * <p>项目名：	ymateplatform</p>
 * <p>作者：		刘镇(suninformation@163.com)</p>
 */
package net.ymate.platform.validation.impl;

import java.util.Map;

import net.ymate.platform.commons.util.DateTimeUtils;
import net.ymate.platform.validation.AbstractValidator;
import net.ymate.platform.validation.IValidateContext;
import net.ymate.platform.validation.ValidationException;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * DateValidator
 * </p>
 * <p>
 * 日期类型参数验证；
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
 *          <td>2013-4-17下午9:43:55</td>
 *          </tr>
 *          </table>
 */
public class DateValidator extends AbstractValidator {

	public static final String NAME = "date";

	/* (non-Javadoc)
	 * @see net.ymate.platform.validation.IValidator#getName()
	 */
	public String getName() {
		return NAME;
	}

	/* (non-Javadoc)
	 * @see net.ymate.platform.validation.AbstractValidator#onValidate(net.ymate.platform.validation.IValidateContext)
	 */
	@Override
	protected String onValidate(IValidateContext context) {
		if (isString(context.getFieldValue().getClass())) {
			String _value = (String) context.getFieldValue();
			if (doParamsLengthCheck(context, 0) && StringUtils.isNotBlank(_value)) {
				Map<String, String> _params = getParamMaps(context);
				try {
					DateTimeUtils.parseDateTime(_value, _params.get("pattern"));
				} catch (Exception e) {
					return doMessageResult(context, getResultMessageI18nStr(context), getDefaultResultMessageStr(context));
				}
			}
		} else {
			throw new ValidationException("不支持非字符串类型的日期参数验证");
		}
		return onValidateNull(context);
	}

	/* (non-Javadoc)
	 * @see net.ymate.platform.validation.AbstractValidator#onValidateArray(net.ymate.platform.validation.IValidateContext, java.lang.Class)
	 */
	@Override
	protected String onValidateArray(IValidateContext context, Class<?> arrayClassType) {
		throw new ValidationException("不支持非字符串类型的日期参数验证");
	}

	/* (non-Javadoc)
	 * @see net.ymate.platform.validation.impl.RegexValidator#getResultMessageI18nStr(net.ymate.platform.validation.IValidateContext)
	 */
	protected String getResultMessageI18nStr(IValidateContext context) {
		return context.isI18n() ? "ymp.validation.date" : null;
	}

	/* (non-Javadoc)
	 * @see net.ymate.platform.validation.impl.RegexValidator#getDefaultResultMessageStr(net.ymate.platform.validation.IValidateContext)
	 */
	protected String getDefaultResultMessageStr(IValidateContext context) {
		return "不是有效的日期格式";
	}

}
