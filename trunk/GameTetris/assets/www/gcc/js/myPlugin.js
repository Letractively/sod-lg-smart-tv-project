var myPlugin = function(){
	
	this.receiveData = function(onSuccess,onError,data){
		PhoneGap.exec(onSuccess,onError,"sod.service.myPlugin","receiveData",[data]);
	};
	this.sendData = function(onSuccess,onError,data){
		
		PhoneGap.exec(onSuccess,onError,"sod.service.myPlugin","sendData",[data]);
	};
//	this.TvSerch = function(onSuccess,onError,data){
//		PhoneGap.exec(onSuccess,onError,"sod.SERVICE.GCC_PHONEActivity","TvSerch",[data]);
//	};
}; 
PhoneGap.addConstructor(function(){
	PhoneGap.addPlugin('myPlugin',new myPlugin());
});