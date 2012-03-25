(function() {
	$(document).ready(function() {
		var baseUrl = basePath;
		
		$(".js-hotel-list").on("click", ".js-hotel-list .js-btn-delete", function() {
			$this = $(this);
			var hotelId = $this.data("id");
			
			var ok = confirm("Est‡ seguro que desea eliminar el hotel?");
			
			if (ok) {
				$.ajax({
					url: baseUrl + "hotels/" + hotelId.toString(),
					type: "DELETE",
					success: function() {
						$this.parents("tr:first").remove();
					}
				});
			}
		});
		
		$(".js-hotel-list").on("click", ".js-hotel-list .js-btn-edit", function() { 
			$this = $(this);
			
			var hotelId = $this.data("id");
			var hotelDescr = $this.data("description");
			var hotelName = $this.data("name");
			
			$("#hotel-modal > .modal-body > form").attr("action", "hotels/" + hotelId);
			
			$("textarea[name=hotel_description]").val(hotelDescr);
			$("input[name=hotel_name]").val(hotelName);
			
			$("#hotel-modal .js-submit-modal").text("Guardar Cambios");
			$("#hotel-modal > .modal-header > h3").text("Editar Hotel");
			
			$("#hotel-modal").modal("show");
		});
		
		$(".js-create-hotel").on("click", function() {
			$this = $(this);
			
			$("#hotel-modal > .modal-body > form").attr("action", "hotels");
			
			$("textarea[name=hotel_description]").val("");
			$("input[name=hotel_name]").val("");
			
			$("#hotel-modal js-submit-modal").text("Crear Hotel");
			$("#hotel-modal > .modal-header > h3").text("Nuevo Hotel");
		});
	
		
		$(".js-submit-modal").on("click", function() {
			$(".form").submit();
		});
		
		$(".js-cancel-modal").on("click", function(){
			$("#hotel-modal").modal("hide");
		});
		
	});	
})()


