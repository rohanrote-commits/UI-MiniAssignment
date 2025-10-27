$(document).ready(function() {

  $('#signUpForm').validate({
    rules: {
      signupName: {
        required: true,
        minlength: 3
      },
      signupEmail: {
        required: true,
        email: true
      },
      signupPassword: {
        required: true,
        minlength: 6
      },
      signupConfirmPassword: {
        required: true,
        equalTo: "#signupPassword"
      },
      signupMobileNumber: {
        required: true,
        digits: true,
        minlength: 10,
        maxlength: 10
      },
      countryCode: {
        required: true
      }
    },
    messages: {
      signupName: {
        required: "Please enter your name",
        minlength: "Name must be at least 3 characters long"
      },
      signupEmail: {
        required: "Please enter your email",
        email: "Enter a valid email address"
      },
      signupPassword: {
        required: "Please enter a password",
        minlength: "Password must be at least 6 characters"
      },
      signupConfirmPassword: {
        required: "Please confirm your password",
        equalTo: "Passwords do not match"
      },
      signupMobileNumber: {
        required: "Please enter your mobile number",
        digits: "Please enter only digits",
        minlength: "Mobile number must be 10 digits",
        maxlength: "Mobile number must be 10 digits"
      },
      countryCode: {
        required: "Please select your country code"
      }
    },
    errorClass: "error-message",
    errorPlacement: function(error, element) {
      error.insertAfter(element);
    },
    
    
    submitHandler: function(form) {
      let user = {
        name: $('#signupName').val(),
        email: $('#signupEmail').val(),
        password: $('#signupPassword').val(),
        confirmPassword: $('#signupConfirmPassword').val(),
        phoneNumber: $('#signupMobileNumber').val(),
        countryCode: parseInt($('#countryCode').val())
      };

      $.ajax({
        url: 'http://localhost:8080/ui/user/signup',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(user),
        success: function(response) {
          alert("Sign Up Successful");
        },
        error: function(xhr, status, error) {
          if (xhr.status === 409) {
            alert("Email is already registered");
          } else if (xhr.status === 400) {
            alert("Passwords do not match");
          } else {
            alert("Sign Up Failed: " + (xhr.responseText || "Server error"));
          }
        }
      });
    }
  });
});
