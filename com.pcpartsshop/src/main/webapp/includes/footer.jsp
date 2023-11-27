	<div class="container">	
		<footer>
			<%@ page import="java.util.GregorianCalendar, java.util.Calendar" %>
			<%
				GregorianCalendar currentDate = new GregorianCalendar();
				int currentYear = currentDate.get(Calendar.YEAR);
			%>
			<p>&copy; Copyright <%=currentYear%> Mot Thanh Vien Inc. All rights reserved.</p>
		</footer>
	</div>
</body>