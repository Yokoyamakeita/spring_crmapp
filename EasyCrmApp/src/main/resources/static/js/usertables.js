$(document).ready(function(){
	$("#user-table").dataTable({
	language:{
		url:"/webjars/datatables-plugins/i18n/Japanese.json"
	},
	dom:"Bfrtip",
	buttons:["excelHtml5","csvHtml5","print"]
});
});

//この画面が表示されたら、
//お手数おかけしますが、ブラウザバックで再度ログインをお試しください。