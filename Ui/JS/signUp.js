$(document).ready(function() {
    $('#signUpForm').submit(function(e) {
        e.preventDefault();
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
                if(xhr.status === 409) {
                    alert("Email is already registered");
                } else if(xhr.status === 400) {
                    alert("Passwords do not match");
                } else {
                    alert("Sign Up Failed: " + (xhr.responseText || "Server error"));
                }
            }
        });
    });
});
