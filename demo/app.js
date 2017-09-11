var options = {
	"debug":"true",//true、false
}
var _result = function(data){
	alert(JSON.stringify(data));
}
//获取磁盘
window.plugins.disk.getDiskinfo(options,_result);
