	$(document).ready( function () {
  	    //https://datatables.net/
  	     // pre nezobrazovanie ponuky search 
  	  	 //  $('#table_id').dataTable({bFilter: false, bInfo: false});	
  		$('#topicsTable').dataTable({
  				"columnDefs" : [ {
  					"targets" : 'no-sort',
  					"orderable" : false,
  					"searchable": false, "targets": [ 3, 4 ],
  				} ]
  			});
  		$('#tasksTable').dataTable({
  			"columnDefs" : [ {
  				"targets" : 'no-sort',
  				"orderable" : false,
  				"searchable": false, "targets": [ 2, 3 ],
  			} ]
  		});

  			$('div.dataTables_filter input').addClass('searchInTable')
  			$('div.dataTables_length select').addClass('numberOfRows')
  		});