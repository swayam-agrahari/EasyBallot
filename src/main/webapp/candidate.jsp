
<%@ taglib prefix="c" uri="jakarta.tags.core"%>


<!DOCTYPE html>
<html lang="en">
<head>
<title>Online Voting System</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: #2C3E50;">
			<div class="text-center">
				<a class="text-center" href="dashboard"
					style="text-decoration: none; color: white; font-size: xx-large; text-align: center;align-items: center;">Online
					Voting System</a>

			</div>


		</nav>
	</header>
	<br>

	<div class="row">


		<div class="container">
			<h3 class="text-center">List of Candidates</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/list" class="btn btn-success">
					Get Candidates</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Party</th>
						<th>Status</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach var="candidate" items="${listUser}">

						<tr>
							<td><c:out value="${candidate.id}" /></td>
							<td><c:out value="${candidate.name}" /></td>
							<td><c:out value="${candidate.party}" /></td>
							<td><c:choose>
									<c:when
										test="${candidate.approved != true && candidate.rejected != true}">
										<c:out value="Pending" />
									</c:when>
									<c:when test="${candidate.approved == true}">
										<c:out value="Accepted" />
									</c:when>
									<c:otherwise>
										<c:out value="Rejected" />
									</c:otherwise>
								</c:choose></td>
							<td>
							    <c:choose>
							        <c:when test="${candidate.approved || candidate.rejected}">
							            <!-- Don't render buttons if either approved or rejected is true -->
							        </c:when>
							        <c:otherwise>
							            <!-- Render buttons if neither approved nor rejected is true -->
							            <a href="update?id=${candidate.id}">Approve</a>
							            &nbsp;&nbsp;&nbsp;&nbsp;
							            <a href="reject?id=${candidate.id}">Reject</a>
							        </c:otherwise>
							    </c:choose>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
