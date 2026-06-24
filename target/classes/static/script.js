function showLogin(){
    document.getElementById("loginForm").style.display = "block";
    document.getElementById("registerForm").style.display = "none";
    document.getElementById("registerBtn").classList.add("active");
    document.getElementById("loginBtn").classList.remove("active");
    document.getElementById("greed").innerText="Sign in to access your account.";
}

function showRegister(){
    document.getElementById("loginForm").style.display = "none";
    document.getElementById("registerForm").style.display = "block";
    document.getElementById("loginBtn").classList.add("active");
    document.getElementById("registerBtn").classList.remove("active");
    document.getElementById("greed").innerText="Join us and start your journey today.";
}
async function registerForm() {
    let name = document.getElementById("regName").value.trim();
    let email = document.getElementById("regEmail").value.trim();
    let pass = document.getElementById("regPass").value.trim();
    let repass = document.getElementById("regRepass").value.trim();
    let registerMessage=document.getElementById("registerMessage");
    let greed=document.getElementById("commonForLoginAndRegisterGreed");
    const usernameRegex = /^[A-Za-z0-9]+$/;
    const emailRegex =/^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if(!usernameRegex.test(name)){
        return;
    }
    if(!emailRegex.test(email)){
        return;
    }
    if(pass!==repass){
        return;
    }
    const response=await fetch(
        `http://localhost:8080/api/register?name=${name}&email=${email}&pass=${pass}`,
        {
            method:"POST"
        }
    );
    const data=await response.text();
    if(data === "Register Successfully"){

        greed.innerText=data;

        document.getElementById("regName").value = "";
        document.getElementById("regEmail").value = "";
        document.getElementById("regPass").value = "";
        document.getElementById("regRepass").value = "";

        document.getElementById("nameWarning").innerText = "";
        document.getElementById("emailWarning").innerText = "";
        document.getElementById("passWarning").innerText = "";
        document.getElementById("repassWarning").innerText = "";
        setTimeout(() => {
            greed.innerText="";
            showLogin();
        }, 1000);
    }
    else{
        registerMessage.innerText=data;
        registerMessage.style.color="red";
    }
}
async function loginForm(){
    let name = document.getElementById("loginName").value.trim();
    let pass = document.getElementById("loginPass").value.trim();
    let loginMessage=document.getElementById("loginMessage");
    let greed=document.getElementById("commonForLoginAndRegisterGreed");
    loginMessage.innerText="";
    const response=await fetch(
        `http://localhost:8080/api/login?name=${name}&pass=${pass}`,
        {
            method:"POST"
        }
    );
    const data=await response.text();
    if(data !== "Invalid Username or Password"){
        greed.innerText="Login Successfully";
        const arr=data.split(" ");
        localStorage.setItem("username", arr[0]);
        localStorage.setItem("id", arr[1]);
        localStorage.setItem("email", arr[2]);
        localStorage.setItem("role",arr[3]);
        // window.location.href = "dashboard.html";
        window.location.replace("dashboard.html");
    }
    else{
        loginMessage.innerText=data;
        loginMessage.style.color="red";
    }
}
document.getElementById("regName").addEventListener("input", function() {

    let name = this.value;
    let warning = document.getElementById("nameWarning");

    if(name === ""){
        warning.innerText = "";
        return;
    }

    if(!/^[A-Za-z0-9]+$/.test(name)){
        warning.innerText =
        "Username can contain only A-Z, a-z, and 0-9";
        warning.style.color = "red";
    }
    else{
        warning.innerText = "";
    }
});
document.getElementById("regEmail").addEventListener("input", function() {

    let email = this.value;
    let warning = document.getElementById("emailWarning");

    if(email === ""){
        warning.innerText = "";
        return;
    }

    if(!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)){
        warning.innerText =
        "Invalid Syntax";
        warning.style.color = "red";
    }
    else{
        warning.innerText = "";
    }
});
document.getElementById("regPass").addEventListener("input", function() {

    let pass = this.value;
    let warning = document.getElementById("passWarning");
    if(pass.length==0){
        warning.innerText = "";
        return;
    }
    else if(pass.length < 6){
        warning.innerText = "Weak Password";
        warning.style.color = "red";
    }
    else if(pass.length < 8){
        warning.innerText = "Medium Password";
        warning.style.color = "orange";
    }
    else if(
        /[A-Z]/.test(pass) &&
        /[a-z]/.test(pass) &&
        /[0-9]/.test(pass) &&
        /[@#$%^&*!]/.test(pass)
    ){
        warning.innerText = "Strong Password";
        warning.style.color = "green";
    }
    else{
        warning.innerText = "Medium Password";
        warning.style.color = "orange";
    }
});
document.getElementById("regRepass").addEventListener("input", function() {

    let repass = this.value;
    let pass = document.getElementById("regPass").value.trim();
    let warning = document.getElementById("repassWarning");

    if(repass === ""){
        warning.innerText = "";
        return;
    }

    if(pass !== repass){
        warning.innerText = "Passwords do not match";
        warning.style.color = "red";
    }
    else{
        warning.innerText = "Passwords match";
        warning.style.color = "green";
    }
});