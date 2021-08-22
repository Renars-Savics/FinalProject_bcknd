$(document).ready(function () {
    $.ajax({
          type: "GET",
          url: "http://localhost:8080/api/rest/Account.svc/accounts",
        success: function(data, status){

          tableCreate(data);
        },
        error: function(request, textStatus, error){

        }
      });
});
function tableCreate(data) {
  var acctable = document.getElementById('acctable');
  var accountTableBody = document.getElementById('accountTableBody');

  for (var i = 0; i < data.length; i++) {
    var $line = $('<tr>');
    $line.append('<th scope="row">'+(i+1)+'</th>');
    $line.append('<td>'+data[i].accountNumber+'</td>');
    $line.append('<td>'+data[i].ballance+'</td>');
    $line.append('<td>'+data[i].currency+'</td>');
    $line.append('<td>'+data[i].status+'</td>');
    $('#acctable').append($line);

  }
  acctable.appendChild(accountTableBody);
}
