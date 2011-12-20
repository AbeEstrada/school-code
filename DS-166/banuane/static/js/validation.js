var validator_login = new FormValidator('login', [{
    name: 'account',
    display: 'cuenta',    
    rules: 'required|numeric'
}, {
    name: 'password',
    display: 'contraseña',    
    rules: 'required'
}], function(errors, events) {
    if (errors.length > 0)
        show_errors(errors);
});

var validator_newaccount = new FormValidator('newaccount', [{
    name: 'first_name',
    display: 'Nombre',    
    rules: 'required'
}, {
    name: 'last_name',
    display: 'Apellido',
    rules: 'required'
}, {
    name: 'telephone',
    display: 'Teléfono',
    rules: 'required|numeric|min_length[10]|max_length[10]'
}, {
    name: 'address',
    display: 'Dirección',
    rules: 'required'
}, {
    name: 'password',
    display: 'Contraseña',
    rules: 'required'
}, {
    name: 'password2',
    display: 'Contraseña',
    rules: 'required|matches[password]'
}, {
    name: 'email',
    display: 'Email',
    rules: 'required|valid_email'
}, {
    name: 'balance',
    display: 'Saldo',
    rules: 'required|numeric|integer|greater_than[0]|less_than[1000000]'
}], function(errors, events) {
    if (errors.length > 0)
        show_errors(errors);
});

var validator_transactions = new FormValidator('transactions', [{
    name: 'account',
    display: 'cuenta',    
    rules: 'required|numeric'
}, {
    name: 'amount',
    display: 'cantidad',    
    rules: 'required|numeric|integer'
}], function(errors, events) {
    if (errors.length > 0)
        show_errors(errors);
});

var validator_services = new FormValidator('services', [{
    name: 'receipt',
    display: 'recibo',    
    rules: 'required|numeric'
}, {
    name: 'amount',
    display: 'cantidad',    
    rules: 'required|numeric|integer'
}], function(errors, events) {
    if (errors.length > 0)
        show_errors(errors);
});

var validator_deposit = new FormValidator('deposit', [{
    name: 'first_name',
    display: 'nombre',    
    rules: 'required'
}, {
    name: 'last_name',
    display: 'apellidos',    
    rules: 'required'
}, {
    name: 'account',
    display: 'cuenta',    
    rules: 'required|numeric'
}, {
    name: 'currency',
    display: 'moneda',    
    rules: 'required'
}, {
    name: 'amount',
    display: 'cantidad',    
    rules: 'required|numeric|integer'
}], function(errors, events) {
    if (errors.length > 0)
        show_errors(errors);
});

var validator_atm = new FormValidator('atm', [{
    name: 'amount',
    display: 'cantidad',    
    rules: 'required|numeric|integer'
}], function(errors, events) {
    if (errors.length > 0)
        show_errors(errors);
});


var show_errors = function(errors) {
    if ($('ul.formerrors').length == 0) {
        $('section').prepend('<ul class="formerrors"></ul>');
    }
    $('ul.formerrors').html('');
    for (var i=0; i < errors.length; i++) {
        $('ul.formerrors').append('<li>'+errors[i]+'</li>');
    }
};