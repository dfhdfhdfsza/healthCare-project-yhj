
let userProfile = document.querySelector('.hidden-userProfile'); //히든 file input
let profileImage = document.querySelector('.profile-image'); // 프로필 이미지
let profile_modal = document.querySelector('.profileImage-modify-modal'); // 프로필 변경 모달창
let modify_button = document.querySelector('.modify-button'); //프로필사진 변경 버튼

let save = document.querySelector('.save'); // 저장버튼


profileImage.addEventListener('click',()=>{
    // 프로필 이미지 클릭 => 모달창 오픈
    profile_modal.style.display = "block";
})

modify_button.addEventListener('click',()=>{
    // 사진추가 누르면 input file 실행
    userProfile.click();
})

// 모달창 닫기
let close = document.querySelector('.closeBtn');
close.addEventListener('click',()=>{
    profile_modal.style.display="none";
})


let close_button = document.querySelector('.close-button');
let cancel_btn = document.querySelector('.cancel-btn');
let userDelete_modal = document.querySelector('.userDelete-modal');
close_button.addEventListener('click',()=>{
    userDelete_modal.style.display = "none";
})
cancel_btn.addEventListener('click',()=>{
    userDelete_modal.style.display = "none";
})
let user_delete = document.querySelector('.user-delete');
user_delete.addEventListener('click',()=>{
    userDelete_modal.style.display = "block";
})

let commentZone = document.querySelector('.commentZone'); // 댓글리스트
let productZone = document.querySelector('.productZone'); // 구매목록리스트

let writing_me = document.querySelector('.writing-me'); // 작성한글
let payment_me = document.querySelector('.payment-me'); // 구매목록

payment_me.addEventListener('click',()=>{
    commentZone.style.display = "none";
    productZone.style.display = "block";

    writing_me.style.backgroundColor = "#dfdfdf";
    payment_me.style.backgroundColor = "#fff";
})

writing_me.addEventListener('click',()=>{
    commentZone.style.display = "block";
    productZone.style.display = "none";

    writing_me.style.backgroundColor = "#fff";
    payment_me.style.backgroundColor = "#dfdfdf";
})


// 개인정보 변경

// 비밀번호
let rePassword = document.querySelector('.modify-rePassword-input');
let no_password = document.querySelector('.no-password');
rePassword.addEventListener('change',(e)=>{
    let password = document.querySelector('.modify-password-input').value;
    if(e.target.value != password){
        no_password.style.display = "block";
    }else{
        no_password.style.display = "none";
    }
})

// 이름
let regex = /^[^a-zA-Z0-9]*$/; // 특수문자 , 숫자 , 영어 불가능
let regexNumber = /^[0-9]*$/; // 숫자만 가능

let modify_userName_input = document.querySelector('.modify-userName-input');
let no_name = document.querySelector('.no-name');
modify_userName_input.addEventListener('change',(e)=>{
    if(regex.test(e.target.value) == false){
        no_name.style.display = "block"
    }else{
        no_name.style.display = "none"
    }
})
//  전화번호 , 나이

let modify_userNumber_input = document.querySelector('.modify-userNumber-input');
let modify_userAge_input = document.querySelector('.modify-userAge-input');

let no_number_phone = document.querySelector('.no-number-phone');
let no_number_age = document.querySelector('.no-number-age');

modify_userNumber_input.addEventListener('change',(e)=>{
    if(regexNumber.test(e.target.value) == false){
        no_number_phone.style.display = "block";
    }else{
        no_number_phone.style.display = "none";
    }
})

modify_userAge_input.addEventListener('change',(e)=>{
    if(regexNumber.test(e.target.value) == false){
        no_number_age.style.display = "block";
    }else{
        no_number_age.style.display = "none";
    }
})







// 개인정보 변경 모달 닫기

let modify_modal_close = document.querySelectorAll('.modify-modal-close');
let modify_userInfo_modal = document.querySelector('.modify-userInfo-modal'); // 모달


modify_modal_close.forEach(e=>{
    e.addEventListener('click',()=>{
        modify_userInfo_modal.style.display = "none";
    })
})

// 열기
let openModifyModal = document.getElementById('userInfo-modify-modal-open');
openModifyModal.addEventListener('click',()=>{
    modify_userInfo_modal.style.display = "block";
})


let modify_modal_button = document.querySelector('.modify-modal-button');

modify_modal_button.addEventListener('click',(e)=>{
    let password = document.querySelector('.modify-password-input').value;
    if(password.length == 0){
        e.preventDefault();
        alert("변경하실 비빌번호를 입력해주세요");
    }
})