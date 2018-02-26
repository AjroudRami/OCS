

var ledRequest = function(ip, redColor, greenColor, blueColor) {
	xhttp.open("POST", "ajax_test.asp", true);
	xhttp.setRequestHeader("Content-type", "application/application/json");
	var color = {
		red:redColor,
		green:greenColor,
		blue:blueColor
	};
	xhttp.send(color);
}