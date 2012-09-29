document.addEventListener("deviceready", onDeviceReady, false);
function onDeviceReady() {
	document.addEventListener("backbutton", onBackKeyDown, false);
	vibrate(0);
}

function EventKeyPad(key) { // arrow clicked - this function calling
	if (key == 'up') {
		key = 38; // up (turn block ).
	} else if (key == 'down') {
		key = 40; // down
	} else if (key == 'esc') {
		key = 27; // esc
	} else if (key == 'space') {
		key = 32;
	}             // space
	else if (key == 'left') {
		key = 37; // left
	} else
		key = 39; // right

	window.plugins.myPlugin.sendData(Success, Error, "" + key);
}

function onBackKeyDown() { // back button clciked - this function calling
	navigator.notification.confirm('This Contorller Exit?', onBackKeyDownMsg,
			'Exit', 'cancel, Exit');
}

function onBackKeyDownMsg(result) { // back button callback function
	if (result == 2) {
		navigator.app.exitApp();
	}
}

function Success(result) { // phonegap plugin success callback function
	alert("success" + result);
}

function Error(result) { // phonegap plugin fail callback function
	alert("fail " + " why? "+result);
}

function vibrate(option) { // vibrate structure function
	switch (option) {
	case 0:
		// Game Start & Game End
		navigator.notification.vibrate(3000);
		alert(option);
		break;
	case 1:
		// Remove four line
		navigator.notification.vibrate(1500);
		alert(option);
		break;
	case 2:
		// Remove one line
		navigator.notification.vibrate(500);
		alert(option);
		break;
	}
}
function na_preload_img()
{ 
  var img_list = na_preload_img.arguments;
  if (document.preloadlist == null) 
    document.preloadlist = new Array();
  var top = document.preloadlist.length;
  for (var i=0; i < img_list.length-1; i++) {
    document.preloadlist[top+i] = new Image;
    document.preloadlist[top+i].src = img_list[i+1];
  } 
}
function na_restore_img_src(name, nsdoc)
{
  var img = eval((navigator.appName.indexOf('Netscape', 0) != -1) ? nsdoc+'.'+name : 'document.all.'+name);
  if (name == '')
    return;
  if (img && img.altsrc) {
    img.src    = img.altsrc;
    img.altsrc = null;
  } 
}
function na_change_img_src(name, nsdoc, rpath, preload)
{ 
  var img = eval((navigator.appName.indexOf('Netscape', 0) != -1) ? nsdoc+'.'+name : 'document.all.'+name);
  if (name == '')
    return;
  if (img) {
    img.altsrc = img.src;
    img.src    = rpath;
  } 
}
