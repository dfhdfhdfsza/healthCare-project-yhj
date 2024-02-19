console.log("jsIns");



function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}

const jwtToken = getCookie('jwtToken');
console.log(jwtToken); // 콘솔에 JWT 토큰 출력

window.onload = function() {
    const jwtToken = getCookie('jwtToken');
    if (jwtToken) {
        // 로그인 상태일 때의 동작
        document.querySelector(".login").style.display = 'none'; // 로그인
        document.querySelector('.signup').style.display = 'none'; // 회원가입
    } else {
        // 비로그인 상태일 때의 동작
        document.querySelector('.logout').style.display = 'none'; // 로그이웃  
        document.querySelector('.myPage').style.display = 'none'; // 마이페이지
        document.querySelector('.community').style.display = 'none'; // 커뮤니티
        document.querySelector('.add-product').style.display = 'none'; // 상품등록
        document.querySelector('.list-product').style.display = 'none'; // 상품목록
        document.querySelector('.modify-product').style.display = 'none'; // 상품수정
        document.querySelector('.list-health').style.display = 'none'; // 운동리스트
    }
}