$(document).ready(function() {

//  $.ajax({
    //     url: 'http://localhost:8080/ui/user/session-check',
    //     type: 'GET',
    //     xhrFields: { withCredentials: true },
    //     crossDomain : true,
    //     success: function(user) {
    //         console.log("Session active:", user);
    //     },
    //     error: function(xhr) {
    //         if(xhr.status === 401){
    //             alert("Session expired. Login again.");
    //             window.location.href = "login.html";
    //         }
    //     }
    // });



$('#userTable').DataTable({
    serverSide: true,
    processing: true,
    ajax: function(data, callback) {
        let page = data.start / data.length;
        let size = data.length;

        $.ajax({
            url: 'http://localhost:8080/ui/user/all-data',
            type: 'GET',
    
            headers : {
                'Authorization' : sessionStorage.getItem("token")
            },
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

    columns: [
        { data: 'id' },
        { data: 'name' },
        { data: 'email' },
        { data: 'countryCode' },
        { data: 'phoneNumber' }
    ]
});

$('#logoutBtn').click(function() {
        $.ajax({
            url: 'http://localhost:8080/ui/user/logout',
            type: 'POST',
                   headers : {
                'Authorization' : sessionStorage.getItem("token")
            },
            success: function() {
                sessionStorage.removeItem("token");
                alert("Logged out successfully");
                window.location.href = "login.html";
            }
        });
    });

});

