
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Search School Profile<!-- <small>( * fields are mandatory)</small> -->
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary">
			<div class="box-header">
				<h3 class="box-title">School Profile</h3>
				<div class="col-md-6 pull-right">
					<a href="addSchoolProfile" class="btn btn-primary pull-right">Add School Profile</a>
				</div>
			</div>
			
			<form action="resultSchoolProfile" method="post" class="">
				<div class="box-body">
					<div class="col-md-12">
								<div class="form-group">

										<div class="col-md-12">
											<div class="col-md-4">
											<fieldset class="form-group">
												<label>School Code </label>
												<div class="input-group">
													<input type="text" class="form-control" name="schoolCode" />
													<div class="input-group-addon greenFont" style="font-size: 18px;"></div>
													
												</div>
											</fieldset>
											</div>
											<div class="col-md-8">
											<fieldset class="form-group">
												<label>School Name </label> 
												<input type="text" class="form-control" id="name" name="name" />
											</fieldset>
											</div>
										</div>
										<div class="col-md-9">
											<div class="col-md-4">
												<fieldset class="form-group">
													<label>Start Year </label>
													<input type="text" class="form-control" id="startYear"
														name="startYear" />
												</fieldset>
											</div>
											<div class="col-md-4">
												<fieldset class="form-group">
													<label>Registration Date</label> <span class="red">&nbsp;</span><input type="text"
														class="form-control" id="registrationDate" name="registrationDate" />
												</fieldset>
											</div>
										</div>
										<div class="col-md-9">
											<div class="col-md-6">
												<fieldset class="form-group">
													<label>Email </label>
													<input type="text" class="form-control" id="email"
														name="email" />
												</fieldset>
											</div>
											<div class="col-md-6">
												<fieldset class="form-group">
													<label>Website</label> <span class="red">&nbsp;</span><input type="text"
														class="form-control" id="website" name="website" />
												</fieldset>
											</div>
										</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<fieldset class="form-group">
											<label>Address </label>
											<input type="text" class="form-control" id="address"
												name="address" />
										</fieldset>
									</div>
									<div class="col-md-3">
										<fieldset class="form-group">
											<label>District</label> <span class="red">&nbsp;</span><input type="text"
												class="form-control" id="district" name="district" />
										</fieldset>
									</div>
									<div class="col-md-3">
										<fieldset class="form-group">
											<label>City</label> <span class="red">&nbsp;</span><input type="text"
												class="form-control" id="city" name="city" />
										</fieldset>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-4">
										<fieldset class="form-group">
											<label>State </label>
											<input type="text" class="form-control" id="state"
												name="state" />
										</fieldset>
									</div>
									<div class="col-md-4">
										<fieldset class="form-group">
											<label>Postcode </label>
											<input type="text" class="form-control" id="postcode"
												name="postcode" />
										</fieldset>
									</div>
									<div class="col-md-3">
										<fieldset class="form-group">
											<label>Phone</label> <span class="red">&nbsp;</span><input type="text"
												class="form-control" id="phone" name="phone" />
										</fieldset>
									</div>
									<div class="col-md-3">
										<fieldset class="form-group">
											<label>Fax</label> <span class="red">&nbsp;</span><input type="text"
												class="form-control" id="fax" name="fax" />
										</fieldset>
									</div>
								</div>

								
						</div>
				</div>
				<!-- /.box-body -->

				<div class="box-footer">
					<input type="submit" class="btn btn-primary" value="Submit" />
				</div> 
			</form>
			
		</div>
	</section>
</div>

