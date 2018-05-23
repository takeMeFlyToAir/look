//GT 命名空间
var GT = {};

// Common 模块命名空间
if (GT.Common == undefined) {
	GT.Common = {};
}

// Purchase 模块命名空间
if (GT.Purchase == undefined) {
    GT.Purchase = {};
}

// BootStrap 模块命名空间
if (GT.BootStrap == undefined) {
    GT.BootStrap = {};
}

GT.Common.formatJsonForUpload = function (data) {
	var start = data.indexOf(">");  
	if(start != -1) {  
	      var end = data.indexOf("<", start + 1);  
	      if(end != -1) {  
	            data = data.substring(start + 1, end);  
	           }  
	    }  
	eval("data = " + data); 
	
	return data;
}
/**
 * 刷新表格 
 * @author szy
 * @param bootstrapTableId
 */
GT.BootStrap.refreshData = function(bootstrapTableId){
	$('#'+bootstrapTableId).bootstrapTable('getOptions').pageNumber = 1;
	$('#'+bootstrapTableId).bootstrapTable('refresh');
}

/**
 * 刷新表格 2
 * @author YZM
 * @param bootstrapTableId
 */
GT.BootStrap.refreshDataNew = function(bootstrapTableId){
	$('#'+bootstrapTableId).bootstrapTable('refresh');
}

/**
 * 重置搜索条件
 * @author YueZhiMing
 * @param paramsForm
 * @param dataTable
 */
GT.BootStrap.refreshBut=function(paramsForm,dataTable) {
    $("#"+paramsForm)[0].reset();
    $("#"+dataTable).bootstrapTable('refresh');
}  

/**
 * 根据枚举名称获取枚举对象<br>
 * 用法：<br>
 * 
 * 1.后台controller,
 * 		model.addAttribute("enumJsonData", EnumUtil.coverJsonFromEnumData(ApplyState.values()));<br>
 * 
 * 2.前台js调用,GT.BootStrap.getEnumInfo(eNumArray,name);<br>
 * 	 例如：bootstrap_table 格式化调用<br>
 * 	 html：<br>
 * 		<th data-field="state"  data-formatter="stateFormatter">审核状态</th><br>
 * 	 js：<br>
 *   	function stateFormatter(value, row, index){<br>
 *   		var eNumArray = '${enumJsonData}';
 *			return GT.BootStrap.getEnumObject(eNumArray,value).display;<br>
 *		}<br>
 *  
 *  3.返回：enumData对象，包括：enumData.display;enumData.name;enumData.code
 *  
 * @param eNumArray 后台枚举数组对象
 * @param name 枚举对象name
 * @return 枚举对象
 * @author 吕佳诚
 * @创建时间 2016-3-23 15:07
 */
GT.BootStrap.getEnumObject = function(eNumArray,name){
	var json = eNumArray;
	var enumData = {
			display	:	'',
			name	:	'',
			code	:	''
	}
	if( name == null || name.length == 0 || name == undefined ) return enumData;
	if( json == null || json.length == 0 || json == 'undefined') return enumData;
	json = jQuery.parseJSON(json);
	for(var i = 0; i < json.length; i++){
		if( name == json[i].name ){
			enumData.display = json[i].display;
			enumData.name = json[i].name;
			enumData.code = json[i].code;
		}
	}
	return enumData;
}

	/** 
	 * 	根据枚举code获取枚举对象<br>
	* 用法：<br>
	* 
	* 1.后台controller,
	* 		model.addAttribute("enumJsonData", EnumUtil.coverJsonFromEnumData(ApplyState.values()));<br>
	* 
	* 2.前台js调用,GT.BootStrap.getEnumInfo(eNumArray,name);<br>
	* 	 例如：bootstrap_table 格式化调用<br>
	* 	 html：<br>
	* 		<th data-field="state"  data-formatter="stateFormatter">审核状态</th><br>
	* 	 js：<br>
	*   	function stateFormatter(value, row, index){<br>
	*   		var eNumArray = '${enumJsonData}';
	*			return GT.BootStrap.getEnumObject(eNumArray,value).display;<br>
	*		}<br>
	*  
	*  3.返回：enumData对象，包括：enumData.display;enumData.name;enumData.code
 * @param eNumArray 后台枚举数组对象
 * @param code 枚举对象code
 * @returns 枚举json对象
 * @author 吕佳诚
 * @创建时间 2016年9月3日,星期六12:36
 */
GT.BootStrap.getEnumObjectByCode = function(eNumArray,code){
	var json = eNumArray;
	var enumData = {
			display	:	'',
			name	:	'',
			code	:	''
	}
	if( code == null || code.length == 0 || code == undefined ) return enumData;
	if( json == null || json.length == 0 || json == 'undefined') return enumData;
	json = jQuery.parseJSON(json);
	for(var i = 0; i < json.length; i++){
		if( code == json[i].code ){
			enumData.display = json[i].display;
			enumData.name = json[i].name;
			enumData.code = json[i].code;
		}
	}
	return enumData;
}

/**
 * 元素内容表格内不会换行
 * 
 * */
GT.BootStrap.formatToBlockShow =  function(value, row, index){
	if(value){
		return  "<span class='blockShow'>"+value+"</span>";
	}
}

Date.prototype.format = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}


GT.OpenUrlModal = function(url, width) {

	var no_cache = "_"+(new Date()).valueOf();
	if(url.indexOf("?")>-1){
		url += "&";
	}else{
		url += "?";
	}
	url += no_cache;

	var commonModal = $("#GT_CommonModal");
	if (commonModal.size() <= 0) {
		var html = '';
		html += '<div class="modal fade" id="GT_CommonModal">';
		html += ' <div class="modal-dialog">';
		html += '<div class="modal-content">';
		html += '</div><!-- /.modal-content -->';
		html += ' </div><!-- /.modal-dialog -->';
		html += '</div><!-- /.modal -->';
		$("body").append(html);
		commonModal = $("#GT_CommonModal");
		$('#GT_CommonModal>.modal-dialog').draggable({
	        cursor: 'move',
	        handle: '.box-header'
	    });
	}

	if (!width) {
		width = 600;
	}

	$(".modal-dialog", commonModal).width(width);
	commonModal.modal({
		remote : url,
		show : true
	});

	commonModal.on("hidden.bs.modal", function() {
		$(this).removeData("bs.modal");
	});
}


GT.CloseModal = function(callback) {
	var commonModal = $("#GT_CommonModal");
	var fn = function() {
		callback();
		commonModal.off("hidden.bs.modal",fn);
	}
	
	if(callback){
		commonModal.on("hidden.bs.modal",fn);
	}
	
	commonModal.modal('hide');
}


/* Nano Templates - https://github.com/trix/nano */
GT.nano = function(template, data) {
	return template.replace(/\{([\w\.]*)\}/g, function(str, key) {
		var keys = key.split("."), v = data[keys.shift()];
		for (var i = 0, l = keys.length; i < l; i++) {
			v = (typeof v !== "undefined" && v !== null) ? v[keys[i]] : "";
		}
		return (typeof v !== "undefined" && v !== null) ? v : "";
	});
}

GT.CallStringFunc = function(callback, data) {
	if (!callback) {
		console.log("callback is null!");
		return;
	}

	try {
		var fn = window[callback];
		fn(data);
	} catch (e) {
		console.log(e);
		console.log("call back error!   " + callback);
	}
}


/**
 * 将form表单封装到bootstrap table请求参数中
 * @param formObj form对象
 * @param params
 */
GT.Common.getFormData = function(formObj, params) {
   var oFrm = formObj.get(0);
	// console.info(oFrm);
	var len = oFrm.elements.length;
	var ret = {};
	if (params != undefined) {
		ret = params;
	}
	for (var i = 0; i < len; i++) {
		var oEle = oFrm.elements[i];
		if (oEle.type == "radio") {
			if (oEle.checked) {
				ret[oEle.name] = oEle.value;
			}
		} else if (oEle.type == "checkbox") {
			var curVal = ret[oEle.name];
			if (curVal == undefined) {
				ret[oEle.name] = [];
				if (oEle.checked) {
					ret[oEle.name].push(oEle.value);
				}
			} else {
				if (oEle.checked) {
					ret[oEle.name].push(oEle.value);
				}
			}
		} else {
			if (ret[oEle.name]) {
				var val = ret[oEle.name];
				if (oEle.value) {
					ret[oEle.name] = val + "," + oEle.value;
				}
			} else {
				if (oEle.value) {
					ret[oEle.name] = oEle.value;
				}
			}
		}
	}
	return ret;
}
/***
 * 显示日期下拉日期选项
 * @param selectJqueryObj selectjquery对象
 */
GT.Common.formatSearchDate = function(selectJqueryObj,dataTableId){
	var d = new Date();
	var cur = d.getMonth()+1;
	var html = '<option value="">全部日期</option><option value="thisweek" >本周</option><option value="lastweek">上周</option><option value="thismonth">本月</option><option value="lastmonth">上月</option>';
	var count = 2;
	
	for(var i = 1 ; i < (cur-1);i++){
		html += '<option  value="range'+count+'"  count = "'+count+'"  >'+((cur-1)-i)+'月</option>';
		count++;
	}
	
	$(html).appendTo(selectJqueryObj);
	selectJqueryObj.on("change",function(){
		showDate(this);
		if(dataTableId){
			GT.BootStrap.refreshData(dataTableId);
		}
	})
}


// 顶部购物车和消息数量 调用和显示
GT.Common.Badge = function() {
	$.ajax({
		async : false,
		dataType : 'json',
		url : APP_BASE + "/notify/events?t=" + (new Date()),
		type : "GET",
		// error:function(){alert("网络错误!");},
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		success : function(res) {
			if (res.data && res.data.noReadNotifyNum) {
				if (res.data.noReadNotifyNum > 99) {
					$(".right_floating_3 .badge").text("99+").show();
				} else {
					$(".right_floating_3 .badge")
							.text(res.data.noReadNotifyNum).show();
				}
			} else {
				$(".right_floating_3 .badge").hide();
			}

			if (res.data && res.data.goodsNum) {
				$(".header-container .btn-cart .badge").text(res.data.goodsNum)
						.show();
				;
			} else {
				$(".header-container .btn-cart .badge").hide();
			}
		}
	});
}

GT.Common.NumbaerUnit = function(val) {
	var units = new Array('个', '十', '百', '千', '万', '十万', '百万', '千万', '亿', '十亿','百亿', '千亿', '万亿');
	var number = parseInt(val);
	if (isNaN(val) || number <= 0) {
		return "";
	}
	var l = (number + "").length;
	if (l <= units.length) {
		var index = l - 1;
		return units[index];
	}
	return "";
}

GT.Common.NumbaerPopover = function() {
	$('input.GT_Number_Popover').keyup(function() {
		var val = $(this).val();
		var content = GT.Common.NumbaerUnit(val);
		$(this).attr("data-content", content);
		if (content != "") {
			$(this).popover('show');
		} else {
			$(this).popover('hide');
		}
	});

	$('input.GT_Number_Popover').keydown(function(e) {
		if (e.keyCode == 8) {
			$(this).popover('hide');
		}
	});

	$('input.GT_Number_Popover').mouseout(function() {
		$(this).popover('hide');
	});
}

/**
 * @Param datStr 格式必须是 yyyy-MM-dd HH:mm:ss 字符串
 *
 * **/
GT.Common.strToDate = function (datStr){
    return new Date(datStr.replace(/-/g,"/"));
}

/**
 * 打开登录框
 * 
 * @author 吕佳诚
 * @data 2016-03-11
 */
GT.Common.ShowLoginDialog = function() {
	var url = APP_BASE + "/loginbox";
	GT.OpenUrlModal(url);
	// 添加登录模态框样式
	//$(".modal-dialog").addClass("pop-login");
}

GT.Common.HideLoginDialog = function() {
	GT.CloseModal();
}

GT.Common.PopAlert = function(msg, type) {
	var alertType = "alert-success";
	if (type) {
		alertType = "alert-" + type;
	}
	var alertHtml = "<div class=\"alert GT_Alert ";
	alertHtml += alertType;
	alertHtml += "\" role=\"alert\">";
	alertHtml += '<button type="button" class="close" data-dismiss="alert" aria-label="Close">';
	alertHtml += '<span aria-hidden="true">&times;</span>';
	alertHtml += "</button><div class='text-center'>";
	alertHtml += msg;
	alertHtml += "</div></div>";
	var $alert = $(alertHtml);
	$("body").append($alert);
	$alert.css({
		position : "fixed",
		top : 0,
		left : 0,
		width : '100%',
		"z-index" : 10
	}).fadeIn();
	setTimeout(function() {
		$alert.fadeOut("slow", function() {
			$alert.remove();
		});
	}, 3000);
}



/*******************************************************************************
 * 提交时候，按钮禁用， 防止重复提交
 */
GT.Common.disDomRepText = function (obj, text) {
	// 原有text防止到oldText上面;
	$(obj).attr("oldText", $(obj).html()).attr("disabled", "disabled").html(
			text);
}

/*******************************************************************************
 * 提交时候，按钮禁用， 防止重复提交
 */
GT.Common.remDisDomRepText = function(obj) {
	// 原有text防止到oldText上面;
	$(obj).removeAttr("disabled").html($(obj).attr("oldText"));
}


/*******************************************************************************
 * 金额类型数据格式化，为xxx,xxx.22 （两位小数，小数点前每三位隔开） 使用方式：引入本函数，然后<span
 * class="format_money">金钱</span>,可以不使用span， 但是class必须是 format_money
 * 
 * @param parentId
 *            为了提高效率，可以指定搜索区
 ******************************************************************************/
GT.Common.formatTextToMoney = function (parentId) {
	var formatTextObj;
	if (parentId) {
		formatTextObj = $("#" + parentId + "  .format_money")
	} else {
		formatTextObj = $(".format_money");
	}

	formatTextObj.each(function() {
		var val = $.trim($(this).text());
		if (val.toString().indexOf('¥') == -1) {
			var money = Number(val).formatMoney();
			$(this).text(money);
		}
	})
}


/**
 * 处理根据ajax返回json ，提示相应的信息
 * 
 * @param data
 *            返回的数据对象
 * @param button
 *            触发事件的对象
 */
GT.Common.ajaxShowMesg = function (data) {
	if (!data.result) {
		if (data.errorCode == '401') {
			GT.Common.ShowLoginDialog();
		} else if (data.errorCode == '4031') {
			GT.Common.ShowNoCompanyDialog();
		} else if (!data.result) {
			gAlert('<span style="font-size: 14px;line-height:200% " >'
					+ data.message + '</span>', "提示");
		}
	}
}



/**
 * 删除多条数据
 *
 * @author 岳志明
 * @param baseUrl
 */
GT.BootStrap.delManyDataToBootstrap = function(bootstrapTableId, baseUrl) {
    var data = GT.BootStrap.isCheckDataToBootstrap(bootstrapTableId, "请选择要删除的数据！",1);
    if (data && data.length > 0) {
        gConfirm("您确定删除数据？", "提示", function(result) {
            if (result) {
                var url = baseUrl + "deleteManyOrOne";
                var ids = GT.BootStrap.checkDataIdsToBootstrap(bootstrapTableId);
                $.post(url, {
                    ids : ids
                }, function(data) {
                    if (data.result) {// 成功
                        $('#' + bootstrapTableId).bootstrapTable('refresh');
                    } else {
                        gAlert(data.message, "提示");
                    }
                }, 'json')
            }
        });
    }
}

/**
 *	获得选中的复选框value拼接字符串
 *	@param checkBoxName:复选框name
 */
GT.BootStrap.checkDataIdsToBootstrap =function (bootstrapTableId){
	/*var ids = "" ;
	var data = $("#"+bootstrapTableId).bootstrapTable('getSelections');
	for(var i = 0;i < data.length;i++ ){
		ids += data[i].id+",";
	}
	return ids.substr(0,ids.length-1);*/
	
	var selectRows = $("#"+bootstrapTableId).bootstrapTable('getSelections');
	var ids = $.map(selectRows, function (row) {
        return row.id;
    });
	return ids;
}




/**
 * 判断操作之前是否选择数据(bootstrapTable专用)
 *
 * @param msg:提示语
 * @param checkBoxName:复选框name
 * @param aimLeng:需要到达的长度
 * @retrun data 选择的数据集合 没有选择任何数据返回null
 */
GT.BootStrap.isCheckDataToBootstrap = function(bootstrapTableId, msg, aimLeng) {
    var data = $("#" + bootstrapTableId).bootstrapTable('getSelections');
    if (data.length < aimLeng) {
        gAlert(msg);
        return null;
    }
    return data;
}

/**
判断操作之前是否选择数据
@param msg:提示语
@param checkBoxName:复选框name
@param aimLeng:需要到达的长度
**/
 GT.BootStrap.isCheckData=function(checkBoxName,msg,aimLeng){
var checkLength = $("input[name="+checkBoxName+"]:checked").length;
if(checkLength < aimLeng){
	gAlert(msg);
	return false;
}
return true;
}

 
 /**
 获得选中的复选框value拼接字符串 以;进行拼接
 @param checkBoxName:复选框name
 **/
 GT.BootStrap.checkDataValues=function(checkBoxName){
 	var ids = "" ;
 	$("input[name="+checkBoxName+"]:checked").each(function(){
 		ids += $(this).val()+";";
 	})
 	return ids.substr(0,ids.length-1);
 }

/**
 * 根据format格式化日期
 * @param format 格式化字符串,类似：yyyy-MM-dd hh:mm:ss
 * @param value 日期
 * @returns
 */
GT.BootStrap.formatDate = function(value,format){
    if(value){
    	 return new Date(Number(value)).format(format);
    }
    return "";
}

/**
 * 转换为金钱格式“xxx，xxx”
 * **/
GT.BootStrap.formatToMoney=function (value, row, index){
	if(value){
		var money = Number(value).formatMoney();
		var html = "<span class='blockShow'>"+money+"</span>"
		return html;
	}
	
	return value;
}

/**
 * 也卖
 * 
 * @param obj
 */
GT.Purchase.sellToo = function(goodsId) {
	// var goodsId = $(goodsId).attr('goods-id');
	// var url = APP_BASE+"/goods/InGoods.json?id="+goodsId;
	// //由于地址过滤，所以放入IndexAction中 by 吕佳诚
	var url = APP_BASE + "/InGoods.json?id=" + goodsId;
	$.post(url, function(data) {
		if (data.result == true) {// 成功
			gAlert("添加成功");
		} else {
            GT.Common.ajaxShowMesg(data);
		}
	}, 'json')
}


/**
 * 时间格式化
 * **/
GT.BootStrap.formatToYYYY = function(value, row, index){
    if(value){
        return "<span class='blockShow'>"+new Date(Number(value)).format('yyyy')+"</span>";
    }
    return "";
}
/**
 * 时间格式化
 * **/
GT.BootStrap.formatToYYYYMMDD = function(value, row, index){
    if(value){
        return "<span class='blockShow'>"+new Date(Number(value)).format('yyyy-MM-dd')+"</span>";
    }
    return "";
}

/**
 * 时间格式化
 * **/
GT.BootStrap.formatToYYYYMMDDHHMMSS = function (value, row, index){
    if(value){
        return "<span class='blockShow'>"+new Date(Number(value)).format('yyyy-MM-dd hh:mm:ss')+"</span>";
    }
    return "";
}

/**
 * 时间格式化
 * **/
GT.BootStrap.formatToYYYYMMDDHHMM = function (value, row, index){
    if(value){
        return "<span class='blockShow'>"+new Date(Number(value)).format('yyyy-MM-dd hh:mm')+"</span>";
    }
    return "";
}


/**
 * 加入商品收藏,通过对象的goods-id获得goodsId
 *
 * @param obj
 *            (页面点击对象如a标签) 使用方法：（必须有 goods-id属性） <a href="javascript:void(0) "
 *            goods-id="商品id" onclick="addFavoriteGoods(this)" >
 */
GT.Purchase.addFavoriteGoods = function (obj) {
    var goodsId = $(obj).attr('goods-id');
    if (!goodsId || $(obj).hasClass("select")) {
        alert("您已经收藏过该商品");
        return;
    }

    // var url = APP_BASE+"/favoriteGoods/addFavoriteGoods/"+goodsId;
    // //由于地址过滤，所以放入IndexAction中 by 吕佳诚
    var url = APP_BASE + "/addFavoriteGoods/" + goodsId;
    $.post(url, function(data) {
        if (data.result) {// 成功
            $(obj).addClass("select");
            gAlert("收藏成功");
        } else {
            GT.Common.ajaxShowMesg(data);
        }
    }, 'json')
}


GT.Purchase.addFavoriteMaterial = function (obj) {
    var materialId = $(obj).attr('material-id');
    // var url = APP_BASE+"/favoriteGoods/addFavoriteGoods/"+goodsId;
    // //由于地址过滤，所以放入IndexAction中 by 吕佳诚
    var url = APP_BASE + "/addFavoriteMaterial/" + materialId;
    $.post(url, function (data) {
        if (data.result) {// 成功
            gAlert("收藏成功");
        } else {
            GT.Common.ajaxShowMesg(data);
        }
    }, 'json')
}

/**
 * @author yong
 * @param places
 *            金额
 * @param symbol
 *            前缀 如 $ 目前没有用该属性
 * @param thousand
 *            分隔符 默认，
 * @param decimal
 *            小数点符号
 * @returns {String}
 */
// defaults: (2, "$", ",", ".")
Number.prototype.formatMoney = function(places, symbol, thousand, decimal) {
    places = !isNaN(places = Math.abs(places)) ? places : 2;
    symbol = symbol !== undefined ? symbol : "¥";
    // symbol = "¥";
    thousand = thousand || ",";
    decimal = decimal || ".";
    var number = this, negative = number < 0 ? "-" : "", i = parseInt(
            number = Math.abs(+number || 0).toFixed(places), 10)
        + "", j = (j = i.length) > 3 ? j % 3 : 0;
    return symbol
        + negative
        + (j ? i.substr(0, j) + thousand : "")
        + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thousand)
        + (places ? decimal + Math.abs(number - i).toFixed(places).slice(2)
            : "");
};
GT.BootStrap.initExpandTable = function(tableId,callback){

	var $table = $("#"+tableId+",table."+tableId);
	if($table.length > 0){
		
		$table.on('expand-row.bs.table', function (e, index, row, $detail) {
			replaceExpandTableIcon();
			//以防止该函数不存在报错
			try{
				callback(e, index, row, $detail);
			}catch (ex){ 
				
			}
			
	    });
		
		//展开执行函数
		$table.on('collapse-row.bs.table', function (e, index, row, $detail) {
			replaceExpandTableIcon();
	    });
		
		//翻页执行函数
		$table.on('page-change.bs.table', function (number, size) {
			replaceExpandTableIcon();
	    });
		
		//当加载完毕执行函数
		$table.on('load-success.bs.table', function (data) {
			replaceExpandTableIcon();
	    });
		
	}
	replaceExpandTableIcon();
}
/***
 * 替换成所需的标志
 */
function replaceExpandTableIcon(){

	$("a.detail-icon i").each(function(){
		
		if($(this).hasClass('glyphicon-menu-right')  ){
			$(this).html("<b>详 情</b>").removeClass("glyphicon-menu-right").css("width","40px");
			
		}else if($(this).hasClass('glyphicon-menu-up') && $(this).html() != '详 情'){
			$(this).html("<b>收 起</b>").removeClass("glyphicon-menu-up").css("width","40px");
		}
		
	});
}

/**
 * 金额类型数据格式化，为xxx,xxx.22  
 * （两位小数，小数点前每三位隔开）
 * 使用方式：引入本函数，然后<span class="format_money">金钱</span>,可以不使用span，
 *  但是class必须是 format_money
 *  @param parentId 为了提高效率，可以指定搜索区
 * **/

GT.Common.formatTextToMoney = function(parentId){
	var formatTextObj ;
	if(parentId){
		formatTextObj = $("#"+parentId+"  .format_money")
	}else{
		formatTextObj = $(".format_money");
	}
	
	formatTextObj.each(function(){
		var val = $.trim($(this).text());
		if(val.toString().indexOf('¥') == -1){
			var money  = Number(val).formatMoney();
			$(this).text(money);
		}
	})
	
}

/**
 * 删除一条数据
 * @param id 删除数据id
 */
GT.BootStrap.delOneBootStrapData = function(bootstrapTableId,baseUrl,id){
	gConfirm("您确定删除数据？", "提示", function(result) {
		if(result){
			var url = baseUrl + "deleteManyOrOne";
			$.post(url,{ids:id},function(data){
				if(data.result){//成功
					$('#'+bootstrapTableId).bootstrapTable('refresh');
				}else {
					gAlert(data.message,"提示");
					$('#'+bootstrapTableId).bootstrapTable('refresh');
				}
			},'json')
		}
	});
}


/**
 * 导入excel出错信息提示
 * @param list 错误信息列表
 * @returns
 */
function listToStr(list){
	var buffer = new Array();

	buffer.push("<ul style='overflow:none;overflow-y:auto;min-height:50px;max-height:200px;height:auto;padding:0;margin:0;'>");
	for(var i=0;i<list.length;i++){
		if( i % 2 == 0 ){
			buffer.push("<li style='background-color:#e0e0e0;text-decoration:none;list-style:none;list-height:28px;height:24px;font-size:14px;overflow:hidden;'>");
		}else{
			buffer.push("<li style='text-decoration:none;list-style:none;list-height:22px;list-height:28px;height:24px;font-size:14px;overflow:hidden;'>");
		}
		buffer.push(i+1 + ".&nbsp;");
		
		if(list[i].row != 0){
			buffer.push("第" + list[i].row + "行--&gt;" );
		}
		var listError=list[i].error;
		
		for(var j=0;j<listError.length;j++){
			if(listError[j].title=="normalError"){
				buffer.push(listError[j].errorMessage+"</li>");
			}else{
				buffer.push(listError[j].title + listError[j].errorMessage+"</li>");
			}
			
    	}
	}
	  
	return buffer.toString().replace(/,/g,'');  
}

/** 
 * 金额按千位逗号分割 
 * @character_set UTF-8 
 * @author Jerry.li(hzjerry@gmail.com) 
 * @version 1.2014.08.24.2143 
 *  Example 
 *  <code> 
 *      alert($.formatMoney(1234.345,'￥')); //=>￥1234.345 
 *      alert($.formatMoney(1234.345,'')); //=>￥1234.345 
 *      alert($.unformatMoney(-1,234.345,'￥')); //=>￥-1234.345 
 *      alert($.formatMoney(1234,'￥')); //=>￥1234.0
 *      alert($.formatMoney(2sdfsdfa,'￥')); //=>￥-0.0
 *  </code> 
 */  
(function($)  
{  
    $.extend({  
        /** 
         * 数字千分位格式化 
         * @public 
         * @param mixed mVal 数值 
         * @param string dolar 金额符号
         * @return string 
         */  
         //formatMoney:function(mVal, accuracy){  
         formatMoney:function(mVal,dolaraMark){
        	 mVal = mVal + "";
		 	if( mVal.indexOf(',') !=-1 ){ return mVal; } ;
            var fTmp = 0.00;//临时变量  
            var iFra = 0;//小数部分  
            var iInt = 0;//整数部分  
            var aBuf = new Array(); //输出缓存  
            var bPositive = true; //保存正负值标记(true:正数)  
            /** 
             * 输出定长字符串，不够补0 
             * <li>闭包函数</li> 
             * @param int iVal 值 
             * @param int iLen 输出的长度 
             */  
            function funZero(iVal, iLen){  
                var sTmp = iVal.toString();  
                var sBuf = new Array();  
                for(var i=0,iLoop=iLen-sTmp.length; i<iLoop; i++) { 
                    sBuf.push('0');  
				}
                sBuf.push(sTmp);  
                return sBuf.join('');  
            };  
  
			//if (typeof(iAccuracy) === 'undefined')  { iAccuracy = 2 ;  }
			
             bPositive = (mVal >= 0);//取出正负号  
            fTmp = (isNaN(fTmp = parseFloat(mVal))) ? 0 : Math.abs(fTmp);//强制转换为绝对值数浮点  
            //所有内容用正数规则处理  
            iInt = parseInt(fTmp); //分离整数部分  
            //iFra = parseInt((fTmp - iInt) * Math.pow(10,iAccuracy) + 0.5); //分离小数部分(四舍五入)  
			iFra = $.isNumeric( mVal.split(".")[1] ) ? mVal.split(".")[1]  : 0 ; 
		    do{  
                aBuf.unshift(funZero(iInt % 1000, 3));  
            }while((iInt = parseInt(iInt/1000)));  
            aBuf[0] = parseInt(aBuf[0]).toString();//最高段区去掉前导0  
          // return "￥" +((bPositive)?'':'-') + aBuf.join(',') +'.'+ ((0 === iFra)?'00':funZero(iFra, iAccuracy));  
		   return dolaraMark + ((bPositive)?'':'-') + aBuf.join(',') +'.'+iFra ;  
        },  
        /** 
         * 将千分位格式的数字字符串转换为浮点数 
         * @public 
         * @param string sVal 数值字符串 
         * @return float 
         */  
        unformatMoney:function(dolar,sVal){  
            var fTmp = parseFloat(sVal.replace(/,/g, ''));  
            return dolaraMark+ (isNaN(fTmp) ? 0 : fTmp);  
        },  
    });  
})(jQuery); 

/**
 * 根据type值切换tab,包括:甲供和联采等
 * @param type:bootstrapTableIds 类型：甲供和联采+表格ids，多个id逗号分隔
 * @author 吕佳诚
 */
GT.Common.changeTab = function(type,bootstrapTableIds) {
	$("#type").val(type); 
	
	var tableIdArray = bootstrapTableIds.split(",");
	
	for(var i = 0 ; i < tableIdArray.length ; i++){
		var table = $("#" + tableIdArray[i]);
		$("#" + tableIdArray[i]).bootstrapTable('getOptions').pageNumber = 1;
		if(typeof($(table).attr("data-toggle"))=="undefined"){
			$("#" + tableIdArray[i]).bootstrapTable().attr("data-toggle","table");
		}else{
			$("#" + tableIdArray[i]).bootstrapTable('refresh');
		}
		
	}
}

/**
 * 重置form表单
 * @param formId form元素id
 * @author 吕佳诚
 */
GT.Common.resertForm = function(formId){
	$("#"+formId)[0].reset();
}

