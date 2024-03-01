
let valid_id = document.querySelector('.valid-id');
let valid_password = document.querySelector('.valid-password');
let valid_name = document.querySelector('.valid-name');
let valid_address = document.querySelector('.valid-address');
let valid_number = document.querySelector('.valid-number');
let valid_age = document.querySelector('.valid-age');

let ageRegex = /^[0-9]+$/; // 숫자만 가능
let koreaRegex = /^[가-힣]+$/; // 한글만 가능
let phoneRegex =  /^\d{11}$/; // 11자리 숫자만 가능
let addressRegex = /^[가-힣0-9]*$/;
let idRegex = /^[a-zA-Z0-9]{8,16}$/; 
let passwordRegex = /^[a-zA-Z0-9!@#\$%\^&*\(\)\-_=+\[\]\{\};:'",.<>\/\?\\|`~]{8,16}$/;

let userId = document.getElementById('userId');
let userPassword = document.getElementById('userPassword');
let userName = document.getElementById('userName');
let userAddress = document.getElementById('userAddress');
let userNumber = document.getElementById('userNumber');
let userAge = document.getElementById('userAge');



let signupBtn = document.querySelector('.signupBtn');


function joinUser(){


    // id
    userId.addEventListener('change',(e)=>{
        if(idRegex.test(e.target.value) == false){
            valid_id.style.fontSize = "12px";
        }else{
            valid_id.style.fontSize = "0px";
        }
     })
     // pwd
     userPassword.addEventListener('change',(e)=>{
        if(passwordRegex.test(e.target.value) == false){
            valid_password.style.fontSize = "12px";
        }else{
            valid_password.style.fontSize = "0px";
        }
     })
     // name
     userName.addEventListener('change',(e)=>{
        if(koreaRegex.test(e.target.value) == false){
            valid_name.style.fontSize = "12px";
        }else{
            valid_name.style.fontSize = "0px";
        }
     })
     // 주소
     userAddress.addEventListener('change',(e)=>{
        if(addressRegex.test(e.target.value) == false){
            valid_address.style.fontSize = "12px";
        }else{
            valid_address.style.fontSize = "0px";
        }
     })
     // 번호
     userNumber.addEventListener('change',(e)=>{
        if(phoneRegex.test(e.target.value) == false){
            valid_number.style.fontSize = "12px";
        }else{
            valid_number.style.fontSize = "0px";
        }
     })
     // 나이
     userAge.addEventListener('change',(e)=>{
        if(ageRegex.test(e.target.value) == false){
            valid_age.style.fontSize = "12px";
        }else{
            valid_age.style.fontSize = "0px";
        }
     })

}
// let userId = document.getElementById('userId');
// let userPassword = document.getElementById('userPassword');
// let userName = document.getElementById('userName');
// let userAddress = document.getElementById('userAddress');
// let userNumber = document.getElementById('userNumber');
// let userAge = document.getElementById('userAge');

signupBtn.addEventListener('click',(event)=>{
    if(idRegex.test(userId.value) == false || passwordRegex.test(userPassword.value) == false ||  koreaRegex.test(userName.value) == false ||
    addressRegex.test(userAddress.value) == false  || phoneRegex.test(userNumber.value) == false ||  ageRegex.test(userAge.value) == false )
    {
        alert('일치하지 않는 조건이 있습니다. 다시 확인해주세요');
        event.preventDefault();
    }
})