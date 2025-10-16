$(document).ready(function() {
    $('#signUpBtn').click(function() {
        let user = {
            name: $('#signupName').val(),
            email: $('#signupEmail').val(),
            password: $('#signupPassword').val(),
            confirmPassword: $('#signupConfirmPassword').val(),
            phoneNumber: $('#signupMobileNumber').val(),
            countryCode: parseInt($('#countryCode').val())
        };

        if(user.password !== user.confirmPassword) {
            alert("Passwords do not match!");
            return;
        }

        $.ajax({
            url: 'http://localhost:8080/ui/user/signup',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(user),
            success: function(response) {
                alert("Sign Up Successful");
            },
            error: function(xhr, status, error) {
                console.log("XHR:", xhr);
                console.log("Status:", status);
                console.log("Error:", error);
                alert("Sign Up Failed: " + (xhr.responseText || error));
            }
        });
    });
});
