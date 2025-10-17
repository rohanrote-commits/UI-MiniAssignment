$(document).ready(function() {
    $('#loginButton').click(function() {
        let user = {
            email : $('#loginEmail').val(),
            password : $('#loginPassword').val()
        };

        $.ajax({
            url : 'http://localhost:8080/ui/user/login',
            type : 'POST',
            contentType : 'application/json',
            xhrFields: { withCredentials: true },
            data : JSON.stringify(user),
            success : function(response){
                if(response){
                    alert('Login Successful...');
                    window.location.href = "userList.html";
                    
                }
            },
            error : function(xhr) {
             
                    if(xhr.status === 401){
                    alert("Invalid credentials. Signup if you dont have account");
                    $('#signupRedirectBtn').show();

                    }else{
                        alert("Server side error occured")
                    }

            }
            
            
        });

    });
    $('#signupRedirectBtn').click(function (){
        window.location.href = "signup.html";
    })
  
})