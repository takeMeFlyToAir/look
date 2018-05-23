package com.zzr.util.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ObjectConvertUtil {

	/**
	 * 将list数据转化为voClass类型
	 * @param poPager
	 * @param voClass
	 * @return
	 */
	public static <VoType, PoType> List<VoType> poListToVOList(
			List<PoType> poPager, Class<VoType> voClass) {
		List<VoType> result = new ArrayList<>();
		try {
			for (Object po : poPager) {
				result.add( copyProperties(po,voClass));
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return result;
	}
	
	/**
	 * po转VO
	 * @param po
	 * @param voClass
	 * @return
	 */
	public static <VoType,PoType> VoType  poToVO(PoType po,Class<VoType> voClass){
		return copyProperties(po,voClass);
	}
	/**
	 * vo转po
	 * @param vo
	 * @param poClass
	 * @return
	 */
	public static <VoType,PoType> PoType  voToPO(VoType vo,Class<PoType> poClass){
		return copyProperties(vo,poClass);
	}
	
	public static <PoType,QoType> QoType poToQo (PoType po,Class<QoType> qoClass){
		return copyProperties(po,qoClass);
	}
	public static <QoType,PoType> PoType qoToPo (QoType qo,Class<PoType> poClass){
		return copyProperties(qo,poClass);
	}
	/**
	 * 反射创建arg2 并将arg1 值 给予 arg1
	 *
	 * @Title: copyProperties
	 * @Description:
	 * @author liufutong
	 * @param @param arg1
	 * @param @param arg2
	 * @param @return    设定文件
	 * @return Arg2Type    返回类型
	 * @throws
	 */
	public static <Arg1Type,Arg2Type> Arg2Type copyProperties(Arg1Type arg1,Class<Arg2Type> arg2){
		Arg2Type result = null;
		try {
			result = arg2.newInstance();
			PropertyUtils.copyProperties(result, arg1);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return  result;
	}

	
	public static void paramsError(String className,String methodName,String msg,Object o){
		log.error("类："+className+"，方法："+methodName+"，信息："+msg+"，Object："+o);
	}
}
