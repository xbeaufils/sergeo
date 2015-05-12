	function minuteDown(idMinute) {
		var minute = parseInt(document.getElementById(idMinute).value, 10);
		minute -= 5;
		if  (minute == -5)
			minute=55;
		if (minute <10)
			document.getElementById(idMinute).value = "0" + minute;
		else
			document.getElementById(idMinute).value = minute;
	}

	function minuteUp(idMinute) {
		var minute = parseInt(document.getElementById(idMinute).value, 10);
		minute += 5;
		if  (minute == 60)
			minute=0;
		if (minute <10)
			document.getElementById(idMinute).value = "0" + minute;
		else
			document.getElementById(idMinute).value = minute;
	}
	
	function hourDown(idHour) {
		var hour = parseInt(document.getElementById(idHour).value, 10);
		hour --;
		if  (hour == -1)
			hour=23;
		if (hour <10)
			document.getElementById(idHour).value = "0" + hour;
		else
			document.getElementById(idHour).value = hour;
	}

	function hourUp(idHour) {
		var hour = parseInt(document.getElementById(idHour).value, 10);
		hour ++;
		if  (hour == 24)
			hour=0;
		if (hour <10)
			document.getElementById(idHour).value = "0" + hour;
		else
			document.getElementById(idHour).value = hour;
	}
