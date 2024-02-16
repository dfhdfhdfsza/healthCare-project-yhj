
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