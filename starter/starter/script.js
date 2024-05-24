document.addEventListener("DOMContentLoaded", function () {
  const mobileMenu = document.querySelector(".mobile-menu");
  const menu = document.querySelector(".menu");

  mobileMenu.addEventListener("click", function () {
    menu.classList.toggle("show");
  });
});

function processRegistration(event) {
  event.preventDefault();
  //alert('registration simulation');
  let username = document.getElementById("username").value;
  let password = document.getElementById("password").value;
  //console.log(username);

  //localStorage.setItem("RegisteredUsers", username + ":" + password + ";");
  const usernameError = document.createElement('h6');
  const passwordError = document.createElement('h6');
  const form = document.querySelector('form');

  if (document.getElementById('username-error')) {
    document.getElementById('username-error').remove();
  }
  if (document.getElementById('password-error')) {
    document.getElementById('password-error').remove();
  }

  let isValid = true;

  // check for the username and the alert
  if (/[^a-zA-Z0-9]/.test(username)) {
    usernameError.textContent = "Username can only contain ordinary letters and numbers.";
    usernameError.id = 'username-error';
    usernameError.style.color = 'red';
    form.querySelector('.input-group').appendChild(usernameError);
    isValid = false;
  }

  // check for the password and the alert
  if (/[^a-zA-Z0-9]/.test(password) || !/(?=.*[a-z])(?=.*[A-Z])(?=.*\d)/.test(password)) {
    passwordError.textContent = "Password must contain uppercase and lowercase letters and numbers, and no special symbols.";
    passwordError.id = 'password-error';
    passwordError.style.color = 'red';
    form.querySelectorAll('.input-group')[1].appendChild(passwordError);
    isValid = false;
  }

  if (!isValid) {
    return;
  }

  
  localStorage.setItem("RegisteredUsers", username + ":" + password + ";");
  alert('Registration successful');
}

function processLogin(event) {
  event.preventDefault();
  let usernameEntered = document.getElementById("username").value;
  let passwordEntered = document.getElementById("password").value;

  //alert("login simulation");
  // Retrieving data from localStorage
  const registeredUsers = localStorage.getItem("RegisteredUsers");
  //console.log(registeredUsers);
  let loginStatus = false;
  let message = "";
  if (registeredUsers != null) {
    let usernamePasswordPairs = registeredUsers.split(";");
    //console.log(usernamePasswordPairs[0]);
    for (let i = 0; i < usernamePasswordPairs.length; i++) {
      //console.log(usernamePasswordPairs[i]);
      if (usernamePasswordPairs[i] != " ") {
        let registeredUsername = usernamePasswordPairs[i].split(":")[0];
        let registeredPassword = usernamePasswordPairs[i].split(":")[1];
        console.log(registeredUsername);
        console.log(registeredPassword);
        if (
          usernameEntered == registeredUsername &&
          passwordEntered == registeredPassword
        ) {
          loginStatus = true;
          break;
        }
      }
    }
    message = loginStatus
      ? "login success"
      : "login failed, invalid credentials";
  } else {
    message = "no one has registered!";
  }
  alert(message);
}
