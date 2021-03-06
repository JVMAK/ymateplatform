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
package net.ymate.platform.mvc.support.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.ymate.platform.base.YMP;
import net.ymate.platform.commons.beans.IBeanMeta;
import net.ymate.platform.commons.beans.IBeanMetaLoader;
import net.ymate.platform.commons.beans.annotation.Bean;
import net.ymate.platform.commons.beans.impl.AnnotationBeanFactory;
import net.ymate.platform.commons.beans.impl.AnnotationBeanMetaLoader;
import net.ymate.platform.commons.i18n.I18N;
import net.ymate.platform.commons.util.ClassUtils;
import net.ymate.platform.mvc.annotation.Controller;
import net.ymate.platform.mvc.filter.IFilter;
import net.ymate.platform.mvc.support.IControllerBeanFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>
 * ControllerBeanFactory
 * </p>
 * <p>
 * MVC框架控制器对象工厂接口实现类；
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
 *          <td>2012-12-15下午7:52:56</td>
 *          </tr>
 *          </table>
 */
public class ControllerBeanFactory extends AnnotationBeanFactory implements IControllerBeanFactory {

	private static final Log _LOG = LogFactory.getLog(ControllerBeanFactory.class);

	protected static final Set<String> excludedClassNameSet;
	
	static {
		excludedClassNameSet = new HashSet<String>();
		excludedClassNameSet.add(IFilter.class.getName());
	}

	/**
	 * 构造器
	 * 
	 * @param packageNames 自动扫描的控制器包名称集合
	 */
	public ControllerBeanFactory(String... packageNames) {
		super(packageNames);
	}

	/* (non-Javadoc)
	 * @see net.ymate.platform.commons.beans.impl.AnnotationBeanFactory#loadBeanMetas()
	 */
	protected List<IBeanMeta> loadBeanMetas() {
		return new ControllerBeanMetaLoader(this.packageNames).loadBeanMetas();
	}

	/**
	 * @param clazz 目标类对象
	 * @return 返回对象元描述加载器接口实现
	 */
	protected IBeanMetaLoader getBeanMetaLoader(Class<?> clazz) {
		return new ControllerBeanMetaLoader(clazz);
	}

	/* (non-Javadoc)
	 * @see net.ymate.platform.commons.beans.impl.AnnotationBeanFactory#add(java.lang.Class)
	 */
	public IBeanMeta add(Class<?> clazz) {
		if (clazz.isAnnotationPresent(Controller.class)) {
			IBeanMetaLoader _loader = this.getBeanMetaLoader(clazz);
			for (IBeanMeta _meta : _loader.loadBeanMetas()) {
				if (!this.beanMap.containsKey(_meta.getClassName())) {
					_LOG.info(I18N.formatMessage(YMP.__LSTRING_FILE, null, null, "ymp.mvc.add_controller", _meta.getClassName()));
					this.beanMetaList.add(_meta);
					this.addBeanMeta(_meta);
					return _meta;
				}
			}
		} else if (ClassUtils.isInterfaceOf(clazz, IFilter.class)) {
			IBeanMetaLoader _loader = new AnnotationBeanMetaLoader<Bean>(Bean.class, clazz);
			for (IBeanMeta _meta : _loader.loadBeanMetas()) {
				if (!this.beanMap.containsKey(_meta.getClassName())) {
					_LOG.info(I18N.formatMessage(YMP.__LSTRING_FILE, null, null, "ymp.mvc.add_filter", _meta.getClassName()));
					this.beanMetaList.add(_meta);
					this.addBeanMeta(_meta);
					return _meta;
				}
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.ymate.platform.mvc.support.IControllerBeanFactory#getBeanMetas()
	 */
	public List<IBeanMeta> getBeanMetas() {
		return this.beanMetaList;
	}

	/* (non-Javadoc)
	 * @see net.ymate.platform.commons.beans.AbstractBeanFactory#getExcludedClassNameSet()
	 */
	protected Set<String> getExcludedClassNameSet() {
		return excludedClassNameSet;
	}

}
