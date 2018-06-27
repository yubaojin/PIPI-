Date.prototype.format = function(format){ 
    var o =  { 
    "M+" : this.getMonth()+1, // month
    "d+" : this.getDate(), // day
    "h+" : this.getHours(), // hour
    "m+" : this.getMinutes(), // minute
    "s+" : this.getSeconds(), // second
    "q+" : Math.floor((this.getMonth()+3)/3), // quarter
    "S" : this.getMilliseconds() // millisecond
    };
    if(/(y+)/.test(format)){ 
    	format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
    }
    for(var k in o)  { 
	    if(new RegExp("("+ k +")").test(format)){ 
	    	format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
	    } 
    } 
    return format; 
};

var PP = PIPI = {
	// 编辑器参数
	kingEditorParams : {
		// 指定上传文件参数名称
		filePostName  : "uploadFile",
		// 指定上传文件请求的url。
		uploadJson : '/pic/upload',
		// 上传类型，分别为image、flash、media、file
		dir : "image"
	},
	// 格式化时间
	formatDateTime : function(val,row){
		var now = new Date(val);
    	return now.format("yyyy-MM-dd hh:mm:ss");
	},
	// 格式化连接
	formatUrl : function(val,row){
		if(val){
			vals=val.split(",");
			var s="";
			for (var i = 0; i < vals.length; i++) {
				s+="<a href='"+vals[i]+"' target='_blank'>查看"+i+"</a> ";	
			}
			return s;			
		}
		return "";
	},
	// 格式化价格
	formatPrice : function(val,row){
		return val;
	},
	// 格式化商品的状态
	formatItemStatus : function formatStatus(val,row){
        if (val == 1){
            return '正常';
        } else if(val == 3){
        	return '<span style="color:red;">下架</span>';
        } else {
        	return '未知';
        }
    },
 // 格式化评价的状态
	formatEavlStatus : function formatStatus(val,row){
        if (val == 1){
        	return '<span style="color:red;">差评</span>';
        } else if(val == 2){
        	return '<span style="color:blue;">中评</span>';
        } else if(val == 3){
        	return '<span style="color:yellow;">好评</span>';
        }else{
        	return '未知';
        }
    },
 // 格式化订单的状态
	formatOrderStatus : function formatStatus(val,row){
        if (val == 1){
        	return '<span style="color:red;">未付款</span>';
        } else if(val == 2){
        	return '<span style="color:blue;">已付款</span>';
        } else if(val == 3){
        	return '<span style="color:#f47920;">已接单</span>';
        }else if(val == 4){
        	return '<span style="color:#2a5caa;">已发货</span>';
        }else if(val == 5){
        	return '<span style="color:#840228;">已完成</span>';
        }else if(val == 6){
        	return '<span style="color:#ea66a6;">已关闭</span>';
        }if(val >= 7){
        	return '<span style="color:yellow;">已取消</span>';
        }else{
        	return '未知';
        }
    },
    
    init : function(data){
    	// 初始化图片上传组件
    	this.initOnePicUpload(data);
    	// 初始化选择类目组件
    	this.initItemCat(data);
    },

    
    // 初始化选择类目组件
    initItemCat : function(data){
    	$(".selectItemCat").each(function(i,e){
    		var _ele = $(e);
    		if(data && data.menu){
    			_ele.after("<span style='margin-left:10px;'>"+data.menu+"</span>");
    		}else{
    			_ele.after("<span style='margin-left:10px;'></span>");
    		}
    		_ele.unbind('click').click(function(){
    			$("<div>").css({padding:"5px"}).html("<ul>")
    			.window({
    				width:'500',
    			    height:"450",
    			    modal:true,
    			    closed:true,
    			    iconCls:'icon-save',
    			    title:'选择类目',
    			    onOpen : function(){
    			    	var _win = this;
    			    	$("ul",_win).tree({
    			    		url:'/menu/list',
    			    		animate:true,
    			    		
    			    		onClick : function(node){
    			    			if($(this).tree("isLeaf",node.target)){
    			    				// 填写到mid中
    			    				_ele.parent().find("[name=menuId]").val(node.id);
    			    				_ele.next().text(node.text).attr("menuId",node.id);
    			    				$(_win).window('close');
// if(data && data.fun){
// data.fun.call(this,node);
// }
    			    			}
    			    		}
    			    	});
    			    },
    			    onClose : function(){
    			    	$(this).window("destroy");
    			    }
    			}).window('open');
    		});
    	});
    },
    
    createEditor : function(select){
    	return KindEditor.create(select, PP.kingEditorParams);
    },
    
    /**
	 * 创建一个窗口，关闭窗口后销毁该窗口对象。<br/>
	 * 
	 * 默认：<br/> width : 80% <br/> height : 80% <br/> title : (空字符串) <br/>
	 * 
	 * 参数：<br/> width : <br/> height : <br/> title : <br/> url : 必填参数 <br/>
	 * onLoad : function 加载完窗口内容后执行<br/>
	 * 
	 * 
	 */
    createWindow : function(params){
    	$("<div>").css({padding:"5px"}).window({
    		width : params.width?params.width:"80%",
    		height : params.height?params.height:"80%",
    		modal:true,
    		title : params.title?params.title:" ",
    		href : params.url,
		    onClose : function(){
		    	$(this).window("destroy");
		    },
		    onLoad : function(){
		    	if(params.onLoad){
		    		params.onLoad.call(this);
		    	}
		    }
    	}).window("open");
    },
    
    closeCurrentWindow : function(){
    	$(".panel-tool-close").click();
    },
    
    getSelectionsIds : function (select){
    	var list = $(select);
    	var sels = list.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		if(sels[i].id){
    			ids.push(sels[i].id);
    		}else if(sels[i].orderId){
    			ids.push(sels[i].orderId);
    		}
    		
    	}
    	ids = ids.join(",");
    	return ids;
    },
    
    /**
	 * 初始化单图片上传组件 <br/> 选择器为：.onePicUpload <br/> 上传完成后会设置input内容以及在input后面追加<img>
	 */
    initOnePicUpload : function(data){
    	$(".onePicUpload").click(function(){
			var _self = $(this);
			KindEditor.editor(PP.kingEditorParams).loadPlugin('image', function() {
				this.plugin.imageDialog({
					showRemote : false,
					clickFn : function(url, title, width, height, border, align) {
						var input = _self.siblings("input");
						input.parent().find("img").remove();
						input.val(url);
						input.after("<a href='"+url+"' target='_blank'><img src='"+url+"' width='80' height='50'/></a>");
						this.hideDialog();
					}
				});
			});
		});
    },
    
//    initPic:function(data){
//    	$(".onePicUpload").each(function(i,e){
//    		var _ele = $(e);
//    		// 回显图片
//        	if(data && data.pic){
//        		var input = _ele.siblings("input");
//    			input.parent().find("img").remove();
//    			input.val(data.pic);
//    			input.after("<a href='"+data.pic+"' target='_blank'><img src='"+data.pic+"' width='80' height='50'/></a>");
//        	}
//    	});
//    }
};
