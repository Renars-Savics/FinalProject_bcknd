$(document).ready(function () {
$("#submitButton").on("click", function() {

    var accountNumber = $('#accountNumber').val();
    var currency = $('#currency').val();
    var ballance = $('#ballance').val();
    var status = $('#status').val();


      var request = {
        accountNumber: accountNumber,
        currency: currency,
        ballance: ballance,
        status: status
      };

        $.ajax({
          type: "POST",
          url: "http://localhost:8080/api/rest/Account.svc/account",
          data: JSON.stringify(request),
        contentType: 'application/json',
          dataType: 'json',
          encode: true,
        success: function(data, status){

          window.location.assign("http://localhost:8080/accounts.html");
        },
        error: function(request, textStatus, error){

          window.location.assign("http://localhost:8080");
        }});

});

});