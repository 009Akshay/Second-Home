<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<br>
<div class="container">
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item linkSize"><i
				class="fas fa-tachometer-alt"></i> <a class="link-dark"
				href="<c:url value="/welcome"/>">Home</a></li>
			<li class="breadcrumb-item linkSize"><i class="fa fa-list"></i>
				<a class="link-dark" href="<c:url value="/ctl/policy/category"/>">Category</a></li>
			<li class="breadcrumb-item linkSize"><i class="fa fa-list-alt"></i>
				<a class="link-dark" href="<c:url value="/ctl/policy/search"/>">Policy</a></li>
			<li class="breadcrumb-item linkSize active" aria-current="page">
				<i class="fa fa-arrow-right" aria-hidden="true"></i> Payment
			</li>
		</ol>
	</nav>
</div>
<hr>

<div class="container">
	<div class="card shadow  mb-5 bg-body rounded" style="width: 700px">
		<h5 class="card-header"
			style="background-color: #00061df7; color: white;">Payment</h5>
		<b><%@ include file="businessMessage.jsp"%></b>
		<div class="card-body">
			<sf:form role="form"
				action="${pageContext.request.contextPath}/ctl/buyPolicy" method="post"
				modelAttribute="form">

				<div class="row">
					<div class="col"><h3>Policy Name :</h3></div>
					<div class="col">${pdto.name}</div>
				</div>
				<hr>
				<div class="row">
					<div class="col"><h5>Policy Price :</h5></div>
					<div class="col">${pdto.price}</div>
				</div>
				
				<div class="row">
					<div class="col"><h5>ROI :</h5></div>
					<div class="col">${pdto.roi}</div>
				</div>
				<hr>
				<div class="row">
					<div class="col"><h4>Total Amount :</h4></div>
					<div class="col">${pdto.price}</div>
				</div>
				<hr>
				<input type="submit" name="operation"
					class="btn btn-primary pull-right" value="Pay">
			</sf:form>
		</div>
	</div>
</div>