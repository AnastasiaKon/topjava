<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body class="w3-light-grey">

<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Meals</h1>
</div>

<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <table border="2" >
            <c:forEach var="meal" items="${userMeals}">
                <tr bgcolor=${meal.excess ? "#ffb6c1" : "#8fbc8f"} >
                    <td>${meal.dateTime.format(dateTimeFormatter)}</td>
                    <td>${meal.description}</td>
                    <td>${meal.calories}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>