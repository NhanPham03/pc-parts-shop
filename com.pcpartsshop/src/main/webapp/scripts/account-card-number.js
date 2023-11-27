document.addEventListener('DOMContentLoaded', function() {
	var cardNumber = document.getElementById('creditCardInput');
	var cleave = new Cleave(cardNumber, {
		creditCard: true,
		delimiter: '-',
	});
});
