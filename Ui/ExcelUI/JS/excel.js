$(document).ready(function() {


     $('#uploadBtn').click(function () {
        let file = $('#excelFile')[0].files[0];

        if (!file) {
            alert("Please choose an Excel file first!");
            return;
        }
        

        let formData = new FormData();
        formData.append("file", file);

        $.ajax({
            url: 'http://localhost:8080/api/employees/upload',
            type: 'POST',
            data: formData,
            processData: false,  
            contentType: false,   
            success: function (response) {
                alert(response); 
            },
            error: function (xhr, status, error) {
                console.error("Upload Error:", error);
                alert("Failed to upload file.");
            }
        });
    });
$('#getData').click(function() {
    
    $('#userTable').show();
$('#userTable').DataTable({
    serverSide: true,
    processing: true,
    ajax: function(data, callback) {
        let page = data.start / data.length;
        let size = data.length;

        $.ajax({
            url: 'http://localhost:8080/api/employees/getAll',
            type: 'GET',

            data: { page: page, size: size },
            success: function(result) {
                callback({
                    recordsTotal: result.totalElements,   
                    recordsFiltered: result.totalElements, 
                    data: result.content                  
                });
            },
            error: function() {
                alert("Failed to fetch data");
            }
        });
     
    },
       columns : [
            {data : 'id'},
            {data : 'empId'},
            {data : 'email'},
            {data : 'name'},
            {data : 'dob'},
            {data : 'salary'}
        ]
});
});
});