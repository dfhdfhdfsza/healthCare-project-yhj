
let userProfile = document.querySelector('.hidden-userProfile'); //히든 file input
let profileImage = document.querySelector('.profile-image'); // 프로필 이미지
let profile_modal = document.querySelector('.profileImage-modify-modal'); // 프로필 변경 모달창
let modify_button = document.querySelector('.modify-button'); //프로필사진 변경 버튼

let save = document.querySelector('.save'); // 저장버튼
// 저장버튼 누르면 비동기로 사진을 저장

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

