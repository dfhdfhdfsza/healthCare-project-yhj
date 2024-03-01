

function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}

const jwtToken = getCookie('jwtToken');

window.onload = function() {
    let jwtToken = getCookie('jwtToken');

    

    if (jwtToken) {
        // 로그인 상태일 때의 동작
        document.querySelector(".login").style.display = 'none'; // 로그인
        document.querySelector('.signup').style.display = 'none'; // 회원가입
        

        let tokens = jwtToken.split('.');
        let decodedToken = JSON.parse(atob(tokens[1]));
        let userRole = decodedToken.userRole;
        if(userRole == "ROLE_ADMIN"){
            document.querySelector('.add-product').style.display = 'blcok'; // 상품등록
            document.querySelector('.modify-product').style.display = 'block'; // 상품수정
        }else{
            document.querySelector('.add-product').style.display = 'none'; // 상품등록
            document.querySelector('.modify-product').style.display = 'none'; // 상품수정
        }
    
    } else {
        // 비로그인 상태일 때의 동작
        // 유저와 어드민을 분리.
        
            document.querySelector('.add-product').style.display = 'none'; // 상품등록
            document.querySelector('.modify-product').style.display = 'none'; // 상품수정
        
            document.querySelector('.logout').style.display = 'none'; // 로그이웃
            document.querySelector('.myPage').style.display = 'none'; // 마이페이지
            document.querySelector('.community').style.display = 'none'; // 커뮤니티
            document.querySelector('.list-product').style.display = 'none'; // 상품목록
            document.querySelector('.list-health').style.display = 'none'; // 운동리스트
        
        
    }

    
}

