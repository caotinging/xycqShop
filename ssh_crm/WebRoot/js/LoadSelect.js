/*
 * 实现从base_dict数据字典表中异步获取数据
 * 动态添加到页面中指定的select中。
 */
function loadFromBaseDict(selectId, typeId, custDictId) {
	$.post(
		"${pageContext.request.contextPath}/baseDictAction",
		{"dict_type_code":typeId},
		function(baseDictList) {
			if(baseDictList != null) {
				for (var i = 0; i < baseDictList.length; i++) {
					var $option = $("<option value="+baseDictList[i].dict_id+">"+baseDictList[i].dict_item_name+"</option>");
					if(baseDictList[i].dict_id == custDictId) {
						$option.attr("selected","selected");
					}
					$("#"+selectId).append($option);
				}
			}
		},
		"json"
	);
}

function selectCustomer(cust_id, cust_name,loadlkm) {
	//获取打开此窗口的父窗口
	var win = window.opener;
	//获取父窗口的document对象
	var document = win.document;
	
	document.getElementById("cust_id_Hbtn").value=cust_id;
	document.getElementById("cust_name_Btn").value=cust_name;
	
	window.close();
}
