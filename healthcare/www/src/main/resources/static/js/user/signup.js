
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

     // 전부 true면 회원가입
     let validation_id = false;
    let validation_password = false;
    let validation_name = false;
    let validation_address = false;
    let validation_number = false;
    let validation_age = false;
    // id
    userId.addEventListener('change',(e)=>{
        if(idRegex.test(e.target.value) == false){
            valid_id.style.fontSize = "12px";
            validation_id = false;
        }else{
            valid_id.style.fontSize = "0px";
            validation_id = true;
        }
     })
     // pwd
     userPassword.addEventListener('change',(e)=>{
        if(passwordRegex.test(e.target.value) == false){
            valid_password.style.fontSize = "12px";
            validation_password = false;
        }else{
            valid_password.style.fontSize = "0px";
            validation_password = true;
        }
     })
     // name
     userName.addEventListener('change',(e)=>{
        if(koreaRegex.test(e.target.value) == false){
            valid_name.style.fontSize = "12px";
            validation_name = false;
        }else{
            valid_name.style.fontSize = "0px";
            validation_name = true;
        }
     })
     // 주소
     userAddress.addEventListener('change',(e)=>{
        if(addressRegex.test(e.target.value) == false){
            valid_address.style.fontSize = "12px";
            validation_address = false;
        }else{
            valid_address.style.fontSize = "0px";
            validation_address = true;
        }
     })
     // 번호
     userNumber.addEventListener('change',(e)=>{
        if(phoneRegex.test(e.target.value) == false){
            valid_number.style.fontSize = "12px";
            validation_number = false;
        }else{
            valid_number.style.fontSize = "0px";
            validation_number = true;
        }
     })
     // 나이
     userAge.addEventListener('change',(e)=>{
        if(ageRegex.test(e.target.value) == false){
            valid_age.style.fontSize = "12px";
            validation_age = false;
        }else{
            valid_age.style.fontSize = "0px";
            validation_age = true;
        }
     })

     
        if(validation_id == false && validation_password == false && validation_name == false && 
            validation_address == false && validation_number == false && validation_age == false){
            console.log('<D>S<AD>AS>D<>ASD');
            
        }
    
    
}