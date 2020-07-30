$(document).ready(function() {
	
	// Crestere suma
	var $sumField = $('input[name="sum"]');
	$('#increaseSumButton').on('click', function() {
		var sumAsString = $sumField.val();
		if (sumAsString.length > 0) {
			var sum = parseFloat(sumAsString);
			sum += 100;
			sumAsString = sum.toString();
			$sumField.val(sumAsString);
		}
	});
	
	// Prevenire submit multiplu
	var $submitButton = $('#submitButton');
	$submitButton.on('click', function() {
		$submitButton.val('Asteptati...');
		$submitButton.prop('disabled', true);
	});
	
});