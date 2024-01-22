let xBtn = document.getElementById("xBtn");
let loginErrModal = document.querySelector(".loginErrModal");

xBtn.addEventListener('click',()=>{
    loginErrModal.style.display="none";
})

let loginErr = document.getElementById("loginErr").value;
console.log("login" +loginErr);


if(loginErr == 1){
    loginErrModal.style.display="block";
}