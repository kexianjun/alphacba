<html>
	<head></head>
	<body>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Basic Setup</h3>
			</div>
			<div class="panel-body">
					
				<script type="text/javascript">
					jQuery(document).ready(function($)
					{
						$("#example-1").dataTable({
							aLengthMenu: [
								[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]
							]
						});
					});
				</script>
					
				<div id="example-1_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">
					<div class="row">
						<div class="col-xs-6">
							<div class="dataTables_length" id="example-1_length">
								<label>Show 
									<select name="example-1_length" aria-controls="example-1" class="form-control input-sm">
										<option value="10">10</option>
										<option value="25">25</option>
										<option value="50">50</option>
										<option value="100">100</option>
										<option value="-1">All</option>
									</select> entries
								</label>
							</div>
						</div>
						<div class="col-xs-6">
						<div id="example-1_filter" class="dataTables_filter">
						<label>
							Search:<input type="search" class="form-control input-sm" placeholder="" aria-controls="example-1">
						</label>
						</div>
						</div>
						</div>
					<table id="example-1" class="table table-striped table-bordered dataTable" cellspacing="0" width="100%" role="grid" aria-describedby="example-1_info" style="width: 100%;">
						<thead>
							<tr role="row">
								<th class="sorting_asc" tabindex="0" aria-controls="example-1" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Name: activate to sort column ascending" style="width: 142px;">
									用户ID
								</th>
								<th class="sorting" tabindex="0" aria-controls="example-1" rowspan="1" colspan="1" aria-label="Position: activate to sort column ascending" style="width: 142px;">
									用户名
								</th>
								<th class="sorting" tabindex="0" aria-controls="example-1" rowspan="1" colspan="1" aria-label="Office: activate to sort column ascending" style="width: 110px;">
									用户类型
								</th>
								<th class="sorting" tabindex="0" aria-controls="example-1" rowspan="1" colspan="1" aria-label="Age: activate to sort column ascending" style="width: 80px;">
									用户状态
								</th>
								<th class="sorting" tabindex="0" aria-controls="example-1" rowspan="1" colspan="1" aria-label="Start date: activate to sort column ascending" style="width: 91px;">
									登录IP地址
								</th>
								<th class="sorting" tabindex="0" aria-controls="example-1" rowspan="1" colspan="1" aria-label="Salary: activate to sort column ascending" style="width: 90px;">
									登录MAC地址
								</th>
							</tr>
						</thead>
						
						<tfoot>
							<tr>
								<th rowspan="1" colspan="1">
									用户ID
								</th>
								<th rowspan="1" colspan="1">
									用户名
								</th>
								<th rowspan="1" colspan="1">
									用户类型
								</th>
								<th rowspan="1" colspan="1">
									用户状态
								</th>
								<th rowspan="1" colspan="1">
									登录IP地址
								</th>
								<th rowspan="1" colspan="1">
									登录MAC地址
								</th>
							</tr>
						</tfoot>
						<tbody>
							<tr role="row" class="odd">
								<td class="sorting_1">
									${userInfo.userId}
								</td>
								<td>
									${userInfo.userName}
								</td>
								<td>
									${userInfo.userType}
								</td>
								<td>
									${userInfo.userStatus}
								</td>
								<td>
									${userInfo.loginIp}
								</td>
								<td>
									${userInfo.loginMac}
								</td>
							</tr>
						</tbody>
					</table>
					<div class="row">
					<div class="col-xs-6">
						<div class="dataTables_info" id="example-1_info" role="status" aria-live="polite">
						Showing 1 to 10 of 57 entries</div></div>
							<div class="col-xs-6">
								<div class="dataTables_paginate paging_simple_numbers" id="example-1_paginate">
									<ul class="pagination">
										<li class="paginate_button previous disabled" aria-controls="example-1" tabindex="0" id="example-1_previous">
											<a href="#">Previous</a>
										</li>
										<li class="paginate_button active" aria-controls="example-1" tabindex="0"><a href="#">
											1</a>
										</li>
										<li class="paginate_button " aria-controls="example-1" tabindex="0">
											<a href="#">2</a>
										</li>
										<li class="paginate_button " aria-controls="example-1" tabindex="0">
											<a href="#">3</a>
										</li>
										<li class="paginate_button " aria-controls="example-1" tabindex="0">
											<a href="#">4</a>
										</li>
										<li class="paginate_button " aria-controls="example-1" tabindex="0">
											<a href="#">5</a>
										</li>
										<li class="paginate_button " aria-controls="example-1" tabindex="0">
											<a href="#">6</a>
										</li>
										<li class="paginate_button next" aria-controls="example-1" tabindex="0" id="example-1_next">
											<a href="#">Next</a>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
		</div>
	</body>
</html>