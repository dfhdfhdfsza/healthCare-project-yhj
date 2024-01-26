
let userProfile = document.querySelector('.hidden-userProfile'); //히든 file input
let profileImage = document.querySelector('.profile-image'); // 프로필 이미지
let profile_modal = document.querySelector('.profileImage-modify-modal'); // 프로필 변경 모달창
let modify_button = document.querySelector('.modify-button'); //프로필사진 변경 버튼


const regExp = new RegExp("\.(exe|sh|bat|js|msi|dll)$");
const regExpImg = new RegExp("\.(jpg|jpeg|png|gif)$");
const maxSize = 1024 * 1024 * 20;

// 이미지 파일만 들어가게 설정하는 함수
function fileValidation(fileName, fileSize){
    if(!regExpImg.test(fileName)){
        return 0;
    }else if(regExp.test(fileName)){
        return 0;
    }else if(fileSize > maxSize){
        return 0;
    }else{
        return 1;
    }
}

userProfile.addEventListener("change",(e)=>{
    console.log(e.target.files);

    let file = e.target.files[0]; // 선택한 파일
    let reader = new FileReader();

    reader.readAsDataURL(file); // 파일 읽는 메서드

    reader.onload = function(){
        var photoFrame = document.createElement("div");
      photoFrame.style = `background : url(${reader.result}); background-size : cover`;
      photoFrame.className = "photoFrame";
      document.getElementById("pictures").appendChild(photoFrame);
      e.target.value = "";

      photoFrame.addEventListener("click",function(){
        document.getElementById("pictures").removeChild(photoFrame);
      })
    }
})


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


async function addProfieImage(){

}