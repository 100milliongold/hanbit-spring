/**
 * 
 */
$(function() {
	$('#btn-write').on('click',function() {
		location.href = '/board/write';
	});
	
	$('#btn-search').on('click',function() {
		var keyword = $('#txt-keyword').val();
		
		$.ajax({
			url: '/api/search',
			method:'GET',
			data:{
				keyword : keyword
			},
			success: function(result) {
				setList(result);
			}
		});
	});
});

function setList(list) {
	$('tbody').empty();
	for(var i=0;i<list.length;i++){
		var row = list[i];
		var rowHtml = '<tr>';
		rowHtml += '<th>'+ row.no +'</td>';
		rowHtml += '<th>'+ row.title +'</td>';
		rowHtml += '<th>'+ row.views +'</td>';
		rowHtml += '<th>'+ row.updateDt +'</td>';
		rowHtml += '</tr>';
		$('tbody').append(rowHtml);
	}
}