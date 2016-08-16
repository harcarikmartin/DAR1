	$(document).ready( function () {
  	    //https://datatables.net/
  	     // pre nezobrazovanie ponuky search 
  	  	 //  $('#table_id').dataTable({bFilter: false, bInfo: false});	
  		$('#topicsTable').dataTable({
  				"columnDefs" : [ {
  					"targets" : 'no-sort',
  					"orderable" : false,
  					"searchable": false,
  				} ]
  			});
  		$('#tasksTable').dataTable({
  			"columnDefs" : [ {
  				"targets" : 'no-sort',
  				"orderable" : false,
  				"searchable": false,
  			} ]
  		});

  			$('div.dataTables_filter input').addClass('searchInTable')
  			$('div.dataTables_length select').addClass('numberOfRows')
  		});