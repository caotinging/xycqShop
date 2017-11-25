/*
 * 实现从base_dict数据字典表中异步获取数据
 * 动态添加到页面中指定的select中。
 */
function loadFromBaseDict(selectId, typeId) {
	$.post(
		"/${pageContext.request.contextPath }/baseDictAction",
		{"dict_type_code":typeId},
		function(baseDictList) {
			if(baseDictList != null) {
				for (var i = 0; i < baseDictList.length; i++) {
					var $option = $("<option value="+baseDictList[i].dict_id+">"+baseDictList[i].dict_item_name+"</option>");
					$("#"+selectId).append($option);
				}
			}
		},
		"json"
	);
	
}