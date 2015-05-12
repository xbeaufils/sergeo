<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<img src="<%=request.getContextPath()%>/images/clock.png" onclick="showTimePicker()"/>
		<div id="timepicker" class="timepicker">
		<table width="100%">
		<tr><td>
			<table class="hour">
			<tr>
				<td onclick="selectHour(this);">00</td><td onclick="selectHour(this);">01</td><td onclick="selectHour(this);">02</td><td onclick="selectHour(this);">03</td><td onclick="selectHour(this);">04</td><td onclick="selectHour(this);">05</td>
				<td onclick="selectHour(this);">06</td><td onclick="selectHour(this);">07</td><td onclick="selectHour(this);">08</td><td onclick="selectHour(this);">09</td><td onclick="selectHour(this);">10</td><td onclick="selectHour(this);">11</td>
			</tr>
			<tr>
				<td onclick="selectHour(this);">12</td><td onclick="selectHour(this);">13</td><td onclick="selectHour(this);">14</td><td onclick="selectHour(this);">15</td><td onclick="selectHour(this);">16</td><td onclick="selectHour(this);">17</td>
				<td onclick="selectHour(this);">18</td><td onclick="selectHour(this);">19</td><td onclick="selectHour(this);">20</td><td onclick="selectHour(this);">21</td><td onclick="selectHour(this);">22</td><td onclick="selectHour(this);">23</td>
			</tr>
			</table>
			</td>
		</tr>
		<tr><td>
			<table class="minute">
			<tr>
				<td onclick="selectMinute(this);">:00</td><td onclick="selectMinute(this);">:05</td><td onclick="selectMinute(this);">:10</td><td onclick="selectMinute(this);">:15</td><td onclick="selectMinute(this);">:20</td><td onclick="selectMinute(this);">:25</td>
			</tr>
			<tr>
				<td onclick="selectMinute(this);">:30</td><td onclick="selectMinute(this);">:35</td><td onclick="selectMinute(this);">:40</td><td onclick="selectMinute(this);">:45</td><td onclick="selectMinute(this);">:50</td><td onclick="selectMinute(this);">:55</td>
			</tr>
			</table>
			</td>
		</tr>
		</table>
		</div>	
</body>
</html>