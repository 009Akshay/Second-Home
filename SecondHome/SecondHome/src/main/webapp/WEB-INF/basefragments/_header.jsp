<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
	<nav class="navbar navbar-expand-lg"
		style="height: 59px; background-color: #074235;">
		<div class="container-fluid">
			<a class="navbar-brand " href="#"
				style="font-size: 26px; font-family: serif; color:white;">
				<span style="font-variant:petite-caps;">Second</span> 
				<span style="font-variant:petite-caps; color:#ff9900">Home</span> 
				</a>


			<div class="collapse navbar-collapse" id="navbarText">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				</ul>
				<c:if test="${sessionScope.user != null}">
				<span class="navbar-text" style="color: white;font-size: 14px; font-variant-caps:petite-caps;"> Hii,${sessionScope.user.firstName}
				</span>
				</c:if>
				<c:if test="${sessionScope.user == null}">
				<span class="navbar-text" style="color: white;font-size: 14px; font-variant-caps:petite-caps;"> Hii,Guest
				</span>
				</c:if>
			</div>
		</div>
	</nav>
	<div class="shadow bg-body rounded">
		<nav class="navbar navbar-expand-lg "
			style="height: 39px; background-color: #0a4f13c7;">
			<div class="container-fluid">
				
				<div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav">
						<li class="nav-item linkSize"><a class="nav-link active link-light"
							aria-current="page" href="<c:url value="/welcome"/>">Home</a></li>
						<c:if test="${sessionScope.user != null}">
							<c:if test="${sessionScope.user.roleId == 1}">
							<li class="nav-item linkSize"><a class="nav-link link-light" href="<c:url value="/ctl/user"/>">Add Warden</a></li>
						<li class="nav-item linkSize"><a class="nav-link link-light" href="<c:url value="/ctl/user/search"/>">Warden List</a></li>
						<li class="nav-item linkSize"><a class="nav-link link-light" href="<c:url value="/ctl/hostel"/>">Add Hostelites</a></li>
						<li class="nav-item linkSize"><a class="nav-link link-light" href="<c:url value="/ctl/hostel/search"/>">Hostelites List</a></li>
						
						<li class="nav-item linkSize"><a class="nav-link link-light" href="<c:url value="/ctl/room"/>">Add Room</a></li>
						<li class="nav-item linkSize"><a class="nav-link link-light" href="<c:url value="/ctl/room/search"/>">Room List</a></li>
						
						<li class="nav-item linkSize"><a class="nav-link link-light" href="<c:url value="/ctl/warden"/>">Allot Warden</a></li>
						<li class="nav-item linkSize"><a class="nav-link link-light" href="<c:url value="/ctl/warden/search"/>">Allot Wardens</a></li>	
		
						</c:if>
						
						<c:if test="${sessionScope.user.roleId == 3}">
						<li class="nav-item linkSize"><a class="nav-link link-light" href="<c:url value="/ctl/hostel/search"/>">Hostelites List</a></li>
						<li class="nav-item linkSize"><a class="nav-link link-light" href="<c:url value="/ctl/room/search"/>">Room List</a></li>
							<li class="nav-item linkSize"><a class="nav-link link-light" href="<c:url value="/ctl/visitor"/>">Add Visitor</a></li>
						<li class="nav-item linkSize"><a class="nav-link link-light" href="<c:url value="/ctl/visitor/search"/>">Visitor List</a></li>
						<li class="nav-item linkSize"><a class="nav-link link-light" href="<c:url value="/ctl/application/search"/>">Applications</a></li>
						<li class="nav-item linkSize"><a class="nav-link link-light" href="<c:url value="/ctl/allotment/search"/>">Allotments</a></li>
						</c:if>
						
						<c:if test="${sessionScope.user.roleId == 2}">
						<li class="nav-item linkSize"><a class="nav-link link-light" href="<c:url value="/ctl/hostel/search"/>">Hostelites List</a></li>
						<li class="nav-item linkSize"><a class="nav-link link-light" href="<c:url value="/ctl/room/search"/>">Room List</a></li>
						<li class="nav-item linkSize"><a class="nav-link link-light" href="<c:url value="/ctl/application"/>">Application</a></li>
						<li class="nav-item linkSize"><a class="nav-link link-light" href="<c:url value="/ctl/allotment/search"/>">Allotments</a></li>
						</c:if>
						
						</c:if>
						<c:if test="${sessionScope.user == null}">
						<li class="nav-item linkSize"><a class="nav-link link-light" href="<c:url value="/aboutUs"/>">AboutUs</a></li>
						<li class="nav-item linkSize"><a class="nav-link link-light" href="<c:url value="/contactUs"/>">ContactUs</a></li>
						</c:if>
					</ul>
				</div>
				<ul class="nav justify-content-end">
				<c:if test="${sessionScope.user != null}">
				
				
				
				<li class="nav-item linkSize"><a class="nav-link link-light" style="padding: 6px;"
						href="<c:url value="/profile"/>">My Profile</a></li>
				<li class="nav-item linkSize"><a class="nav-link link-light" style="padding: 6px;"
						href="<c:url value="/changepassword"/>">Change Password</a></li>
				
					<li class="nav-item linkSize"><a class="nav-link link-light" style="padding: 6px;"
						href="<c:url value="/login"/>">Logout</a></li>
				</c:if>
				<c:if test="${sessionScope.user == null}">
					<li class="nav-item linkSize"><a class="nav-link link-light" style="padding: 6px;"
						href="<c:url value="/login"/>">SignIn</a></li>
					<li class="nav-item linkSize"><a class="nav-link link-light" style="padding: 6px;"
						href="<c:url value="/signUp"/>">SignUp</a></li>
				</c:if>
					
				</ul>
			</div>
		</nav>
	</div>
